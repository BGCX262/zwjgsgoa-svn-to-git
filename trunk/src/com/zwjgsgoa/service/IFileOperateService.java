
package com.zwjgsgoa.service; 

import java.io.File;
import java.io.FileOutputStream;

import com.zwjgsgoa.service.impl.FileOperateService;

public interface IFileOperateService {
	public void fileUpload(String savePath,String fileName,File upload) throws Exception;
}


