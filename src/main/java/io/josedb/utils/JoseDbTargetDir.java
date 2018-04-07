package io.josedb.utils;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

public class JoseDbTargetDir {
	
	public static boolean checkTargetDirectory(){
	    String targetDir = JoseDbInfo.getConfig().get("targetDir");
	    String configFile = JoseDbInfo.getConfig().get("configFile");
		String databaseFile = JoseDbInfo.getConfig().get("databaseFile");
		String indexFile = JoseDbInfo.getConfig().get("databaseIndexFile");
	    
	    if(Files.isDirectory(Paths.get(targetDir), LinkOption.NOFOLLOW_LINKS)){
	    	// Check existence of logs folder
	    	if(!Files.exists(Paths.get(targetDir, "logs"), LinkOption.NOFOLLOW_LINKS)) {
	    		System.err.println(String.format("Couldn't find a folder [%s] in this target.", "logs"));
	    		return false;
	    	}
	    	
	    	// Check existence of config file
	    	if(!Files.exists(Paths.get(targetDir, configFile), LinkOption.NOFOLLOW_LINKS)){
	    		System.err.println(String.format("Couldn't find a file [%s] in this target.", configFile));
	    		return false;
	    	}
	    	
	    	// Check existence of database file
//	    	if(!Files.exists(Paths.get(targetDir, "data", databaseFile), LinkOption.NOFOLLOW_LINKS)) {
//	    		System.err.println(
//	    				String.format("Couldn't find a file (data/%s) in this target.", databaseFile));
//	    		return false;
//	    	}
//	    	
//	    	// Check existence of index file
//	    	if(!Files.exists(Paths.get(targetDir, "indexes", indexFile), LinkOption.NOFOLLOW_LINKS)) {
//	    		System.err.println(
//	    				String.format("Couldn't find a file (indexes/%s) in this target.", indexFile));
//	    		return false;
//	    	}

	    	return true;
	    }
	    
	    // Create target directory structure
	    try {
	    	// Create folders
	    	Files.createDirectories(Paths.get(targetDir, "logs"));
	    	Files.createDirectories(Paths.get(targetDir, "data"));
	    	Files.createDirectories(Paths.get(targetDir, "indexes"));
	    	// Create files
	    	Files.createFile(Paths.get(targetDir, configFile));
	    	Files.createFile(Paths.get(targetDir, "data", databaseFile));
	    	Files.createFile(Paths.get(targetDir, "indexes", indexFile));

	    	return true;
		} catch (Exception e) {
			System.err.println("Couldn't initialize database folder structure in this target.");
    		return false;
		}
	}

}
