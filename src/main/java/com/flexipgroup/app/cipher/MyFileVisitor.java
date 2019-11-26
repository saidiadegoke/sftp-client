package com.flexipgroup.app.cipher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor implements FileVisitor<Path> 
{
	private UploadFilesCollection ufc = new UploadFilesCollection();
	private UploadFileNameCollection ufnc = new UploadFileNameCollection();

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("About to visit "+ dir);		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		if(!file.endsWith(".DS_Store"))
		{
			System.out.println("Currently visiting file "+ file);
			System.out.println("toString : "+ file.toString());
			System.out.println("substring :" + file.toString().substring(29));			
			ufc.add(file.toString());
			ufnc.add(file.toString().substring(29));
			System.out.println("FILE SIZE : "+UploadFilesCollection.getAll().size());
			System.out.println("FILE NAME SIZE : "+UploadFileNameCollection.getAll().size());
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		System.err.println("Just visited "+ exc.getMessage());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Just visited "+ dir);
		return FileVisitResult.CONTINUE;
	}
								
}