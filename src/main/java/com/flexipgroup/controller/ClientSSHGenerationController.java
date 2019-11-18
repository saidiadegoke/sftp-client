package com.flexipgroup.controller;

import org.springframework.stereotype.Controller;

import com.flexipgroup.service.ClientSSHGenerationService;

@Controller
public class ClientSSHGenerationController { 
	
	public ClientSSHGenerationController()
	{
		new ClientSSHGenerationService();
		System.out.println("running");
	}
	

}
