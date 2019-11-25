package com.flexipgroup.sender_client;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;

import com.flexipgroup.*;

public class MessagingFile {
	private String url;
	Path path;
	
	MessagingFile(String url) {
		this.url = url;
	}

	public Path getPath(){
		System.out.println(new File(url).exists());
		return Paths.get(url);
	}
	
	public String getUrl() {
		return url;
	}

	public String getExtension() { 
		return null;
	}

	public String getMimeType() throws IOException {
		return Files.probeContentType(getPath());
		 
	}
}
