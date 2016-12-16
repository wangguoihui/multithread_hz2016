package com.cn.hangzhou.guava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.google.common.io.Files;

/**
 *  System.out.println(Files.getNameWithoutExtension(file.getAbsolutePath()));
	System.out.println(Files.getFileExtension(file.getAbsolutePath()));
 * @author Administrator
 *
 */
public class FileTest {

	 public static void demoSimpleFileCopy(String sourceFile,String targetFile) {
		 File file = new File(sourceFile);
		 FileOutputStream fileOutputStream = null;
		 try {
	    	 byte[] content = Files.toByteArray(file);
		     fileOutputStream = new FileOutputStream(targetFile);
	         fileOutputStream.write(content);
	         System.out.println("succeeded!");
		 } catch (IOException e) {
			 e.printStackTrace();
		 } finally{
			 if(fileOutputStream != null){
				 try {
					 fileOutputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
	 }
	 
	 public static void main(String args[]) {
		String sourceFile = "C:/Users/Administrator/Pictures/五月天《第二人生》..mp4";
		String targetFile = "C:/Users/Administrator/Desktop/五月天《第二人生》..mp4";
		demoSimpleFileCopy(sourceFile,targetFile);
	 }
}
