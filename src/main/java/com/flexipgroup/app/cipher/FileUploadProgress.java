package com.flexipgroup.app.cipher;

import com.flexipgroup.app.smd.SendMoveDelete;
import com.jcraft.jsch.SftpProgressMonitor;

public class FileUploadProgress {
	
	private static SftpProgressMonitor monitorProgress ()
	{
		//monitor progress
		SftpProgressMonitor progress = new SftpProgressMonitor() {
			
			private long max,count,percent = 0;
			private long megabytes = 1000000;
			
			@Override
			public void init(int op, String src, String dest, long max) {
				this.max = max;
//				System.out.println("\n Starting...");
//				System.out.println("Local File : "+src);  // Origin destination
//				System.out.println("Remote Location : " +dest); // Destination path
//				System.out.println(max);  //Total filesize						
			}

			@Override
			public boolean count(long count) {							
				this.count += count;
				long percentNow = this.count*100/max;
				
				if(percentNow > this.percent) {
					this.percent = percentNow;
					
//					System.out.print("\nProgress : " + this.percent +"%    ");
//					System.out.printf("%-5s",this.count/megabytes + " mb / "); //Progress in bytes from the total
//					System.out.printf("%-15s",max/megabytes + " mb ");
					
				}							
				return true;
			}
			

			@Override
			public void end() {
//				System.out.println(this.percent +  "%");
//				System.out.println(max);
//				System.out.println(this.count);	
				if(this.percent == 100)
				{
					SendMoveDelete.sendNotification = true;
				}
			}						
		};
		
		return progress;
	}
	
	public static SftpProgressMonitor getMonitorProgress ()
	{
		return monitorProgress();
	}

}
