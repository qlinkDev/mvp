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

public class RecordSaveThread implements Runnable{
	public static Logger logger = LoggerFactory.getLogger(RecordSaveThread.class);
	
	public static void saveRecord(String recordId, String addressFrom, String formP2pId, String addressTo, String toP2pId, String qlc) throws UnsupportedEncodingException{
		logger.info("RecordSaveThread===========开始");
		//HttpRequest request = HttpRequest.get("http://47.90.50.172/api/dapp/record/save.json");
		HttpRequest request = HttpRequest.get("http://localhost/api/dapp/record/save.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");

		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("recordId", recordId);
		recordMap.put("addressFrom", addressFrom);
		recordMap.put("formP2pId", formP2pId);
		recordMap.put("addressTo", addressTo);
		recordMap.put("toP2pId", toP2pId);
		recordMap.put("qlc", qlc);
		JSONObject params = JSONObject.fromObject(recordMap);

		obj.put("params", params);

		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

		// 参数
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
		logger.info("RecordSaveThread===========结束");
	}

	@Override
	public void run() {
		try {
			RecordSaveThread.saveRecord("record_test_"+(int)((Math.random()*9+1)*1000), RandomMacAddress.getMacAddrWithFormat(":"), IdGen.uuid(), IdGen.uuid(), IdGen.uuid(), (int)((Math.random()*9+1)*1000)+"");
		} catch (UnsupportedEncodingException e) {
			logger.info("调用saveRecord接口异常信息====="+e.getMessage());
		}
	}

}
