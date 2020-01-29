package com.flexipgroup.app.cipher;

import java.io.File;

import org.ini4j.Wini;

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.app.service.FileObserver;
import com.flexipgroup.app.smd.SendMoveDelete;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;

public class RunEnviron {
	
	public static void runsend(UploadFilesCollection ufc,UploadFileNameCollection ufnc) throws Exception
	{
		String path = System.getProperty("user.home")+File.separator;
		
		
		for(int i=0;i < UploadFilesCollection.getAll().size();i++)
		{
			String file = ufc.get(i);
			
			String fileName = UploadFileNameCollection.get(i);
			ChannelSftp sftp = SFTPAgent.connect();
			
//			System.out.println("sending file "+file);
//			System.out.println("sending filename "+fileName);
			
			//send method
			SFTPAgent.send(sftp, file);
		
		}
		
		//SendMoveDelete.getInstance(path+ini.get("polling", "upload"),path+ini.get("polling", "uploaded"));
		
	}
	
	public static void main1 (String [] args)throws Exception
	{
		String iniPath = System.getProperty("user.home")+File.separator+"/Documents/flexware/sftp-client/config.ini";		
		Wini ini = new Wini(new File(iniPath));
		
		String host = ini.get("sftp", "host");

		String username = ini.get("sftp", "username");
		String password = ini.get("sftp", "password");
		RegisterKnownHosts.getInstance(host);
		
		UploadFilesCollection ufc = new UploadFilesCollection();
		UploadFileNameCollection ufnc = new UploadFileNameCollection();
		
		Thread t1 = new Thread() {
			@Override
			public void run ()
			{
				try {
					SFTPAgent sftp = new SFTPAgent("", new ConfigurationFile());
					sftp.connect();
				} catch (JSchException e) {
					FileLogger.log(e.toString(),"error");

				} catch (Exception e) {
					FileLogger.log(e.toString(),"error");

				}
//				System.out.println("Status : Connection established");
			}
		};
		
		Thread t2 = new Thread() {
			@Override
			public void run ()
			{
				try 
				{				
					Thread.sleep(5000);
					System.out.println("Status : File Listening started");
					new FileObserver();					
				}catch(InterruptedException ie)
				{
					FileLogger.log(ie.toString(),"error");
	
				}
			}
		};
		
		Thread t3 = new Thread() 
		{
			@Override
			public void run ()
			{
				try
				{
					while(true)
					{		
						if(UploadFilesCollection.getAll().isEmpty())
						{
							System.out.println("Status : ...Waiting to send file");
						}
						else {
							try {
								runsend(ufc,ufnc);
							} catch (Exception e) {
								FileLogger.log(e.toString(),"error");

							}
						}
						Thread.sleep(5000);
					}										
				}catch(InterruptedException ie)
				{
					FileLogger.log(ie.toString(),"error");

				}catch(IndexOutOfBoundsException iob)
				{
					FileLogger.log(iob.toString(),"error");
				}
//				runsend(ini,sftp,ufc,ufnc);
			}
		};
		
		t1.setName("CONNECTION");
		t2.setName("LISTENER");
		t3.setName("SEND MOVE DELETE");
		
		t1.join();
		t2.join();
		t3.join();
		
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.NORM_PRIORITY);
		
		t1.start();
		t2.start();
		t3.start();
		
	}
}
