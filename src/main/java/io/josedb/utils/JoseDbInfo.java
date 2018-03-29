package io.josedb.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class JoseDbInfo {
	private static final String VERSION = "0.0.1";
	private static final String MESSAGE = "Welcome to JoseDB";
	private static final String TAG_LINE = "The NoSQL database made in Liberia!";
	
	private static JoseDbInfo instance;
	
	private boolean isDebugEnabled;
    private String currentWorkingDir;
    private Config config;
    
	private JoseDbInfo(){}
	
	public static boolean init(String[] args){
		if(JoseDbInfo.instance != null ){
			System.err.println("System aleardy initialized !");
			return false;
		}
		
		JoseDbInfo.instance = new JoseDbInfo();
		JoseDbInfo.instance.config = Config.load("config.properties");
		
		// REF: https://commons.apache.org/proper/commons-cli/usage.html
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		options.addOption( "p", "path", false, "Path where JoseDB should operate." );
		options.addOption( "v", "verbose", false, "display all logs" );
		options.addOption( "d", "debug", false, "display request logs" );
		try {
		    // parse the command line arguments
		    CommandLine commandLine = parser.parse(options, args);
		    
		    
		    // validate arguments & set config info on this class
		    // command line take precedenc over file config
		    if(commandLine.hasOption("debug")){
		        JoseDbInfo.instance.isDebugEnabled = true; 
		        System.out.println(commandLine.getOptionValue( "debug"));
		    }
		    
		    JoseDbInfo.instance.currentWorkingDir = System.getProperty("user.dir");
		    
		    
		} catch(ParseException ex) {
		    System.err.println("Unable to parse the arguments!");
		    return false;
		}
		return true;
	}
	
	public static boolean hasDebugEnabled() {
		return JoseDbInfo.instance.isDebugEnabled;
	}

	public static Config getConfig(){
		return JoseDbInfo.instance.config;
	}

	
	public static String getVersion(){
		return JoseDbInfo.VERSION;
	}
	
	public static String getMessage(){
		return JoseDbInfo.MESSAGE;
	}
	
	public static String getTagLine(){
		return JoseDbInfo.TAG_LINE; 
	}


}
