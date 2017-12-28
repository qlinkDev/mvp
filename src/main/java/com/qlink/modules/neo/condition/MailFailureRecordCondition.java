/** 
 * @Package com.uu.modules.ico.condition 
 * @Description 
 * @author yifang.huang
 * @date 2017年11月23日 下午3:16:22 
 * @version V1.0 
 */ 
package com.qlink.modules.neo.condition;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.qlink.common.utils.DateUtils;

/** 
 * @Description 邮件记录 查询条件类
 * @author yifang.huang
 * @date 2017年11月23日 下午3:16:22 
 */
public class MailFailureRecordCondition {
	
	private String eqId;							// id eq 查询值
	
	private String likeEmails;						// 接收邮件的邮箱 like 查询值
	
	private String eqType;							// 类型 eq 查询值
	
	private String eqStatus;						// 状态 eq 查询值 
	
	private String geCreateDate;					// 创建时间  ge 查询值

	private String leCreateDate;					// 创建时间  le 查询值
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public void build(DetachedCriteria dc) {
		
		if (StringUtils.isNotBlank(eqId)) {
			dc.add(Restrictions.eq("id", eqId));
		}
		
		if (StringUtils.isNotBlank(likeEmails)) {
			dc.add(Restrictions.like("emails", likeEmails, MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(eqType)) {
			dc.add(Restrictions.eq("type", eqType));
		}
		
		if (StringUtils.isNotBlank(eqStatus)) {
			dc.add(Restrictions.eq("status", eqStatus));
		}
		
		if (StringUtils.isNotBlank(geCreateDate)) {
			try {
				dc.add(Restrictions.ge("createDate", DateUtils.getDateStart(df.parse(geCreateDate))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if (StringUtils.isNotBlank(leCreateDate)) {
			try {
				dc.add(Restrictions.le("createDate", DateUtils.getDateEnd(df.parse(leCreateDate))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		// 按创建时间降序排序
		dc.addOrder(Order.desc("createDate"));
		
	}

	/** 
	 * @return eqId
	 */
	public String getEqId() {
		return eqId;
	}

	/** 
	 * @param eqId
	 */
	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	/** 
	 * @return likeEmails
	 */
	public String getLikeEmails() {
		return likeEmails;
	}

	/** 
	 * @param likeEmails
	 */
	public void setLikeEmails(String likeEmails) {
		this.likeEmails = likeEmails;
	}

	/** 
	 * @return eqType
	 */
	public String getEqType() {
		return eqType;
	}

	/** 
	 * @param eqType
	 */
	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	/** 
	 * @return eqStatus
	 */
	public String getEqStatus() {
		return eqStatus;
	}

	/** 
	 * @param eqStatus
	 */
	public void setEqStatus(String eqStatus) {
		this.eqStatus = eqStatus;
	}

	/** 
	 * @return geCreateDate
	 */
	public String getGeCreateDate() {
		return geCreateDate;
	}

	/** 
	 * @param geCreateDate
	 */
	public void setGeCreateDate(String geCreateDate) {
		this.geCreateDate = geCreateDate;
	}

	/** 
	 * @return leCreateDate
	 */
	public String getLeCreateDate() {
		return leCreateDate;
	}

	/** 
	 * @param leCreateDate
	 */
	public void setLeCreateDate(String leCreateDate) {
		this.leCreateDate = leCreateDate;
	}
	
}
