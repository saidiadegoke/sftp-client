package com.flexipgroup.app.cipher;

import lombok.Data;

@Data
public class UploadFileNameModel {
	private String fileName;
	
	public UploadFileNameModel(String fileName)
	{
		this.fileName = fileName;
	}
}
