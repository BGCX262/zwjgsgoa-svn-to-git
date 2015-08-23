
package com.zwjgsgoa.action; 

import java.io.File;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.zwjgsgoa.exception.ZGException;
import com.zwjgsgoa.service.IFileOperateService;

@Component
@Scope("prototype")
public class FileOperateAction extends ActionSupport {
	private IFileOperateService ifo;
	
	//文件标题
	private String fileTitle;
	//上传文件域的属性
	private File upload;
	//上传文件的属性类型
	private String uploadContentType;
	//上传文件的名字
	private String uploadFileName;
	//保存路劲
	private String savePath;
	
	/** 
	 * @description 用户上传文件功能,上传到项目根目录下
	 * @param  
	 * @return 如果没有接收到异常返回成功页面,如果接收到异常返回错误页面
	 * @throws
	 **/
	public String upload() throws Exception{
		ifo.fileUpload(getSavePath(), uploadFileName, upload);	
		return SUCCESS;
	}


	public IFileOperateService getIfo() {
		return ifo;
	}

	@Resource
	public void setIfo(IFileOperateService ifo) {
	
		this.ifo = ifo;
	}


	public String getFileTitle() {
	
		return fileTitle;
	}


	public void setFileTitle(String fileTitle) {
	
		this.fileTitle = fileTitle;
	}


	public File getUpload() {
	
		return upload;
	}


	public void setUpload(File upload) {
	
		this.upload = upload;
	}


	public String getUploadContentType() {
	
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
	
		this.uploadContentType = uploadContentType;
	}


	public String getUploadFileName() {
	
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
	
		this.uploadFileName = uploadFileName;
	}


	public String getSavePath() {
	
		return ServletActionContext.getRequest().getRealPath(savePath);
	}


	public void setSavePath(String savePath) {
	
		this.savePath = savePath;
	}
}

