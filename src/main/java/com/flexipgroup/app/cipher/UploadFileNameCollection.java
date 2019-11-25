package com.flexipgroup.app.cipher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UploadFileNameCollection {
	
	private static Set<String> uploadFileName = new TreeSet<String>();
	
	private static List<String> uploadFileNameList = new ArrayList<String>();
	
	
	public void add(String e) {
		uploadFileName.add(e);
	}

	public void update(String e) {
		delete(e);
		add(e);	
	}

	public static String get(int e) {
		
		return getList().get(e);
	}
	
	public static List<String>getList()
	{
		Iterator<String> i = uploadFileName.iterator();
		
		while(i.hasNext())
		{
			uploadFileNameList.add(i.next());
		}
		return uploadFileNameList;
	}
	
	
	public static Set<String> getAll() {
		return uploadFileName;
	}

	public void delete(String e) {
		uploadFileName.remove(e);
	}

}
