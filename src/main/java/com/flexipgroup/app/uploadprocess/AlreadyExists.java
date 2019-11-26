package com.flexipgroup.app.uploadprocess;

import java.util.ArrayList;
import java.util.List;

public class AlreadyExists implements UploadDirectorySortRule<UploadDirectoryModel>{

private List<UploadDirectoryModel>alreadyExists= new ArrayList<UploadDirectoryModel>();
	

	@Override
	public void add(UploadDirectoryModel filePath) {
		// TODO Auto-generated method stub
		alreadyExists.add(filePath);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		alreadyExists.clear();
	}

	@Override
	public void delete(String filePath) {
		// TODO Auto-generated method stub
		alreadyExists.remove(filePath);
	}

	@Override
	public List<UploadDirectoryModel> get() {
		// TODO Auto-generated method stub
		return alreadyExists;
	}
	
	
}
