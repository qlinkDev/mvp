package com.qlink.modules.neo.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlink.common.persistence.Page;
import com.qlink.common.service.BaseService;
import com.qlink.common.utils.DateUtils;
import com.qlink.modules.neo.condition.NeoRecordCondition;
import com.qlink.modules.neo.condition.NeoSsidCondition;
import com.qlink.modules.neo.dao.NeoRecordDao;
import com.qlink.modules.neo.dao.NeoSsidDao;
import com.qlink.modules.neo.entity.NeoRecord;
import com.qlink.modules.neo.entity.NeoSsid;
import com.qlink.modules.sys.utils.ApiUtils;
import com.qlink.modules.sys.utils.Constants;
import com.qlink.modules.utils.ReturnCode;

import jodd.jtx.meta.Transaction;
import net.sf.json.JSONObject;

/**
 * neo service
 * 
 * @Description
 * @author shuxin
 * @date 2017年12月9日 下午3:11:17
 */
@Service
@Transactional(readOnly = true)
public class NeoDateService extends BaseService {

	@Autowired
	private NeoSsidDao neoSsidDao;

	@Autowired
	private NeoRecordDao neoRecordDao;
	
	/**
	 * 保存ssid
	 * 
	 * @Description
	 * @param neoSsid
	 * @return void
	 * @author shuxin
	 * @date 2017年12月9日 下午8:47:00
	 */
	@Transaction(readOnly = false)
	public void saveNeoSsid(NeoSsid neoSsid) {
		neoSsidDao.save(neoSsid);
	}

	/**
	 * 保存record
	 * 
	 * @Description
	 * @param neoRecord
	 * @return void
	 * @author shuxin
	 * @date 2017年12月9日 下午8:47:03
	 */
	@Transaction(readOnly = false)
	public void saveNeoRecord(NeoRecord neoRecord) {
		neoRecordDao.save(neoRecord);
	}

	/**
	 * 根据sid获取 neoSsid
	 * 
	 * @Description
	 * @param sid
	 * @return
	 * @return NeoSsid
	 * @author shuxin
	 * @date 2017年12月10日 下午4:37:13
	 */
	public NeoSsid getNeoSsidbySid(String sid) {
		return neoSsidDao.getNeoSsidBySid(sid);
	}

	/**
	 * 根据rid获取 NeoRecord
	 * 
	 * @Description
	 * @param rid
	 * @return
	 * @return NeoRecord
	 * @author shuxin
	 * @date 2017年12月10日 下午4:38:35
	 */
	public NeoRecord getNeoRecordbyRid(String rid) {
		return neoRecordDao.getNeoRecordByRid(rid);
	}

	/** 
	 * ssid分页列表
	 * @Description 
	 * @param page
	 * @param condition
	 * @return 
	 * @return Page<NeoSsid>  
	 * @author shuxin
	 * @date 2017年12月14日 下午1:55:35 
	 */ 
	public Page<NeoSsid> findSsidPage(Page<NeoSsid> page, NeoSsidCondition condition) {
		
		return neoSsidDao.findPage(page, condition);
		
	}
	
	/** 
	 * record 分页列表
	 * @Description 
	 * @param page
	 * @param condition
	 * @return 
	 * @return Page<NeoRecord>  
	 * @author shuxin
	 * @date 2017年12月14日 下午1:55:49 
	 */ 
	public Page<NeoRecord> findRecordPage(Page<NeoRecord> page, NeoRecordCondition condition) {
		
		return neoRecordDao.findPage(page, condition);
		
	}
	
