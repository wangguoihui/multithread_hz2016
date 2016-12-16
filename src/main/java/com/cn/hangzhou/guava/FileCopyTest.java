package com.cn.hangzhou.guava;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;

public class FileCopyTest {

	 public static void demoSimpleFileCopy(String sourceFileName, String targetFileName) {
	      File sourceFile = new File(sourceFileName);
	      File targetFile = new File(targetFileName);
	      Long start = System.currentTimeMillis();
	      try {
	    	  System.out.println("Starting:"+start);
	          Files.copy(sourceFile, targetFile);
	          System.out.println("ended,time used:"+(System.currentTimeMillis() - start));
	      }catch (IOException fileIoEx){
	         System.out.println(
	              "ERROR trying to copy file '" + sourceFileName
	            + "' to file '" + targetFileName + "' - " + fileIoEx.toString());
	      }
	 }
	 
	 public static void main(String args[]) {
		 String sourceFileName = "C:/Users/Administrator/Pictures/五月天《第二人生》..mp4";
		 String targetFileName = "D:/My Movie/五月天《第二人生》..mp4";
		 demoSimpleFileCopy(sourceFileName,targetFileName);
	 }
}
