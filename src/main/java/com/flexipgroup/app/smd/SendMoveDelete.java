package com.flexipgroup.app.smd;

import java.util.Set;

import com.flexipgroup.app.cipher.CopyFileToUploaded;
import com.flexipgroup.app.cipher.UploadFileNameCollection;

public class SendMoveDelete {
	
	
	public static boolean sendNotification=false;
	public static boolean moveNotification = false;
	public static boolean deleteNotification = false;
	
	private static boolean send()
	{
		return sendNotification;
	}
	
	private static boolean move()
	{
		return moveNotification;
	}
	
	private static boolean delete()
	{
		return deleteNotification;
	}
	
	private SendMoveDelete(String uploadfileLocation,String uploadedLocation) {
		
		sendMoveDelete(uploadfileLocation,uploadedLocation);
	}
	
	private SendMoveDelete() {}
	
	
	public static SendMoveDelete getInstance(String uploadfileLocation,String uploadedLocation)
	{
		return new SendMoveDelete(uploadfileLocation,uploadedLocation);
	}
	
	
	private static void sendMoveDelete (String uploadfileLocation,String uploadedLocation) {
	
		//send method
		while(true) {
			try {
				System.out.println("SEND STATUS :" +send());

				System.out.println("UPLOAD FILE LOCATION : "+uploadfileLocation);
				System.out.println("UPLOADED LOCATION : "+uploadedLocation);
				System.out.println("UPLOAD FILE NAME SIZE : "+UploadFileNameCollection.getAll().size());
				
				CopyFileToUploaded.copyFile(uploadfileLocation, uploadedLocation);

				Thread.sleep(5000);
				break;
			}catch(InterruptedException io) {}
				
		}
	}
	

}
