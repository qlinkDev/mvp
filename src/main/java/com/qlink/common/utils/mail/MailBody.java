package com.qlink.common.utils.mail;

/**
 * 系统所有模块的邮件内容
 */
public class MailBody {

	/** 
	 * JsonRpc邮件
	 * @Description 
	 * @return 
	 * @return String  
	 * @author shuxin
	 * @date 2017年12月25日 下午2:24:17 
	 */ 
	public static String JsonRpcFailed(String mssage) {
		
		StringBuffer sbuffer = new StringBuffer();
		
		sbuffer.append("<div>"+mssage+"</div><br />");
		
		return sbuffer.toString();
	}

}
