package com.flexipgroup.app.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.flexipgroup.app.config.ConfigurationFile;

public class FileManager {
	
	/**
	 * TODO
	 * 
	 * Implement moveFile() method
	 */
	public static void moveFile(String sourceFile, String destinationFile) {
		try {
			Files.move(Paths.get(sourceFile), Paths.get(destinationFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO
	 * 
	 * Implement copyFile() method
	 */
	public static void copyFile(String sourceFile, String destinationFile) {
		try {
			Files.copy(Paths.get(sourceFile), Paths.get(destinationFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * TODO
	 * 
	 * Implement deleteFile() method
	 */
	public void deleteFile(File file) {
		
	}
}
