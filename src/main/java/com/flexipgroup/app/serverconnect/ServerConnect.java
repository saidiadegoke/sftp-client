package com.flexipgroup.app.serverconnect;

import com.flexipgroup.app.model.SftpClient;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ServerConnect {
	
	public void connect (Session session, JSch jsch, String knownHosts, String privateKey, String passPhrase, String username, String host, int port, boolean isConnected,ChannelSftp sftp, String message, 
			KeySession keySession,Connection connection, SftpClient clientDetails,RemoteDirectory remoteDirectory) throws Exception 
	{
		session = keySession.createSession( jsch, knownHosts, privateKey, passPhrase, username, host, port);
		
		ClientChannelSftp.sf = connection.openConnection(session , sftp,"/tmp/");
		
		isConnected = ClientChannelSftp.sf.isConnected();
		
		clientDetails.setConnected(isConnected);
		
		message = remoteDirectory.getRemotedirectory(ClientChannelSftp.sf, "/tmp/");
		
		clientDetails.setMessage(message);
		
	}
	
	public void connect(Session session,boolean isConnected,String message,JSch jsch,String knownHosts, String username, String password, String host, int port,ChannelSftp sftp, UserPassSession userPassSession,
			Connection connection,SftpClient clientDetails,RemoteDirectory remoteDirectory) throws Exception
	{
		session = userPassSession.createSession(jsch, knownHosts, username, password, host, port);	
			
		ClientChannelSftp.sf = connection.openConnection(session , sftp,"/tmp/");			
		isConnected = ClientChannelSftp.sf.isConnected();			
		clientDetails.setConnected(isConnected);			
		message = remoteDirectory.getRemotedirectory(ClientChannelSftp.sf, "/tmp/");			
		clientDetails.setMessage(message);
	}

}
