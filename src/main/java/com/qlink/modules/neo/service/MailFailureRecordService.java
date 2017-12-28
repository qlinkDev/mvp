/** 
 * @Package com.uu.modules.ico.service 
 * @Description 
 * @author yifang.huang
 * @date 2017年11月23日 下午3:31:33 
 * @version V1.0 
 */ 
package com.qlink.modules.neo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlink.common.persistence.Page;
import com.qlink.common.service.BaseService;
import com.qlink.modules.neo.condition.MailFailureRecordCondition;
import com.qlink.modules.neo.dao.MailFailureRecordDao;
import com.qlink.modules.neo.entity.MailFailureRecord;

/** 
 * @Description 邮件记录 业务处理
 * @author yifang.huang
 * @date 2017年11月23日 下午3:31:33 
 */
@Service
@Transactional(readOnly = true)
public class MailFailureRecordService extends BaseService {
	
	@Autowired
	private MailFailureRecordDao mailFailureRecordDao;
	
	/**
	 * 
	 * @Description 根据ID取数据
	 * @param id
	 * @return MailFailureRecord  
	 * @author yifang.huang
	 * @date 2017年11月14日 下午3:56:08
	 */
	public MailFailureRecord get(String id) {
		MailFailureRecord oldBean = mailFailureRecordDao.get(id);
		if (oldBean != null) {
			MailFailureRecord newBean = new MailFailureRecord();
			BeanUtils.copyProperties(oldBean, newBean);
			// 清除指定对象缓存
			mailFailureRecordDao.getSession().evict(oldBean);
			return newBean;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description 根据查询参数取列表数据
	 * @param condition
	 * @return List<MailFailureRecord>  
	 * @author yifang.huang
	 * @date 2017年11月14日 下午3:56:17
	 */
	public List<MailFailureRecord> findList(MailFailureRecordCondition condition) {
		
		return mailFailureRecordDao.findList(condition);
		
	}
	
	/**
	 * 
	 * @Description 根据查询参数取分页数据
	 * @param page
	 * @param condition
	 * @return Page<MailFailureRecord>  
	 * @author yifang.huang
	 * @date 2017年11月14日 下午3:56:25
	 */
	public Page<MailFailureRecord> findPage(Page<MailFailureRecord> page, MailFailureRecordCondition condition) {
		
		return mailFailureRecordDao.findPage(page, condition);
		
	}

	/**
	 * 
	 * @Description 保存数据
	 * @param bean 
	 * @return void  
	 * @author yifang.huang
	 * @date 2017年11月14日 下午3:56:33
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void save(MailFailureRecord bean) {
		mailFailureRecordDao.save(bean);
	}
	
	
	public MailFailureRecord findListByNest() {
		String qlString = "From MailFailureRecord m where m.status = 'SUCCESS' order by createDate desc";
		List<MailFailureRecord> list = mailFailureRecordDao.find(qlString);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
