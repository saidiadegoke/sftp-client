package com.flexipgroup.app.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.flexipgroup.app.log.FileLogger;

/**
 * 
 * TODO
 * 
 * Implement the encrypt() and decrypt() methods on this utility class
 *
 */

public class Crypto
{
	ConfigurationFile config = new ConfigurationFile();
    /**
     * create an object with just the passphrase from the user. Don't do anything else yet 
     * @param password
     */
    public Crypto (String password)
    {
		ConfigurationFile config = new ConfigurationFile();
        config.mPassword = password;
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
     * this must be called after creating the initial Crypto object. It creates a salt of SALT_LEN bytes
     * and generates the salt bytes using secureRandom().  The encryption secret key is created 
     * along with the initialization vectory. The member variable mEcipher is created to be used
     * by the class later on when either creating a CipherOutputStream, or encrypting a buffer
     * to be written to disk.
     *  
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidParameterSpecException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public void setupEncrypt () throws NoSuchAlgorithmException, 
                                                           InvalidKeySpecException, 
                                                           NoSuchPaddingException, 
                                                           InvalidParameterSpecException, 
                                                           IllegalBlockSizeException, 
                                                           BadPaddingException, 
                                                           UnsupportedEncodingException, 
                                                           InvalidKeyException
    {
        SecretKeyFactory factory = null;
        SecretKey tmp = null;

        // crate secureRandom salt and store  as member var for later use
         config.mSalt = new byte [config.SALT_LEN];
        SecureRandom rnd = new SecureRandom ();
        rnd.nextBytes (config.mSalt);
        Db ("generated salt :" + Hex.encodeHexString (config.mSalt));

        factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        /* Derive the key, given password and salt. 
         * 
         * in order to do 256 bit crypto, you have to muck with the files for Java's "unlimted security"
         * The end user must also install them (not compiled in) so beware. 
         * see here:  http://www.javamex.com/tutorials/cryptography/unrestricted_policy_files.shtml
         */
        KeySpec spec = new PBEKeySpec (config.mPassword.toCharArray (), config.mSalt, config.ITERATIONS, config.KEYLEN_BITS);
        tmp = factory.generateSecret (spec);
        SecretKey secret = new SecretKeySpec (tmp.getEncoded(), "AES");

        /* Create the Encryption cipher object and store as a member variable
         */
        config.mEcipher = Cipher.getInstance ("AES/CBC/PKCS5Padding");
        config.mEcipher.init (Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = config.mEcipher.getParameters ();

        // get the initialization vectory and store as member var 
        config.mInitVec = params.getParameterSpec (IvParameterSpec.class).getIV();

        Db ("mInitVec is :" + Hex.encodeHexString (config.mInitVec));
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
     */
    public void setupDecrypt (String initvec, String salt) throws NoSuchAlgorithmException, 
                                                                                       InvalidKeySpecException, 
                                                                                       NoSuchPaddingException, 
                                                                                       InvalidKeyException, 
                                                                                       InvalidAlgorithmParameterException, 
                                                                                       DecoderException
    {
        SecretKeyFactory factory = null;
        SecretKey tmp = null;
        SecretKey secret = null;

        // since we pass it as a string of input, convert to a actual byte buffer here
        config.mSalt = Hex.decodeHex (salt.toCharArray ());
       Db ("got salt " + Hex.encodeHexString (config.mSalt));

        // get initialization vector from passed string
        config.mInitVec = Hex.decodeHex (initvec.toCharArray ());
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
        config.mDecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        config.mDecipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(config.mInitVec));
    }


    /**
     * This is where we write out the actual encrypted data to disk using the Cipher created in setupEncrypt().
     * Pass two file objects representing the actual input (cleartext) and output file to be encrypted.
     * 
     * there may be a way to write a cleartext header to the encrypted file containing the salt, but I ran
     * into uncertain problems with that. 
     *  
     * @param input - the cleartext file to be encrypted
     * @param output - the encrypted data file
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public void WriteEncryptedFile (File input, File output) throws 
                                                                                          IOException, 
                                                                                          IllegalBlockSizeException, 
                                                                                          BadPaddingException
    {
        FileInputStream fin;
        FileOutputStream fout;
        long totalread = 0;
        int nread = 0;
        byte [] inbuf = new byte [config.MAX_FILE_BUF];

        fout = new FileOutputStream (output);
        fin = new FileInputStream (input);

        while ((nread = fin.read (inbuf)) > 0 )
        {
            Db ("read " + nread + " bytes");
            totalread += nread;

            // create a buffer to write with the exact number of bytes read. Otherwise a short read fills inbuf with 0x0
            // and results in full blocks of MAX_FILE_BUF being written. 
            byte [] trimbuf = new byte [nread];
            for (int i = 0; i < nread; i++)
                trimbuf[i] = inbuf[i];

            // encrypt the buffer using the cipher obtained previosly
            byte [] tmp = config.mEcipher.update (trimbuf);

            // I don't think this should happen, but just in case..
            if (tmp != null)
                fout.write (tmp);
        }

        // finalize the encryption since we've done it in blocks of MAX_FILE_BUF
        byte [] finalbuf = config.mEcipher.doFinal ();
        if (finalbuf != null)
            fout.write (finalbuf);

        fout.flush();
        fin.close();
        fout.close();

        Db ("wrote " + totalread + " encrypted bytes");
    }


    /**
     * Read from the encrypted file (input) and turn the cipher back into cleartext. Write the cleartext buffer back out
     * to disk as (output) File.
     * 
     * I left CipherInputStream in here as a test to see if I could mix it with the update() and final() methods of encrypting
     *  and still have a correctly decrypted file in the end. Seems to work so left it in.
     *  
     * @param input - File object representing encrypted data on disk 
     * @param output - File object of cleartext data to write out after decrypting
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public void ReadEncryptedFile (File input, File output) throws 
                                                                                                                                            IllegalBlockSizeException, 
                                                                                                                                            BadPaddingException, 
                                                                                                                                            IOException
    {
        FileInputStream fin; 
        FileOutputStream fout;
        CipherInputStream cin;
        long totalread = 0;
        int nread = 0;
        byte [] inbuf = new byte [config.MAX_FILE_BUF];

        fout = new FileOutputStream (output);
        fin = new FileInputStream (input);

        // creating a decoding stream from the FileInputStream above using the cipher created from setupDecrypt()
        cin = new CipherInputStream (fin, config.mDecipher);

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

        Db ("wrote " + totalread + " encrypted bytes");
    }


    /**
     * adding main() for usage demonstration. With member vars, some of the locals would not be needed
     */
    public static void main1(String [] args)
    {

        // create the input.txt file in the current directory before continuing
        File input = new File ("input.txt");
        File eoutput = new File ("encrypted.aes");
        File doutput = new File ("decrypted.txt");
        String iv = null;
        String salt = null;
        Crypto en = new Crypto ("mypassword");

        /*
         * setup encryption cipher using password. print out iv and salt
         */
        try
      {
          en.setupEncrypt ();
          iv = Hex.encodeHexString (en.getInitVec ()).toUpperCase ();
          salt = Hex.encodeHexString (en.getSalt ()).toUpperCase ();
      }
      catch (InvalidKeyException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (NoSuchAlgorithmException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (InvalidKeySpecException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (NoSuchPaddingException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (InvalidParameterSpecException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (IllegalBlockSizeException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (BadPaddingException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (UnsupportedEncodingException e)
      {
			FileLogger.log(e.toString(),"error");
      }

        /*
         * write out encrypted file
         */
        try
      {
          en.WriteEncryptedFile (input, eoutput);
          System.out.printf ("File encrypted to " + eoutput.getName () + "\niv:" + iv + "\nsalt:" + salt + "\n\n");
      }
      catch (IllegalBlockSizeException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (BadPaddingException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (IOException e)
      {
			FileLogger.log(e.toString(),"error");
      }


        /*
         * decrypt file
         */
        Crypto dc = new Crypto ("mypassword");
        try
      {
          dc.setupDecrypt (iv, salt);
      }
      catch (InvalidKeyException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (NoSuchAlgorithmException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (InvalidKeySpecException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (NoSuchPaddingException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (InvalidAlgorithmParameterException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (DecoderException e)
      {
			FileLogger.log(e.toString(),"error");
      }

    /*
     * write out decrypted file
     */
      try
      {
          dc.ReadEncryptedFile (eoutput, doutput);
          System.out.println ("decryption finished to " + doutput.getName ());
      }
      catch (IllegalBlockSizeException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (BadPaddingException e)
      {
			FileLogger.log(e.toString(),"error");
      }
      catch (IOException e)
      {
			FileLogger.log(e.toString(),"error");
      }
   }


}
