package com.flexipgroup.app.cipher;

import java.io.File;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpProgressMonitor;

public class Upload {
	
	private static void upload (String file, String remoteDirectory, ChannelSftp sftp, SftpProgressMonitor progress) 
	{
			
			try {
				if(!(file == ""  || file.isEmpty()) && !(remoteDirectory == "" || remoteDirectory.isEmpty())) {									
					
					//list remote directory
					System.out.println(RemoteDirectory.getListRemoteDirectory(sftp, remoteDirectory));
					String files = System.getProperty("user.home") + File.separator + "ubaclient/upload/attachment.svg";
					System.out.println("SFTP : "+sftp.isConnected());
					sftp.put(file, remoteDirectory, progress);				
				}	
				
			}catch(Exception e) {
				System.out.println("Upload file not found: "+ file);
				e.printStackTrace();
			}
			
		}
	
	private static void upload (String file, String remoteDirectory, ChannelSftp sftp) 
	{
			
			try {
				if(!(file == ""  || file.isEmpty()) && !(remoteDirectory == "" || remoteDirectory.isEmpty())) {									
					
					//list remote directory
					System.out.println(RemoteDirectory.getListRemoteDirectory(sftp, remoteDirectory));
					
					sftp.put(file, remoteDirectory);				
				}	
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
	public static void getUpload (String file, String remote, ChannelSftp sftp, SftpProgressMonitor progress)
	{
		upload (file, remote, sftp, progress);
	}
	
	public static void getUpload (String file, String remote, ChannelSftp sftp)
	{
		upload (file, remote, sftp);
	}
	
}
