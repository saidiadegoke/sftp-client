package com.flexipgroup.app.config;

import java.io.File;
import java.io.IOException;

import javax.crypto.Cipher;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.flexipgroup.app.cipher.Crypto;

/**
 * 
 * TODO
 * 
 * Read the config file and populate this file with corresponding fields form the config file;
 *
 */

public class ConfigurationFile {
	
	public String SFTP_PASSWORD;
	public int SFTP_PORT_NUMBER;
	public int MAIN_POLL_INTERVAL;
	public String BASEPATH;
    public int MAX_FILE_BUF;
    public static int SALT_LEN;
    public String mPassword;
    public int ITERATIONS;
    public int KEYLEN_BITS;
    public Cipher mDecipher;
    public byte [] mInitVec;
    public byte [] mSalt;
    public Cipher mEcipher;
    
	public ConfigurationFile() {
		try {
			run("config.ini");
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void run(String filePath) throws InvalidFileFormatException, IOException {
		Wini ini = new Wini(new File(filePath));
        //int port = ini.get("port", "22", int.class);
        //double height = ini.get("owner", "height", double.class);
        SFTP_PASSWORD = ini.get("sftp", "password");
        MAIN_POLL_INTERVAL = ini.get("app", "main_poll_interval", int.class);
        BASEPATH = ini.get("path", "basepath");
        MAX_FILE_BUF = ini.get("crypto", "MAX_FILE_BUF", int.class);
        SALT_LEN = ini.get("crypto", "SALT_LEN", int.class);
        mPassword = ini.get("crypto", "mPassword");
        ITERATIONS = ini.get("crypto","ITERATIONS",int.class);
        KEYLEN_BITS = ini.get("crypto","KEYLEN_BITS",int.class);
        mDecipher = ini.get("crypto","mDecipher",Cipher.class);
        mEcipher = ini.get("crypto","mEcipher",Cipher.class);
        mSalt = ini.get("crypto", "mSalt", byte[].class);
        mInitVec = ini.get("crypto","mInitVec",byte[].class);

	}

}
