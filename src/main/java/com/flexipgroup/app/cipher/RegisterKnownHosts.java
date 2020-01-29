package com.flexipgroup.app.cipher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class RegisterKnownHosts {
	
	private RegisterKnownHosts () {}
	
	public static RegisterKnownHosts getInstance(String host)throws Exception
	{
		return new RegisterKnownHosts(host);
	}
	
	
	private RegisterKnownHosts(String host) throws Exception
	{		
		executeShellCommand (host);
	}
	
	
//	private static void registerKnownHosts (String host)throws Exception 
//	{
//		if((Objects.equals(null, host)) || (Objects.equals("", host)))
//		{
//			System.out.println("hostname not provided");
//			getHost(host);
//		}else
//		{
//			executeShellCommand (host);
//		}
//	}
	
	
	private static void executeShellCommand (String hostname)throws Exception
	{
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		//Run a shell command
		processBuilder.command("bash", "-c", "ssh-keyscan "+hostname+" >> ~/.ssh/known_hosts");
		//chmod 700 ~/.ssh && chmod 600 ~/.ssh/authorized_keys
		//scp ~/.ssh/id_rsa.pub your_username@hostname:~/.ssh/authorized_keys
		
		Process process = processBuilder.start();
			
		StringBuilder output = new StringBuilder();
			
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String line;int count = 0;
		
		while((line = reader.readLine())!= null)
		{
			output.append(line);
		}
				
		int exitVal = process.waitFor();
			
		if(exitVal == 0)
		{
//			System.out.println("Success!");
//			System.out.println(output);
//			System.out.println(count);
			//System.exit(0);
		}
		
	}
	
	
	
}
