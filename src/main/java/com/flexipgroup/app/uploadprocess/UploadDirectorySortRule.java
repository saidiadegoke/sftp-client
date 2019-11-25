package com.flexipgroup.app.uploadprocess;

import java.util.List;

public interface UploadDirectorySortRule<E> {

	public void add(E filePath);
	
	public void deleteAll();
	
	public void delete(String filePath);
	
	public List<E> get();
	
}
