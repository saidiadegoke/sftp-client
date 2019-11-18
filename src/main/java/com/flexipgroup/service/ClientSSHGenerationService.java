package com.flexipgroup.service;
import org.springframework.stereotype.Service;

@Service
public class ClientSSHGenerationService {

	
	public ClientSSHGenerationService ()
	{
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		System.out.println(System.getProperty("os.name"));
		System.out.println(isWindows);
	}
	
}
