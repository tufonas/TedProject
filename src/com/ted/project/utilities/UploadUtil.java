package com.ted.project.utilities;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

public class UploadUtil {
	
	
	public UploadUtil() {
	}
	
	public static void fileUpload(InputStream fileContent,String outputPath,String id) {

  	  try {

  		  // creating the directory succeeded

  		  File targetFile = new File(outputPath);
  		  FileUtils.copyInputStreamToFile(fileContent, targetFile);
		
	} catch (Exception e) {
		// creating the directory failed
		e.printStackTrace();
	}

		
	}

}
