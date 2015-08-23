package com.zwjgsgoa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.zwjgsgoa.model.UserModel;
import com.zwjgsgoa.service.IUserBaseOperateService;

@Component
@Scope("prototype")
public class UserBaseOperateAction extends ActionSupport {

	private IUserBaseOperateService userBaseOperateService;
	private UserModel user;

	public IUserBaseOperateService getUserBaseOperateService() {
		return userBaseOperateService;
	}

	@Resource
	public void setUserBaseOperateService(
			IUserBaseOperateService userBaseOperateService) {
		this.userBaseOperateService = userBaseOperateService;
	}

	/**
	 * @description 用户注册功能
	 * @param
	 * @return 如果没有接收到异常返回成功页面,如果接收到异常返回错误一面
	 * @throws
	 */
	public String register() throws Exception {
		userBaseOperateService.userRegister(user);
		return SUCCESS;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}
