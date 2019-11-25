package com.flexipgroup.app.uploadprocess;

import java.util.ArrayList;
import java.util.List;

public class Processing implements UploadDirectorySortRule<UploadDirectoryModel>{

private List<UploadDirectoryModel>processing = new ArrayList<UploadDirectoryModel>();
	

	@Override
	public void add(UploadDirectoryModel filePath) {
		// TODO Auto-generated method stub
		processing.add(filePath);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		processing.clear();
	}

	@Override
	public void delete(String filePath) {
		// TODO Auto-generated method stub
		processing.remove(filePath);
	}

	@Override
	public List<UploadDirectoryModel> get() {
		// TODO Auto-generated method stub
		return processing;
	}
	
	
}
