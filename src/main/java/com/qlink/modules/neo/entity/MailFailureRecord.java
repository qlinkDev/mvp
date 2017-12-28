/** 
 * @Package com.uu.modules.ico.entity 
 * @Description 
 * @author yifang.huang
 * @date 2017年11月23日 下午2:45:32 
 * @version V1.0 
 */ 
package com.qlink.modules.neo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.qlink.common.persistence.BaseEntity;
import com.qlink.common.utils.IdGen;

/** 
 * @Description 邮件记录  实体类
 * @author yifang.huang
 * @date 2017年11月23日 下午2:45:32 
 */
@Entity
@Table(name = "mail_record")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MailFailureRecord extends BaseEntity<MailFailureRecord> {

	private static final long serialVersionUID = 6961536122345849761L;
	
	private String id;								// 唯一值
	
	private String emails;							// 接收邮件的邮箱
	
	private String type;							// 邮件类型(JSONRPC-rpc,)
	
	private String status;							// 邮件发送状态(FAIL_失败,SUCCESS_成功)
	
	private String title;							// 邮件标题
	
	private String nickname;						// 发件人昵称				
	
	private String body;							// 邮件内容
	
	private Integer times;							// 重发次数
	
	private Date createDate;						// 创建时间
	
	public MailFailureRecord() {
		super();
		id = IdGen.uuid();
		status = MailFailureRecord.STATUS_FAIL;
		times = 0;
		createDate = new Date();
	}
	
	/** 
	 * @return id
	 */
	@Id
	public String getId() {
		return id;
	}

	/** 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * @return emails
	 */
	public String getEmails() {
		return emails;
	}

	/** 
	 * @param emails
	 */
	public void setEmails(String emails) {
		this.emails = emails;
	}

	/** 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/** 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/** 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/** 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/** 
	 * @return body
	 */
	public String getBody() {
		return body;
	}

	/** 
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/** 
	 * @return times
	 */
	public Integer getTimes() {
		return times;
	}

	/** 
	 * @param times
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}

	/** 
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/** 
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static final String TYPE_JSONRPC = "JSONRPC";							// 预售
	
	public static final String STATUS_FAIL = "FAIL";						// 失败
	public static final String STATUS_SUCCESS = "SUCCESS";					// 成功

}
