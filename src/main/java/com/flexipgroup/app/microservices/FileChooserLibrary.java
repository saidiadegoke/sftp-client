package com.flexipgroup.app.microservices;

import java.io.File;
import java.io.FileFilter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserLibrary {
	
	public File openFileLocation(String msg)
	{
		File file = null;
		JFileChooser jf = new JFileChooser(msg);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","xls","xlsx","txt");
		jf.setFileFilter(filter);
		
		if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			file = jf.getSelectedFile();
		}
		
		System.out.println("KeyStore Location : " + file);
		return file;
	}
	
	public File openFolderLocation(String msg)
	{
		File file = null;
		JFileChooser jf = new JFileChooser(msg);
		
		FileFilter filter = new FileFilter() {
			public boolean accept(File file)
			{
				return file.isDirectory();
			}
		};
		
		jf.setFileFilter((javax.swing.filechooser.FileFilter) filter);
		
		if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			file = jf.getSelectedFile();
		}
		
		System.out.println("KeyStore Location : " + file);
		return file;
	}

}
