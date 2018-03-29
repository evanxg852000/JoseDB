package io.josedb.utils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.eclipse.jetty.server.NCSARequestLog;

public class JoseDbLogger {
	
	public JoseDbLogger() {
	}
	
	public static void init(){
		ConsoleAppender console = new ConsoleAppender();
		console.setName("JoseDbConsoleLogger");
		console.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n")); 
		console.setThreshold(Level.FATAL);
		console.activateOptions();

		RollingFileAppender rollingFile = new RollingFileAppender();
		rollingFile.setName("JoseDbFileLogger");
		rollingFile.setFile("logs.log");
		rollingFile.setMaxFileSize("5MB");
		rollingFile.setMaxBackupIndex(10);
		rollingFile.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
		rollingFile.setThreshold(Level.DEBUG);
		rollingFile.setAppend(true);
		rollingFile.activateOptions();
		
		Logger.getRootLogger().addAppender(console);
		Logger.getRootLogger().addAppender(rollingFile);
		
	
	}

}
