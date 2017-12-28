package com.qlink.common.test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.qlink.common.utils.DigestUtils;
import com.qlink.common.utils.neo.http.HttpNeoSession;
import com.qlink.modules.sys.security.api.StatelessAuthcFilter;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import net.sf.json.JSONObject;

public class NeoApiTest {

	@Test
	public void saveSsid() throws UnsupportedEncodingException {
		//HttpRequest request = HttpRequest.get("http://47.90.50.172/api/neo/ssId/save.json");
		HttpRequest request = HttpRequest.get("http://47.90.50.172/api/neo/ssId/save.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");

		// params
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("ssId", "Prajeesh's xxx");
		recordMap.put("mac", "shuxin123");
		recordMap.put("p2pId", "123123123123123123123123");
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

		System.out.println(result);

	}
	
	@Test
	public void querySsid() throws UnsupportedEncodingException {
			//HttpRequest request = HttpRequest.get("http://localhost/api/neo/ssId/query.json");
			HttpRequest request = HttpRequest.get("http://47.90.50.172/api/neo/ssId/query.json");

			JSONObject obj = new JSONObject();
			obj.put("appid", "MIFI");
			obj.put("timestamp", "1345678990");

			// 数据参数
			Map<String, String> recordMap = new HashMap<String, String>();
			recordMap.put("ssId", "Prajeesh's xxx");
			JSONObject params = JSONObject.fromObject(recordMap);
			obj.put("params", params);

			String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

			// 参数
			JSONObject jsonParam = new JSONObject();
			jsonParam.put(StatelessAuthcFilter.PARAM_APPID, obj.get("appid"));
			jsonParam.put(StatelessAuthcFilter.PARAM_NAME, params);
			jsonParam.put(StatelessAuthcFilter.PARAM_TIMESTAMP, obj.get("timestamp"));
			jsonParam.put(StatelessAuthcFilter.PARAM_SIGN, sign);

			String bodyStr = jsonParam.toString();
			bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

			request.method("post");
			request.queryEncoding("UTF-8");
			request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
			request.header("Content-Type", "application/json;charset=utf-8");

			request.body(bodyStr);
			HttpResponse response = request.send();
			String result = response.bodyText();

			System.out.println(result);

	}
	
	@Test
	public void saveRecord() throws UnsupportedEncodingException {
		HttpRequest request = HttpRequest.get("http://47.90.50.172/api/dapp/record/save.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");

		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("recordId", "shuxin_2017");
		recordMap.put("addressFrom", "00-00-00-00-00-00-00-E0");
		recordMap.put("formP2pId", "857fe98f2dc84e15bc10376a5d470e11");
		recordMap.put("addressTo", "857fe98f2dc84e15bc10376a5d470e11");
		recordMap.put("toP2pId", "857fe98f2dc84e15bc10376a5d470e11");
		recordMap.put("qlc", "6521.5");
		JSONObject params = JSONObject.fromObject(recordMap);

		obj.put("params", params);

		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

		// 参数
		JSONObject jsonParam = new JSONObject();
		jsonParam.put(StatelessAuthcFilter.PARAM_APPID, obj.get("appid"));
		jsonParam.put(StatelessAuthcFilter.PARAM_NAME, params);
		jsonParam.put(StatelessAuthcFilter.PARAM_TIMESTAMP, obj.get("timestamp"));
		jsonParam.put(StatelessAuthcFilter.PARAM_SIGN, sign);

		String bodyStr = jsonParam.toString();
		bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();

		System.out.println(result);

	}

	@Test
	public void queryRid() throws UnsupportedEncodingException {
		HttpRequest request = HttpRequest.get("http://47.90.50.172/api/dapp/record/query.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");

		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("recordId", "shuxin_2017");
		JSONObject params = JSONObject.fromObject(recordMap);
		obj.put("params", params);

		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

		// 参数
		JSONObject jsonParam = new JSONObject();
		jsonParam.put(StatelessAuthcFilter.PARAM_APPID, obj.get("appid"));
		jsonParam.put(StatelessAuthcFilter.PARAM_NAME, params);
		jsonParam.put(StatelessAuthcFilter.PARAM_TIMESTAMP, obj.get("timestamp"));
		jsonParam.put(StatelessAuthcFilter.PARAM_SIGN, sign);

		String bodyStr = jsonParam.toString();
		bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();

		System.out.println(result);

	}
	
