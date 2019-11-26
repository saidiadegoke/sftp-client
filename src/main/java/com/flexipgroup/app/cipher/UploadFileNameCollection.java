package com.flexipgroup.app.cipher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UploadFileNameCollection {
	
	private static Set<String> uploadFileName = new TreeSet<String>();
	
	private static Set<String> uploadFileNameList = new TreeSet<String>();
	
	
	public void add(String e) {
		uploadFileName.add(e);
	}

	public void update(String e) {
		delete(e);
		add(e);	
	}

	public static String get(int e) 
	{	
		List<String> getFile  = new ArrayList<String>();
		Iterator<String> i = uploadFileName.iterator();	
		while(i.hasNext())
		{
			getFile.add(i.next());
		}
		return getFile.get(e);
	}
	
	public static Set<String> getAll() {
		return uploadFileName;
	}

	public static void delete(String e) {
		uploadFileName.remove(e);
	}
	
	public static void deleteAll() {
		uploadFileName.clear();
	}

}
