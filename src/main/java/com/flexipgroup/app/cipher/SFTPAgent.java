package com.flexipgroup.app.cipher;

import java.io.File;

import org.ini4j.Wini;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 
 * TODO
 * 
 * Implement this class to connect and send files to the SFTP server.
 *
 */
public class SFTPAgent {
	
	private static String filePath = System.getProperty("user.home")+File.separator+"/sshj/rnmon.mp4";
	
	public SFTPAgent(String filePath) {
		this.filePath = filePath;
	}
	
	
	
	public static ChannelSftp connect(String host,String username, String password) throws JSchException {
		
		String knownHosts = System.getProperty("user.home") + File.separator + ".ssh/known_hosts";
		
		JSch jsch = new JSch();
		jsch.setKnownHosts(knownHosts);
		
		Session session = jsch.getSession(username, host);
		session.setPassword(password);
		
		session.connect();
		System.out.println(session.isConnected());

		return (ChannelSftp) session.openChannel("sftp");		

	}
	
	
	
	public static void send(Wini ini,ChannelSftp sftp, String filePath){
		try {
			// TODO
			
			String remotefilePath = ini.get("sftp", "remotefilePath");
			System.out.println("REMOTE FILE PATH: "+remotefilePath);
			System.out.println("FILEPAth from sftpagent : "+filePath);
			
			System.out.println("SERVER CONNECTED : "+sftp.isConnected());
			
			Upload.getUpload(filePath, remotefilePath, sftp,FileUploadProgress.getMonitorProgress());
			
			
		} catch (Exception e) {
			System.out.println("Not sending");
			e.printStackTrace();
		}
	}
	
}
	