	@Test
	public void testJsonRpc(){
		//0x5577e874d78d0509e43fc1618c32a8bcf2691fbf
		try {
    		JSONArray parameters = new JSONArray();
    		parameters.add("0x5577e874d78d0509e43fc1618c32a8bcf2691fbf");
    		parameters.add("deploy");
    		Credentials credentials = new UsernamePasswordCredentials("", "");
			URI uri = new URI("http", null, "127.0.0.1", 20332, null, null, null);
			HttpNeoSession session  = new HttpNeoSession(uri, credentials);
        	com.alibaba.fastjson.JSONObject request = createRequest("invokefunction", parameters);
        	com.alibaba.fastjson.JSONObject response = session.sendAndReceive(request);
        	System.out.println(response.toJSONString());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSendraw(){
		//0x5577e874d78d0509e43fc1618c32a8bcf2691fbf
		//AQC7Bod2LxaRxmLewRrwCA1Nt6AQMWSm28
		try {
    		JSONArray parameters = new JSONArray();
    		parameters.add("0400c2eb0b145c564ab204122ddce30eb9a6accbfa23b27cc3ac145659f46be31edc4c35f84cf0ee5c22618f241af053c1087472616e7366657267bf1f69f2bca8328c61c13fe409058dd774e87755f1");
    		Credentials credentials = new UsernamePasswordCredentials("", "");
			URI uri = new URI("http", null, "127.0.0.1", 20332, null, null, null);
			HttpNeoSession session  = new HttpNeoSession(uri, credentials);
        	com.alibaba.fastjson.JSONObject request = createRequest("sendrawtransaction", parameters);
        	com.alibaba.fastjson.JSONObject response = session.sendAndReceive(request);
        	System.out.println(response.toJSONString());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testTranserf(){
		//0x5577e874d78d0509e43fc1618c32a8bcf2691fbf
		//APeTXAPz3UEekLPu5fye26XdmCVjZVUX1P   from
		//AQC7Bod2LxaRxmLewRrwCA1Nt6AQMWSm28  to
		try {
    		JSONArray parameters = new JSONArray();
    		parameters.add("0x5577e874d78d0509e43fc1618c32a8bcf2691fbf");
    		parameters.add("transfer");
    		JSONArray pp = new JSONArray();
			JSONObject obj1 = new JSONObject();
			obj1.put("type", "ByteArray");
			//Integer[] from = new Integer[]{240, 26, 36, 143, 97, 34, 92, 238, 240, 76, 248, 53, 76, 220, 30, 227, 107, 244, 89, 86};
			Integer[] from = new Integer[]{86, 89, 244, 107, 227, 30, 220, 76, 53, 248, 76, 240, 238, 92, 34, 97, 143, 36, 26, 240};
			//byte[] from = new byte[]{-16,26,36,-113,97,34,92,-18,-16,76,-8,53,76,-36,30,-29,107,-12,89,86};
			//byte[] from = new byte[]{86,89,-12,107,-29,30,-36,76,53,-8,76,-16,-18,92,34,97,-113,36,26,-16};
			obj1.put("value", from);
			pp.add(obj1);
			JSONObject obj2 = new JSONObject();
			obj2.put("type", "ByteArray");
			//Integer[] to = new Integer[]{172,195,124,178,35,250,203,172,166,185,14,227,220,45,18,4,178,74,86,92};
			Integer[] to = new Integer[]{92, 86, 74, 178, 4, 18, 45, 220, 227, 14, 185, 166, 172, 203, 250, 35, 178, 124, 195,172};
			//byte[] to = new byte[]{-84,-61,124,-78,35,-6,-53,-84,-90,-71,14,-29,-36,45,18,4,-78,74,86,92};
			//byte[] to = new byte[]{92,86,74,-78,4,18,45,-36,-29,14,-71,-90,-84,-53,-6,35,-78,124,-61,-84};
			obj2.put("value", to);
			pp.add(obj2);
			JSONObject obj3 = new JSONObject();
			obj3.put("type", "Integer");
			obj3.put("value", 1000);
			pp.add(obj3);
			parameters.add(pp);
    		Credentials credentials = new UsernamePasswordCredentials("", "");
			URI uri = new URI("http", null, "127.0.0.1", 20332, null, null, null);
			HttpNeoSession session  = new HttpNeoSession(uri, credentials);
        	com.alibaba.fastjson.JSONObject request = createRequest("invokefunction", parameters);
        	com.alibaba.fastjson.JSONObject response = session.sendAndReceive(request);
        	System.out.println(response.toJSONString());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private com.alibaba.fastjson.JSONObject createRequest(String functionName) throws JSONException {
		return createRequest(functionName, new JSONArray());
	}
	
	private com.alibaba.fastjson.JSONObject createRequest(String functionName, JSONArray parameters) throws JSONException {
		com.alibaba.fastjson.JSONObject request = new com.alibaba.fastjson.JSONObject();
		request.put("jsonrpc", "2.0");
		request.put("id", UUID.randomUUID().toString());
		request.put("method", functionName);
		request.put("params", parameters);

		return request;
	}

	public static void main(String[] args) {
		String str = "f01a248f61225ceef04cf8354cdc1ee36bf45956";
		//String str = "acc37cb223facbaca6b90ee3dc2d1204b24a565c";  //to
		StringBuffer ja = new StringBuffer();
		StringBuffer ca  = new StringBuffer();
		byte[] ss = hexStrToByteArray(str);
		for (int i = 0; i < ss.length; i++) {
			ja.append(ss[i]);
			ja.append(",");
		}
		for (int i = 0; i < ss.length; i++) {
			ca.append(bytetoint(ss[i]));
			ca.append(",");
		}
		System.out.println(ja.toString());
		System.out.println(reverse(ja.toString()));
		System.out.println(ca.toString());
		System.out.println(reverse(ca.toString()));
	}
	
	public static String reverse(String str){
		StringBuffer buffer = new StringBuffer();
		String[] temp = str.split(",");
		for (int i = temp.length -1; i >= 0 ; i--) {
			buffer.append(temp[i]);
			buffer.append(",");
		}
		return buffer.toString();
	}
	
	public static int bytetoint(byte tb)
	{
	  int temp;
	  temp = Integer.valueOf(tb);
	  if(temp < 0)
	  {
	    temp = temp & 0x7F + 128;
	  }
	  return temp;
	}
	
	public static byte[] hexStrToByteArray(String str)
	{
	    if (str == null) {
	        return null;
	    }
	    if (str.length() == 0) {
	        return new byte[0];
	    }
	    byte[] byteArray = new byte[str.length() / 2];
	    for (int i = 0; i < byteArray.length; i++){
	        String subStr = str.substring(2 * i, 2 * i + 2);
	        byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
	    }
	    return byteArray;
	}
	
	public static String byteArrayToHexStr(byte[] byteArray) {
	    if (byteArray == null){
	        return null;
	    }
	    char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[byteArray.length * 2];
	    for (int j = 0; j < byteArray.length; j++) {
	        int v = byteArray[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

}
