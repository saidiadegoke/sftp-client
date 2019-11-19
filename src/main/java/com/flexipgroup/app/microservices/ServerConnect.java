package com.flexipgroup.app.microservices;

import java.io.File;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ServerConnect {
	
	public static void main (String [] args)throws Exception
	{
		
		String hostname="192.168.1.245";
		String username = "angerdooshima";
		String password = "   d";
		
		String knownHosts = System.getProperty("user.home") + File.separator + ".ssh/known_hosts";
		int timeout = 8000;
		
		JSch jsch = new JSch();
		jsch.setKnownHosts(knownHosts);
		
		Session session = jsch.getSession(username, hostname);
		session.setPassword(password);
		
		session.connect();
		System.out.println(session.isConnected());
		
		ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
		
		channel.connect(timeout);
		
		System.out.println(channel.isConnected());
		
		
	}

}
