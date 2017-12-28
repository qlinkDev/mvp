/** 
 * @Package com.uu.modules.ico.web 
 * @Description 
 * @author yifang.huang
 * @date 2017年11月23日 下午3:35:05 
 * @version V1.0 
 */ 
package com.qlink.modules.neo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qlink.common.persistence.Page;
import com.qlink.common.web.BaseController;
import com.qlink.modules.neo.condition.MailFailureRecordCondition;
import com.qlink.modules.neo.entity.MailFailureRecord;
import com.qlink.modules.neo.service.MailFailureRecordService;

/** 
 * @Description 邮件记录  后台控制类
 * @author yifang.huang
 * @date 2017年11月23日 下午3:35:05 
 */
@Controller
@RequestMapping(value = "${adminPath}/neo/email")
public class MailFailureRecordController extends BaseController {
	
	@Autowired
	private MailFailureRecordService mailFailureRecordService;
	
	@ModelAttribute
	public MailFailureRecord get(@RequestParam(required=false) String id) {
		
		if (StringUtils.isNotBlank(id))
			return mailFailureRecordService.get(id);
		
		return new MailFailureRecord();
		
	}
	/**
	 * 
	 * @Description 分页查询
	 * @param condition	查询条件
	 * @param request
	 * @param response
	 * @param model
	 * @return String  
	 * @author yifang.huang
	 * @date 2017年10月30日 下午5:24:48
	 */
	@RequiresPermissions("neo:email:view")
	@RequestMapping(value = {"list", ""})
	public String list(MailFailureRecordCondition condition, 
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (StringUtils.isNotBlank(request.getParameter("queryData"))) {// 是否查询数据
	        Page<MailFailureRecord> page = mailFailureRecordService.findPage(new Page<MailFailureRecord>(request, response), condition); 
	        model.addAttribute("page", page);
		}
		
		return "modules/neo/mailFailureRecordList";
		
	}

}