	/** 
	 * @Description 
	 * @param jsonObject
	 * @return 
	 * @return JSONObject  
	 * @author shuxin
	 * @date 2017年12月10日 下午5:12:42 
	 */ 
	@Transaction(readOnly = false)
	public  JSONObject saveSsi(JSONObject param) throws Exception {
		JSONObject resObj = new JSONObject();
		//默认返回值
		resObj.put("code", "0");
		resObj.put("msg", ReturnCode.ERR_0);
		Object ssIdObject = param.get(Constants.PARAM_SSID);
		String ssId = (ssIdObject == null ||  ("null".equals(ssIdObject.toString()))) ? "" : StringUtils.isNotBlank(ssIdObject.toString()) ? ssIdObject.toString() : "";
		if(!StringUtils.isNotBlank(ssId)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|ssid can't be empty!");
			return resObj;
		}
		if(ssId.length() > 32){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|ssid character length cannot be greater than 32!");
			return resObj;
		}
		Object macObject = param.get(Constants.PARAM_MAC);
		String mac = (macObject == null ||  ("null".equals(macObject.toString()))) ? "" : StringUtils.isNotBlank(macObject.toString()) ? macObject.toString() : "";
		if(!StringUtils.isNotBlank(mac)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|mac can't be empty!");
			return resObj;
		}
		Object p2pIdObject = param.get(Constants.PARAM_P2PID);
		String p2pId = (p2pIdObject == null ||  ("null".equals(p2pIdObject.toString()))) ? "" : StringUtils.isNotBlank(p2pIdObject.toString()) ? p2pIdObject.toString() : "";
		if(!StringUtils.isNotBlank(p2pId)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|p2pId can't be empty!");
			return resObj;
		}
		
		//数据库中去查
		NeoSsid existNeoSsid = neoSsidDao.getNeoSsidBySid(ssId);
		if(existNeoSsid == null){  //没有进行数据保存
			NeoSsid neoSsid = new NeoSsid();
			neoSsid.setSsid(ssId);
			neoSsid.setMac(mac);
			neoSsid.setP2pId(p2pId);
			neoSsidDao.save(neoSsid);
			resObj.put("ssId", ssId);
		} else { //存在
			resObj.put(ApiUtils.CODE, "46005");
			resObj.put(ApiUtils.MSG, "SSID exists");
		}
		return resObj;
	}

	/** 
	 * ssid 查询
	 * @Description 
	 * @param param
	 * @return 
	 * @return JSONObject  
	 * @author shuxin
	 * @date 2017年12月11日 上午10:56:00 
	 */ 
	public JSONObject queryBySsid(JSONObject param) throws Exception {
		JSONObject resObj = new JSONObject();
		//默认返回值
		resObj.put("code", "0");
		resObj.put("msg", ReturnCode.ERR_0);
		Object ssIdObject = param.get(Constants.PARAM_SSID);
		String ssId = (ssIdObject == null ||  ("null".equals(ssIdObject.toString()))) ? "" : StringUtils.isNotBlank(ssIdObject.toString()) ? ssIdObject.toString() : "";
		if(!StringUtils.isNotBlank(ssId)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|ssid can't be empty!");
			return resObj;
		}
		//数据库中去查
		NeoSsid existNeoSsid = neoSsidDao.getNeoSsidBySid(ssId);
		
		JSONObject rs = new JSONObject();
		if(existNeoSsid != null){
			rs.put("ssId", existNeoSsid.getSsid());
			rs.put("mac", existNeoSsid.getMac());
			rs.put("p2pId", existNeoSsid.getP2pId());
			resObj.put(ApiUtils.DATA, rs);
		} else {
			rs.put("ssId", "");
			rs.put("mac", "");
			rs.put("p2pId", "");
			resObj.put(ApiUtils.DATA, rs);
		}
		return resObj;
	}

