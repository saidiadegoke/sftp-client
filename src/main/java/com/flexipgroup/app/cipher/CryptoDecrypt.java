package com.flexipgroup.app.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.flexipgroup.app.config.ConfigurationFile;

/**
 * 
 * TODO
 * 
 * Implement the encrypt() and decrypt() methods on this utility class
 *
 */

public class CryptoDecrypt
{
    String mPassword = null;
    byte [] mInitVec = null;
    byte [] mSalt = null;
    Cipher mEcipher = null;
    Cipher mDecipher = null;
	private String inFilePath; 
	private String outFilePath;
	
	ConfigurationFile config = new ConfigurationFile(); 

    /**
     * create an object with just the passphrase from the user. Don't do anything else yet 
     * @param password
     */
    public CryptoDecrypt (String password, String inFilePath, String outFilePath)
    {
    	ConfigurationFile config = new ConfigurationFile();
        this.config.mPassword = password;
        this.inFilePath = inFilePath;
        this.outFilePath = outFilePath;
    }

    /**
     * return the generated salt for this object
     * @return
     */
    public byte [] getSalt ()
    { 
        return (config.mSalt);
    }

    /**
     * return the initialization vector created from setupEncryption
     * @return
     */
    public byte [] getInitVec ()
    {
        return (config.mInitVec);
    }

    /**
     * debug/print messages
     * @param msg
     */
    private void Db (String msg)
    {
        System.out.println ("** Crypt ** " + msg);
    }

    /**
     * If a file is being decrypted, we need to know the pasword, the salt and the initialization vector (iv). 
     * We have the password from initializing the class. pass the iv and salt here which is
     * obtained when encrypting the file initially.
     *   
     * @param initvec
     * @param salt
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws DecoderException
     * @throws IOException 
     */
    public void decrypt () throws NoSuchAlgorithmException, 
                                                                                       InvalidKeySpecException, 
                                                                                       NoSuchPaddingException, 
                                                                                       InvalidKeyException, 
                                                                                       InvalidAlgorithmParameterException, 
                                                                                       DecoderException, IOException
    {
    	//String initvec;
    	//String salt;
        SecretKeyFactory factory = null;
        SecretKey tmp = null;
        SecretKey secret = null;
        byte[] initvec = new byte[32];
        byte[] salt = new byte[16];
        File input = new File(inFilePath);
    	File output = new File(outFilePath);
        FileInputStream fin; 
        FileOutputStream fout;
        CipherInputStream cin;
        long totalread = 0;
        int nread = 0;
        byte [] inbuf = new byte [config.MAX_FILE_BUF];
        

        fout = new FileOutputStream (output);
        fin = new FileInputStream (input);
        
        //ByteBuffer byteBuffer = ByteBuffer.wrap(fin);
        
        fin.read(initvec);
        fin.read(salt);

        // since we pass it as a string of input, convert to a actual byte buffer here
        config.mSalt = Hex.decodeHex (new String(salt).toCharArray ());
       Db ("got salt " + Hex.encodeHexString (config.mSalt));

        // get initialization vector from passed string
        config.mInitVec = Hex.decodeHex (new String(initvec).toCharArray ());
        Db ("got initvector :" + Hex.encodeHexString (config.mInitVec));


        /* Derive the key, given password and salt. */
        // in order to do 256 bit crypto, you have to muck with the files for Java's "unlimted security"
        // The end user must also install them (not compiled in) so beware. 
        // see here: 
      // http://www.javamex.com/tutorials/cryptography/unrestricted_policy_files.shtml
        factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(config.mPassword.toCharArray (), config.mSalt, config.ITERATIONS, config.KEYLEN_BITS);

        tmp = factory.generateSecret(spec);
        secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        /* Decrypt the message, given derived key and initialization vector. */
        mDecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        mDecipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(config.mInitVec));
        
     // creating a decoding stream from the FileInputStream above using the cipher created from setupDecrypt()
        cin = new CipherInputStream (fin, mDecipher);

        while ((nread = cin.read (inbuf)) > 0 )
        {
            Db ("read " + nread + " bytes");
            totalread += nread;

            // create a buffer to write with the exact number of bytes read. Otherwise a short read fills inbuf with 0x0
            byte [] trimbuf = new byte [nread];
            for (int i = 0; i < nread; i++)
                trimbuf[i] = inbuf[i];

            // write out the size-adjusted buffer
            fout.write (trimbuf);
        }

        fout.flush();
        cin.close();
        fin.close ();       
        fout.close();
    }

}

