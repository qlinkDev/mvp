package com.qlink.common.utils.neo.thread;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlink.common.utils.DigestUtils;
import com.qlink.common.utils.IdGen;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import net.sf.json.JSONObject;

public class SsidSaveThread implements Runnable{
	public static Logger logger = LoggerFactory.getLogger(SsidSaveThread.class);
	
	public static void saveSSID(String ssId, String mac, String p2pId) throws UnsupportedEncodingException{
		logger.info("SsidSaveThread===========开始");
		//HttpRequest request = HttpRequest.get("http://47.90.50.172/api/dapp/ssId/save.json");
		HttpRequest request = HttpRequest.get("http://localhost/api/dapp/ssId/save.json");
		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");
		// params
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("ssId", ssId);
		recordMap.put("mac", mac);
		recordMap.put("p2pId", p2pId);
		JSONObject params = JSONObject.fromObject(recordMap);
		obj.put("params", params);
		//sign
		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");
		// params
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appid", obj.get("appid"));
		jsonParam.put("params", params);
		jsonParam.put("timestamp", obj.get("timestamp"));
		jsonParam.put("sign", sign);

		String bodyStr = jsonParam.toString();
		bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();

		logger.info("SsidSaveThread===========结束");
	}

	@Override
	public void run() {
		try {
			SsidSaveThread.saveSSID("ssid_test_"+(int)((Math.random()*9+1)*1000), RandomMacAddress.getMacAddrWithFormat(":"), IdGen.uuid());
		} catch (UnsupportedEncodingException e) {
			logger.info("调用saveSSID接口异常信息====="+e.getMessage());
		}
	}

}
