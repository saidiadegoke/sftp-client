package com.flexipgroup.app.cipher;

import lombok.Data;

@Data
public class UploadFilesModel {
	
	private String file;
	
	
	public UploadFilesModel(String file) 
	{
		this.file = file;
	}
	
}
