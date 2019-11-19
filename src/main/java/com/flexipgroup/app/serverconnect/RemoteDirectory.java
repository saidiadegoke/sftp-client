package com.flexipgroup.app.serverconnect;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpATTRS;

public class RemoteDirectory {
	
private String firstMessage="";
	
	private String secondMessage ="";
	
	public String getRemotedirectory(ChannelSftp sftp, String remote) throws Exception
	{
		
		
		sftp.cd(remote);
		
		Vector filelist = sftp.ls(remote);
		for(int i = 0; i < filelist.size(); i++)
		{
			firstMessage += (filelist.get(i).toString())+"\n";
		}
		
		List flNmLst = new ArrayList();
		
		for(int j = 0; j < filelist.size(); j++ )
		{
			LsEntry ent = (LsEntry) filelist.get(j);
			
			SftpATTRS attr = ent.getAttrs();
			
			if(!attr.isDir() && !attr.isLink() )
			{
				flNmLst.add(ent.getFilename());
				
				secondMessage += (ent.getFilename()+"; " )+ " ";
				
			}
			
			
			
		}
		return "Remote File Contents : " + secondMessage;
		
	}

}
