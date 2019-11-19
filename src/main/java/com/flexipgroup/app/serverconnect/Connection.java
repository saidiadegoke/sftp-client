package com.flexipgroup.app.serverconnect;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

public class Connection {
	
RemoteDirectory remoteDirectory = new RemoteDirectory();
	
	
	public ChannelSftp openConnection(Session mSSHSession,ChannelSftp mSSHCnhannel, String remoteDir) throws Exception{
				
		mSSHSession.connect();
		
		mSSHCnhannel = (ChannelSftp) mSSHSession.openChannel("sftp");
		
		mSSHCnhannel.connect();
		
		return mSSHCnhannel;
							
	
	}

}
