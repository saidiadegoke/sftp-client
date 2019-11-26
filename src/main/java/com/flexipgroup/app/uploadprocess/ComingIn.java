package com.flexipgroup.app.uploadprocess;

import java.util.ArrayList;
import java.util.List;

public class ComingIn implements UploadDirectorySortRule<UploadDirectoryModel>{
	
	private List<UploadDirectoryModel>comingIn = new ArrayList<UploadDirectoryModel>();
	
	@Override
	public void add(UploadDirectoryModel filePath) {
		// TODO Auto-generated method stub
		comingIn.add(filePath);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		comingIn.clear();
	}

	@Override
	public void delete(String filePath) {
		// TODO Auto-generated method stub
		comingIn.remove(filePath);
	}

	@Override
	public List<UploadDirectoryModel> get() {
		// TODO Auto-generated method stub
		return comingIn;
	}
	
	

}
