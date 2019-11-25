package com.flexipgroup.app.cipher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UploadFilesCollection{

	
	private static Set<String> uploadFiles = new TreeSet<String>();
	
	public void add(String e) {
		uploadFiles.add(e);
	}

	public void update(String e) {
		delete(e);
		add(e);
		
	}

	public static String get(int e) {
		List<String> getFile  = new ArrayList<String>();
		Iterator<String> i = uploadFiles.iterator();		
		while(i.hasNext())
		{
			getFile.add(i.next());
		}
		return getFile.get(e);
	}

	public static Set<String> getAll() {
		// TODO Auto-generated method stub
		return uploadFiles;
	}

	public static void delete(String e) {
		uploadFiles.remove(e);
	}
	
	public static void deleteAll() {
		uploadFiles.clear();
	}

	
}
