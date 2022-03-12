package cn.sparrow.permission.mgt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class FileUtil {

  public static String getChecksum(InputStream file) {
    
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    // Get file input stream for reading the file content
    InputStream fis = file;
//    try {
//      fis = new FileInputStream(file);
//    } catch (FileNotFoundException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }

    // Create byte array to read data in chunks
    byte[] byteArray = new byte[1024];
    int bytesCount = 0;

//     Read file data and update in message digest
    try {
      while ((bytesCount = fis.read(byteArray)) != -1) {
        digest.update(byteArray, 0, bytesCount);
        
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } ;

    // close the stream; We don't need it now.
     try {
      fis.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Get the hash's bytes


    byte[] bytes = digest.digest();

    // This bytes[] has bytes in decimal format;
    // Convert it to hexadecimal format
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }

    return sb.toString();
  }

}
