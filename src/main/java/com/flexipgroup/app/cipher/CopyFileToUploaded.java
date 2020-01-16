package com.flexipgroup.app.cipher;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.flexipgroup.app.smd.SendMoveDelete;
import com.jcraft.jsch.Logger;

public class CopyFileToUploaded {
	
	
	
	
	public static void copyFile(String uploaddirectoryLocation,String uploadeddirectoryLocation)
	{
		
		int movemonitor = 0;
		int monitor = 0;
		
		System.out.println("INITIAL FILE NAME COLLECTION SIZE : "+UploadFileNameCollection.getAll().size());

		for(String fileName : UploadFileNameCollection.getAll()) {
			
			File file = new File(uploaddirectoryLocation+File.separator+fileName);
			
			//renaming the file and moving it to a new location
			if(file.renameTo(new File(uploadeddirectoryLocation+File.separator+fileName)))
			{
				movemonitor++;				
				//if file copied successfully then delete the original
				file.delete();
				System.out.println(fileName + " moved successfully");
			}
		}
		System.out.println("...Deleting files from collection");
		removeFromList ();
		
	}
	
	
	
	public static void removeFromList ()
	{
		System.out.println("FILE COLLECTION SIZE : "+UploadFilesCollection.getAll().size());
		System.out.println("FILE NAME COLLECTION SIZE : "+UploadFileNameCollection.getAll().size());
		
		UploadFileNameCollection.deleteAll();
		UploadFilesCollection.deleteAll();
		
		System.out.println("FILE COLLECTION SIZE : "+UploadFilesCollection.getAll().size());
		System.out.println("FILE NAME COLLECTION SIZE : "+UploadFileNameCollection.getAll().size());
	}
	
	
}
