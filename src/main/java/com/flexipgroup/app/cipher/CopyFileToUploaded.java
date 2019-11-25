package com.flexipgroup.app.cipher;

import java.io.File;
import java.util.List;

import com.flexipgroup.app.smd.SendMoveDelete;

public class CopyFileToUploaded {
	
	
	
	
	public static void copyFile(List<String> uploadfileNameCollection,String uploaddirectoryLocation,String uploadeddirectoryLocation)
	{
		
		int movemonitor = 0;
		int monitor = 0;
		
		
		for(String fileName : uploadfileNameCollection) {
			File file = new File(uploaddirectoryLocation+File.separator+fileName);
			
			//renaming the file and moving it to a new location
			if(file.renameTo(new File(uploadeddirectoryLocation+File.separator+fileName)))
			{
				movemonitor++;
				
				//if file copied successfully then delete the original
				file.delete();
				System.out.println(fileName + " moved successfully");
			}else
			{
				System.out.println("Failed to move "+ fileName);
				monitor++;
			}
		}
		
		if(uploadfileNameCollection.size() == movemonitor) {
			SendMoveDelete.moveNotification = true;
			SendMoveDelete.deleteNotification = true;
		}
	}
	
	
}
