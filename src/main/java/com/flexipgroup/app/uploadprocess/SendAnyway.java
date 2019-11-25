package com.flexipgroup.app.uploadprocess;

import java.util.ArrayList;
import java.util.List;

public class SendAnyway implements UploadDirectorySortRule<UploadDirectoryModel>{
	
	private List<UploadDirectoryModel>sendAnyWay = new ArrayList<UploadDirectoryModel>();
	
	@Override
	public void add(UploadDirectoryModel filePath) {
		// TODO Auto-generated method stub
		sendAnyWay.add(filePath);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		sendAnyWay.clear();
	}

	@Override
	public void delete(String filePath) {
		// TODO Auto-generated method stub
		sendAnyWay.remove(filePath);
	}

	@Override
	public List<UploadDirectoryModel> get() {
		// TODO Auto-generated method stub
		return sendAnyWay;
	}
	
}
