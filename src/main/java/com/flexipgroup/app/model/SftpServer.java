package com.flexipgroup.app.model;

import java.io.File;
import java.security.PublicKey;

import lombok.Data;

@Data
public class SftpServer {
	
	private PublicKey publicKey;
	
	private File clientFileDestination;

}
