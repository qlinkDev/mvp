/** 
 * @Package com.uu.modules.ico.dao 
 * @Description 
 * @author yifang.huang
 * @date 2017年11月23日 下午3:27:04 
 * @version V1.0 
 */ 
package com.qlink.modules.neo.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.qlink.common.persistence.BaseDao;
import com.qlink.common.persistence.Page;
import com.qlink.modules.neo.condition.MailFailureRecordCondition;
import com.qlink.modules.neo.entity.MailFailureRecord;

/** 
 * @Description 邮件记录  DAO接口
 * @author yifang.huang
 * @date 2017年11月23日 下午3:27:04 
 */
@Repository
public class MailFailureRecordDao extends BaseDao<MailFailureRecord> {

	/**
	 * 
	 * @Description 根据查询参数取列表数据
	 * @param condition
	 * @return List<MailFailureRecord>  
	 * @author yifang.huang
	 * @date 2017年10月30日 下午5:19:06
	 */
	public List<MailFailureRecord> findList(MailFailureRecordCondition condition) {
		
		DetachedCriteria dc = createDetachedCriteria();
		
		// build 查询条件
		condition.build(dc);
		
		return find(dc);
	}
	
	/**
	 * 
	 * @Description 根据查询参数取分页数据
	 * @param page
	 * @param condition
	 * @return Page<MailFailureRecord>  
	 * @author yifang.huang
	 * @date 2017年10月30日 下午5:19:16
	 */
	public Page<MailFailureRecord> findPage(Page<MailFailureRecord> page, MailFailureRecordCondition condition) {
		
		DetachedCriteria dc = createDetachedCriteria();
		
		// build 查询条件
		condition.build(dc);
		
		return find(page, dc);
	}

}
