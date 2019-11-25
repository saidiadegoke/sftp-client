package com.flexipgroup.app.cipher;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

public class Config extends Wini{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5808385729899976199L;
	
	private static String iniPath = System.getProperty("user.home")+File.separator+"/Documents/flexware/sftp-client/config.ini";
	
	private static Config config;

	private Config ()
	{
		super();
		try {
			config = (Config) new Wini(new File(iniPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Config getInstance () {
		return config;
	}
	
}
