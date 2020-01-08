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
	public String SFTP_USERNAME;
	public String SFTP_HOSTNAME;
	public String SFTP_REMOTE_PATH;
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
    public String DOWNLOAD_FOLDER;
    public String READ_FOLDER;
    public String ARCHIVE_FOLDER;
    public String ERROR_FOLDER;
    public String SUCCESS_FOLDER;
    public String CUSTOMER_ID;
    public String SECRET_KEY;
	public boolean CREATE_REMOTE_FOLDERS;
	public String FILE_EXTENSION;
	public String RECEIVER_FOLDER;
	public String RECEIVER_EXTENSION;
	public String UPLOAD_FOLDER;
    
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
        SFTP_USERNAME = ini.get("sftp", "username");
        SFTP_HOSTNAME = ini.get("sftp",  "host");
        SFTP_REMOTE_PATH = ini.get("path", "basedir") + "/" + ini.get("sftp", "remotefilePath");
        MAIN_POLL_INTERVAL = ini.get("app", "main_poll_interval", int.class);
        BASEPATH = System.getProperty("user.home") + File.separatorChar + ini.get("path", "basepath");
        MAX_FILE_BUF = ini.get("crypto", "MAX_FILE_BUF", int.class);
        SALT_LEN = ini.get("crypto", "SALT_LEN", int.class);
        mPassword = ini.get("crypto", "mPassword");
        ITERATIONS = ini.get("crypto","ITERATIONS",int.class);
        KEYLEN_BITS = ini.get("crypto","KEYLEN_BITS",int.class);
        //mDecipher = ini.get("crypto","mDecipher",Cipher.class);
        //mEcipher = ini.get("crypto","mEcipher",Cipher.class);
        //mSalt = ini.get("crypto", "mSalt", byte[].class);
        //mInitVec = ini.get("crypto","mInitVec",byte[].class);
        DOWNLOAD_FOLDER = ini.get("path", "download");
        READ_FOLDER = ini.get("path", "read");
        ARCHIVE_FOLDER = ini.get("path", "archive");
        UPLOAD_FOLDER = ini.get("path", "upload");
        ERROR_FOLDER = ini.get("path", "error");
        SUCCESS_FOLDER = ini.get("path", "success");
        CUSTOMER_ID = ini.get("app", "customerID");
        SECRET_KEY = ini.get("app", "secretKey");
        CREATE_REMOTE_FOLDERS = ini.get("app", "createRemoteFolders", boolean.class);
        FILE_EXTENSION = ini.get("app", "fileExtension");
        RECEIVER_FOLDER = ini.get("path", "receiver");
        RECEIVER_EXTENSION = FILE_EXTENSION + "." + ini.get("app", "receiverExtension");
	}

}
