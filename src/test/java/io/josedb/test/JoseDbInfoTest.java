package io.josedb.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;
import io.josedb.utils.JoseDbInfo;


public class JoseDbInfoTest {

	@After
	public void reset() throws Exception {
		// Change instance accessibility & reset to null
		Field instance = JoseDbInfo.class.getDeclaredField("instance");
	    instance.setAccessible(true);
		instance.set(null, null);
	}
	
    @Test 
    public void testInitWithDefault() {
    	boolean status = JoseDbInfo.init(new String[]{});
    	assertEquals(true, status);
    	assertEquals(false, JoseDbInfo.getConfig().getBool("bavard"));
    	assertEquals(false, JoseDbInfo.getConfig().getBool("debug"));
    	Path currentDir = Paths.get(System.getProperty("user.dir"));
		Path targetDir = Paths.get(currentDir.toString(), "base");
    	assertEquals(currentDir.toString(), JoseDbInfo.getConfig().get("workingDir"));
    	assertEquals(targetDir.toString(), JoseDbInfo.getConfig().get("targetDir"));
    	assertEquals(Paths.get(targetDir.toString(), "logs", "logs.yyyy_mm_dd.log").toString(), JoseDbInfo.getConfig().get("logFile"));
    	assertEquals(Paths.get(targetDir.toString(), "logs", "logs.requests.yyyy_mm_dd.log").toString(), JoseDbInfo.getConfig().get("requestLogFile"));
    }
    
    @Test 
    public void testInitWithVersion() {
    	boolean status = JoseDbInfo.init(new String[]{"-v"});
    	assertEquals(false, status);
    }
    
    @Test 
    public void testInitWithBadOptionVersion() {
    	boolean status = JoseDbInfo.init(new String[]{"--target path/to/db"});
    	assertEquals(false, status);
    }
	
    @Test 
    public void testInitWithArgs() {
    	boolean status = JoseDbInfo.init(new String[]{"-b", "--debug", "-t=path/tu/db"});
    	assertEquals(true, status);
    	assertEquals(true, JoseDbInfo.getConfig().getBool("bavard"));
    	assertEquals(true, JoseDbInfo.getConfig().getBool("debug"));
    	assertEquals("path/tu/db", JoseDbInfo.getConfig().get("targetDir"));
    	assertEquals(Paths.get("path/tu/db", "logs", "logs.yyyy_mm_dd.log").toString(), JoseDbInfo.getConfig().get("logFile"));
    	assertEquals(Paths.get("path/tu/db", "logs", "logs.requests.yyyy_mm_dd.log").toString(), JoseDbInfo.getConfig().get("requestLogFile"));
    }

}
