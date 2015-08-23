package com.zwjgsgoa.dao;

import com.zwjgsgoa.model.BaseModel;

public interface IUserBaseOperateDao {
	public void userRegister(BaseModel model,String matchType) throws Exception;
}
