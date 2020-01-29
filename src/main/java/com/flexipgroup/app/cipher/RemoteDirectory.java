package com.flexipgroup.app.cipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class RemoteDirectory {

	private static String listRemoteDirectory (ChannelSftp sftp,String remoteDirectory)throws SftpException
	{
		sftp.cd(remoteDirectory);
		LsEntry ent = null;
		
		Vector<?> filelist = sftp.ls(remoteDirectory);
		for(int i = 0; i < filelist.size(); i++)
		{
			//System.out.println(filelist.get(i).toString());
		}
		
		List <String>flNmLst = new ArrayList<String>();
		
		for(int j = 0; j < filelist.size(); j++ )
		{
			ent = (LsEntry) filelist.get(j);
			
			SftpATTRS attr = ent.getAttrs();
			
			if(!attr.isDir() && !attr.isLink())
			{
				flNmLst.add(ent.getFilename());							
				//System.out.println("File : " + ent.getFilename());												
			}												
		}
		return ent.getFilename();
	}
	
	
	public static String getListRemoteDirectory (ChannelSftp sftp,String remoteDirectory)throws SftpException
	{
		return listRemoteDirectory (sftp,remoteDirectory);
	}
	
	
}
