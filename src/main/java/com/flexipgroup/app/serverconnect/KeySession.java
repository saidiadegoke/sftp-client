package com.flexipgroup.app.serverconnect;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class KeySession {
	
	public Session createSession(JSch jsch, String knownHosts, String privateKey, String passPhrase, String username, String host, int port) throws Exception
	{
		jsch.setKnownHosts(knownHosts);			
		
		byte [] pass = passPhrase.getBytes();
		
		jsch.addIdentity(privateKey,pass);
						
		return jsch.getSession(username, host, port);		
		
	}

}
