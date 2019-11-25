package com.flexipgroup.app.cipher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UploadFilesCollection{

	
	private static Set<String> uploadFiles = new TreeSet<String>();
	private static List<String> uploadFileList = new ArrayList<String>();
	
	public void add(String e) {
		uploadFiles.add(e);
	}

	public void update(String e) {
		delete(e);
		add(e);
		
	}

	public String get(int e) {
		Iterator<String> i = uploadFiles.iterator();
		
		while(i.hasNext())
		{
			uploadFileList.add(i.next());
		}
		return uploadFileList.get(e);
	}

	public static Set<String> getAll() {
		// TODO Auto-generated method stub
		return uploadFiles;
	}

	public void delete(String e) {
		uploadFiles.remove(e);
	}

	
}
