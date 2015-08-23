package com.zwjgsgoa.dao.impl;

import org.springframework.stereotype.Component;

import com.zwjgsgoa.dao.IUserBaseOperateDao;
import com.zwjgsgoa.model.BaseModel;

@Component("userBaseOperateDao")
public class UserBaseOperateDao extends BaseDao implements IUserBaseOperateDao {
	
	/** 
	 * @description 用户注册功能 
	 * @param  userModel:用户的model  matchType:baseDao 里面匹配NULL的规则
	 * @return 如果没有接收到异常返回成功页面,如果接收到异常返回错误一面
	 * @throws 根据匹配规则和DAO底层抛出SQLException 或者 ZGException
	 **/
	public void userRegister(BaseModel userModel,String matchType) throws Exception {
		this.saveModel(userModel, matchType);
	}

}