	/** 
	 * 保存record
	 * @Description 
	 * @param param
	 * @return 
	 * @return JSONObject  
	 * @author shuxin
	 * @date 2017年12月11日 下午1:11:16 
	 */ 
	public JSONObject saveRecord(JSONObject param) throws Exception {
		JSONObject resObj = new JSONObject();
		//默认返回值
		resObj.put("code", "0");
		resObj.put("msg", ReturnCode.ERR_0);
		Object recordIdObject = param.get(Constants.PARAM_RECORD_ID);
		String recordId = (recordIdObject == null ||  ("null".equals(recordIdObject.toString()))) ? "" : StringUtils.isNotBlank(recordIdObject.toString()) ? recordIdObject.toString() : "";
		if(!StringUtils.isNotBlank(recordId)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|recordId can't be empty!");
			return resObj;
		}
		if(recordId.length() > 32){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|recordId character length cannot be greater than 32!");
			return resObj;
		}
		Object addressFromObject = param.get(Constants.PARAM_ADDRESSFROM);
		String addressFrom = (addressFromObject == null ||  ("null".equals(addressFromObject.toString()))) ? "" : StringUtils.isNotBlank(addressFromObject.toString()) ? addressFromObject.toString() : "";
		if(!StringUtils.isNotBlank(addressFrom)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|addressFrom can't be empty!");
			return resObj;
		}
		Object formP2pIdObject = param.get(Constants.PARAM_FORMP2PID);
		String formP2pId = (formP2pIdObject == null ||  ("null".equals(formP2pIdObject.toString()))) ? "" : StringUtils.isNotBlank(formP2pIdObject.toString()) ? formP2pIdObject.toString() : "";
		if(!StringUtils.isNotBlank(formP2pId)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|formP2pId can't be empty!");
			return resObj;
		}
		Object addressToObject = param.get(Constants.PARAM_ADDRESSTO);
		String addressTo = (addressToObject == null ||  ("null".equals(addressToObject.toString()))) ? "" : StringUtils.isNotBlank(addressToObject.toString()) ? addressToObject.toString() : "";
		if(!StringUtils.isNotBlank(addressTo)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|addressTo can't be empty!");
			return resObj;
		}
		Object toP2pIdObject = param.get(Constants.PARAM_TOP2PID);
		String toP2pId = (toP2pIdObject == null ||  ("null".equals(toP2pIdObject.toString()))) ? "" : StringUtils.isNotBlank(toP2pIdObject.toString()) ? toP2pIdObject.toString() : "";
		if(!StringUtils.isNotBlank(toP2pId)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|toP2pId can't be empty!");
			return resObj;
		}
		Object qlcValueObject = param.get(Constants.PARAM_QLCVALUE);
		String qlcValue = (qlcValueObject == null ||  ("null".equals(qlcValueObject.toString()))) ? "" : StringUtils.isNotBlank(qlcValueObject.toString()) ? qlcValueObject.toString() : "";
		if(!StringUtils.isNotBlank(qlcValue)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|qlc can't be empty!");
			return resObj;
		}
		if(!NumberUtils.isNumber(qlcValue)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|qlc format not correct!");
			return resObj;
		}
		
		//数据库中去查
		NeoRecord existNeoRecord = neoRecordDao.getNeoRecordByRid(recordId);
		if(existNeoRecord == null){  //没有进行数据保存
			NeoRecord neoRecord = new NeoRecord();
			neoRecord.setRecordKey(recordId);
			neoRecord.setAddressFrom(addressFrom);
			neoRecord.setFormP2pId(formP2pId);
			neoRecord.setAddressTo(addressTo);
			neoRecord.setToP2pId(toP2pId);
			neoRecord.setQlc(new BigDecimal(qlcValue));
			neoRecordDao.save(neoRecord);
			resObj.put("recordId", recordId);
		} else { //存在
			resObj.put(ApiUtils.CODE, "46005");
			resObj.put(ApiUtils.MSG, "RECORDID exists");
		}
		return resObj;
	}
	
	public JSONObject queryByRid(JSONObject param) throws Exception {
		JSONObject resObj = new JSONObject();
		//默认返回值
		resObj.put("code", "0");
		resObj.put("msg", ReturnCode.ERR_0);
		Object recordIdObject = param.get(Constants.PARAM_RECORD_ID);
		String recordId = (recordIdObject == null ||  ("null".equals(recordIdObject.toString()))) ? "" : StringUtils.isNotBlank(recordIdObject.toString()) ? recordIdObject.toString() : "";
		if(!StringUtils.isNotBlank(recordId)){
			resObj.put(ApiUtils.CODE, "61451");
			resObj.put(ApiUtils.MSG, ReturnCode.ERR_61451 + "|recordId can't be empty!");
			return resObj;
		}
		//根据ssid检索neoSsid是否存在
		NeoRecord existNeoRecord = neoRecordDao.getNeoRecordByRid(recordId);
		JSONObject rs = new JSONObject();
		if(existNeoRecord == null){
			rs.put("addressFrom", "");
			rs.put("fromP2pId", "");
			rs.put("addressTo", "");
			rs.put("toP2pId", "");
			rs.put("qlc", "");
			rs.put("time","");
		} else {
			rs.put("addressFrom", existNeoRecord.getAddressFrom());
			rs.put("fromP2pId", existNeoRecord.getFormP2pId());
			rs.put("addressTo", existNeoRecord.getAddressTo());
			rs.put("toP2pId", existNeoRecord.getToP2pId());
			rs.put("qlc", existNeoRecord.getQlc());
			rs.put("time",DateUtils.formatDateTime(existNeoRecord.getCreateDate()));
		}
		resObj.put(ApiUtils.DATA, rs);
		return resObj;
	}

}
