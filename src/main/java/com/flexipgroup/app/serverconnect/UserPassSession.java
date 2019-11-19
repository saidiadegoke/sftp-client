package com.flexipgroup.app.serverconnect;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class UserPassSession {
	
	public Session createSession(JSch jsch, String knownHosts, String username, String password, String host, int port) throws Exception
	{
		
		jsch.setKnownHosts(knownHosts);
		
		Session session = jsch.getSession(username, host,port);
		session.setPassword(password);
		
		return session;
		
	}

}
