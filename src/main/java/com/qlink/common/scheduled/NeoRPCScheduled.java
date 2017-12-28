package com.qlink.common.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qlink.common.utils.neo.thread.RecordQueryThread;
import com.qlink.common.utils.neo.thread.RecordSaveThread;
import com.qlink.common.utils.neo.thread.SsidQueryThread;
import com.qlink.common.utils.neo.thread.SsidSaveThread;

/** 
 * 文件读取定时调度
 * @Description 
 * @author shuxin
 * @date 2017年12月19日 下午4:30:34 
 */ 
@Component
public class NeoRPCScheduled {
	
	public static Logger logger = LoggerFactory.getLogger(NeoRPCScheduled.class);
	
	private static boolean IN_PROGRESS = false;
	
	
	@Scheduled(cron = "0 0/2 * * * ?") // 秒、分、时、日、月、年
	public void testApiRPC(){
		// 如果上一次未执行完
		if (IN_PROGRESS) {	
			logger.info("neo和rpc处理接口调用定时处理 上次未执行完成！");
			return;
		}
		logger.info("neo和rpc处理接口调用定时处理开始！");
		IN_PROGRESS = true;
		try {
			 SsidSaveThread ssidSave = new SsidSaveThread();
			 Thread ssidSaveThread = new Thread(ssidSave);
			 ssidSaveThread.start();
			 SsidQueryThread ssidQuery = new SsidQueryThread();
			 Thread querySsidThread = new Thread(ssidQuery);
			 querySsidThread.start();
			 RecordSaveThread recordSave = new RecordSaveThread();
			 Thread recordSaveThread = new Thread(recordSave);
			 recordSaveThread.start();
			 RecordQueryThread recordQuery = new RecordQueryThread();
			 Thread recordQueryThread = new Thread(recordQuery);
			 recordQueryThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			IN_PROGRESS = false;
		}
		logger.info("neo和rpc处理接口调用定时处理结束！");
	}
}
