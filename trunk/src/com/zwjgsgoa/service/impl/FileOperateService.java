package com.zwjgsgoa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.zwjgsgoa.service.IFileOperateService;

@Component
public class FileOperateService implements IFileOperateService {
	
	
	/** 
	 * @description 用户上传文件功能,上传到项目根目录下
	 * @param  savePath：是否要再根目录下进一步调整位置，现在为空 ，fileName文件名，upload上传的文件
	 * @return 如果没有接收到异常返回成功页面,如果接收到异常返回错误页面
	 * @throws
	 **/
	public void fileUpload(String savePath, String fileName, File upload)
			throws Exception {
		// 以服务器路劲和文件名作输出流
		FileOutputStream fos = new FileOutputStream(savePath + fileName);
		// 以上传文件作为输入流
		FileInputStream fis = new FileInputStream(upload);

		byte[] buffer = new byte[1024];

		int len = 0;

		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}

	}

}
