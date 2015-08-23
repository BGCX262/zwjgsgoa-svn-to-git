package com.zwjgsgoa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zwjgsgoa.dao.impl.BaseDao;
import com.zwjgsgoa.model.BaseModel;
import com.zwjgsgoa.service.IUserBaseOperateService;

@Component
public class UserBaseOperateService implements IUserBaseOperateService {
	private BaseDao userBaseOperateDao;
	
	/** 
	 * @description 用户注册功能 
	 * @param  userModel:用户的model
	 * @return 
	 * @throws 注册失败就抛出ZGException异常
	 **/
	public void userRegister(BaseModel model)throws Exception{
		userBaseOperateDao.saveModel(model, BaseDao.MATCH_NULL_4_ALL);
	}

	public BaseDao getUserBaseOperateDao() {
		return userBaseOperateDao;
	}
	
	@Resource
	public void setUserBaseOperateDao(BaseDao userBaseOperateDao) {
		this.userBaseOperateDao = userBaseOperateDao;
	}

}
