package com.flexipgroup.app.cipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.Wini;

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * TODO
 * 
 * Implement this class to connect and send files to the SFTP server.
 *
 */
public class SFTPAgent {
	
	private String filePath;
	private static String password;
	private static String username;
	private static String remoteHost;
	private static String remotePath;
	
	public SFTPAgent(String filePath, ConfigurationFile config) {
		this.filePath = filePath;
		this.username = config.SFTP_USERNAME;
		this.password = config.SFTP_PASSWORD;
		this.remoteHost = config.SFTP_HOSTNAME;
		this.remotePath = config.SFTP_REMOTE_PATH;
	}
	
	
	
	public static ChannelSftp connect() throws Exception {
		
		String knownHosts = System.getProperty("user.home") + File.separatorChar + ".ssh/known_hosts";
		
		JSch jsch = new JSch();
		jsch.setKnownHosts(knownHosts);
		
		Session session = jsch.getSession(username, remoteHost);
		session.setPassword(password);
		
		session.connect();
		System.out.println(session.isConnected());

		return (ChannelSftp) session.openChannel("sftp");		

	}
	
	public ChannelSftp setupJsch() throws JSchException {
	    JSch jsch = new JSch();
	    String knownHosts = System.getProperty("user.home") + File.separatorChar + ".ssh/known_hosts";
	    jsch.setKnownHosts(knownHosts);
	    // System.getProperty("user.home") + File.separator + "known_hosts"
	    //jsch.setKnownHosts("/Users/saidiadegoke/known_hosts");
	    System.out.println(remoteHost);
	    Session jschSession = jsch.getSession(username, remoteHost);
	    
	    java.util.Properties config = new java.util.Properties(); 
	    config.put("StrictHostKeyChecking", "no");
	    jschSession.setConfig(config);
	    
	    jschSession.setPassword(password);
	    jschSession.connect();
	    return (ChannelSftp) jschSession.openChannel("sftp");
	}
	
	public void upload() throws Exception {
		//RegisterKnownHosts.getInstance(remoteHost);
	    ChannelSftp channelSftp = setupJsch();
	    channelSftp.connect();
	    
	    File f = new File(filePath);
	  
	    //String remoteDir = remotePath + File.separator + "upload" + File.separator + f.getName();
	    String remoteDir = remotePath + "/" + "upload" + "/" + f.getName();
	    //prepareUpload(channelSftp, remoteDir, false);
	   
	    System.out.println(remoteDir);
	    channelSftp.put(filePath, remoteDir);
	  
	    channelSftp.exit();
	}
	
	public static void send(ChannelSftp sftp, String filePath) {
		try {
			// TODO
			
			String remotefilePath = remotePath;
//			System.out.println("REMOTE FILE PATH: "+remotefilePath);
//			System.out.println("FILEPAth from sftpagent : "+filePath);
//			
//			System.out.println("SERVER CONNECTED : "+sftp.isConnected());
			
			//Upload.getUpload(filePath, remotefilePath, sftp,FileUploadProgress.getMonitorProgress());
			
		} catch (Exception e) {
			FileLogger.log(e.toString(),"error");
		}
	}
	
	public boolean prepareUpload(
			  ChannelSftp sftpChannel,
			  String userId,
			  String[] paths,
			  boolean overwrite) {

			  boolean result = false;
			  
			  //String[] folders = path.split("/");
			  String basedir = userId;
			  System.out.println(basedir);

			      try {
			        sftpChannel.cd(basedir);
			      } catch (SftpException e) {
						FileLogger.log(e.toString(),"error");
			    	  try {
						sftpChannel.mkdir(basedir);
					} catch (SftpException e3) {
						FileLogger.log(e3.toString(),"error");

					}
			    	  try {
						sftpChannel.cd(basedir);
						try {
							for(String path: paths) {
								if(path != null && !path.isEmpty()) {
									try {
										sftpChannel.cd(path);
									} catch (SftpException e2) {
										FileLogger.log(e2.toString(),"error");
										sftpChannel.mkdir(path);
									}
								}
							}
							//sftpChannel.cd(folder);
						} catch (SftpException e1) {
							FileLogger.log(e1.toString(),"error");

						}
					} catch (SftpException e3) {
						FileLogger.log(e3.toString(),"error");

					}
			        
			        
			      }
			    
			  

			  // Build romote path subfolders inclusive:
		/*
		 * String[] folders = path.split("/"); for (String folder : folders) { if
		 * (folder.length() > 0 && !folder.contains(".")) { // This is a valid folder:
		 * try { sftpChannel.cd(folder); } catch (SftpException e) { // No such folder
		 * yet: try { sftpChannel.mkdir(folder); //sftpChannel.cd(folder); } catch
		 * (SftpException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * }
		 * 
		 * } } }
		 */

			  // Folders ready. Remove such a file if exists: 
		/*
		 * try { if (sftpChannel.ls(path).size() > 0) { if (!overwrite) {
		 * System.out.println( "Error - file " + path + " was not created on server. " +
		 * "It already exists and overwriting is forbidden."); } else { // Delete file:
		 * sftpChannel.ls(path); // Search file. sftpChannel.rm(path); // Remove file.
		 * result = true; } } else { // No such file: result = true; } }
		 * catch(SftpException e) { e.printStackTrace(); }
		 */

			  return result;
			}
	
}
	

