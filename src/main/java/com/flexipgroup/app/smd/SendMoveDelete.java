package com.flexipgroup.app.smd;

import java.util.List;

import com.flexipgroup.app.cipher.CopyFileToUploaded;

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
	
	private SendMoveDelete(List<String> uploadfileNameCollection,String uploadfileLocation,String uploadedLocation) {
		
		sendMoveDelete(uploadfileNameCollection,uploadfileLocation,uploadedLocation);
	}
	
	private SendMoveDelete() {}
	
	
	public static SendMoveDelete getInstance(List<String> uploadfileNameCollection,String uploadfileLocation,String uploadedLocation)
	{
		return new SendMoveDelete(uploadfileNameCollection,uploadfileLocation,uploadedLocation);
	}
	
	
	private static void sendMoveDelete (List<String> uploadfileNameCollection,String uploadfileLocation,String uploadedLocation) {
	
		//send method
		while(true) {
			try {
				System.out.println("SEND STATUS :" +send());
				
//				if(send()==true)
//				{	
//					//move method					
//					if(move()==true && delete() == true)
//					{								
						//delete method
				
				System.out.println("UPLOAD FILE LOCATION : "+uploadfileLocation);
				System.out.println("UPLOADED LOCATION : "+uploadedLocation);
				
				CopyFileToUploaded.copyFile(uploadfileNameCollection, uploadfileLocation, uploadedLocation);
//					}else {
//						System.out.println("...Copying files to Uploaded Directory");
//						System.out.println("...Deleting file from Upload Directory");
//					}
//					System.out.println("...Files copied to uploaded folder and deleted from upload folder");
//					break;
//				}else
//				{
//					System.out.println("...Sending Files to Server");
//				}
				Thread.sleep(5000);
			}catch(InterruptedException io) {}
				
		}
	}
	

}
