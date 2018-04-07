package io.josedb.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class JoseDbInfo {
	private static final String PRODUCT = "JoseDB";
	private static final String VERSION = "0.0.1";
	private static final String VENDOR = "Evansofts";
	private static final String MESSAGE = "Welcome to JoseDB";
	private static final String TAG_LINE = "The NoSQL database made in Liberia!";
	
	private static JoseDbInfo instance;
    private Config config;
    
	private JoseDbInfo(){}
	
	public static boolean init(String[] args){
		if(JoseDbInfo.instance != null ){
			System.err.println("System aleardy initialized !");
			return false;
		}
		
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path targetPath = Paths.get(currentPath.toString(), "base");
		String configFile = "config.properties";
		
		JoseDbInfo.instance = new JoseDbInfo();
		JoseDbInfo.instance.config = Config.load(Paths.get(currentPath.toString(), "base", configFile).toString());
		JoseDbInfo.instance.config.set("configFile", configFile);
		
		JoseDbInfo.instance.config.set("workingDir", currentPath.toString());
		JoseDbInfo.instance.config.set("targetDir", targetPath.toString());
		JoseDbInfo.instance.config.set("bavard", false);
		JoseDbInfo.instance.config.set("debug", false);
		
		// Ref: https://commons.apache.org/proper/commons-cli/usage.html
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		options.addOption("v", "version", false, "print the version information and exit.");
		options.addOption("b", "bavard", false, "verbose: display all logs");
		options.addOption("d", "debug", false, "display request logs");
		options.addOption(Option.builder("t")
				.hasArg()
				.argName("target")
				.longOpt("target")
				.desc("set the JoseDB working path.")
				.build());
		HelpFormatter formatter = new HelpFormatter();
		
		try {
		    CommandLine commandLine = parser.parse(options, args);
		    if(commandLine.hasOption("help")){
		    	formatter.printHelp("JoseDB", options, true);
		    	return false;
		    }
		    
		    if(commandLine.hasOption("version")){
		    	System.out.println(String.format("{product: JoseDB, version: %s, vendor: %s}", JoseDbInfo.getVersion(), JoseDbInfo.getVendor()));
		    	return false;
		    }

		    JoseDbInfo.instance.config.set("bavard", commandLine.hasOption("bavard"));
	    	JoseDbInfo.instance.config.set("debug", commandLine.hasOption("debug") );
		    if(commandLine.hasOption("target")){
		    	JoseDbInfo.instance.config.set("targetDir", commandLine.getOptionValue("target")); 
		    }
		    
		    // Set other variables here
		    String targetDir = JoseDbInfo.getConfig().get("targetDir");
		    String logFile = JoseDbInfo.getConfig().get("logFile");
		    String requestLogFile = JoseDbInfo.getConfig().get("requestLogFile");
		    JoseDbInfo.instance.config.set("logFile", Paths.get(targetDir, "logs", logFile).toString()); 
		    JoseDbInfo.instance.config.set("requestLogFile", Paths.get(targetDir, "logs", requestLogFile).toString()); 
		    
		} catch(ParseException ex) {
			formatter.printHelp("JoseDB", options, true);
		    return false;
		}
		return true;
	}


	public static JoseDbInfo getInstance(){
		return JoseDbInfo.instance;
	}
	
	public static boolean isDebug() {
		return JoseDbInfo.instance.config.getBool("debug");
	}

	public static Config getConfig(){
		return JoseDbInfo.instance.config;
	}
		
	public static String getProduct() {
		return JoseDbInfo.PRODUCT;
	}
	
	public static String getVersion(){
		return JoseDbInfo.VERSION;
	}
	
	public static String getVendor() {
		return JoseDbInfo.VENDOR;	
	}
	
	public static String getMessage(){
		return JoseDbInfo.MESSAGE;
	}
	
	public static String getTagLine(){
		return JoseDbInfo.TAG_LINE; 
	}

}
