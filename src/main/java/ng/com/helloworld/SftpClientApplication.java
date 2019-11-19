package ng.com.helloworld;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flexipgroup.app.sshconnect.ClientSSHGeneration;

@SpringBootApplication
public class SftpClientApplication {

	public static void main(String[] args) throws Exception{
		
		//SpringApplication.run(SftpClientApplication.class, args);
		
		new ClientSSHGeneration();
		
//		new AESKeyGeneration().generateAESKey();
		
	}

}
