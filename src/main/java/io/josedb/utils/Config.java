package io.josedb.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private Properties properties;
	
	private Config() {
		this.properties = new Properties();
	
	}

	public String get(String propName) {
		return this.properties.getProperty(propName, null);
	}
	
	public void set(String propName, Object propValue) {
		this.properties.setProperty(propName, propValue.toString());
	}
	
	public void overrideWith(Config other){
		String key;
		for(Object keyObj : other.properties.keySet()){
			key = (String) keyObj;
			this.properties.setProperty(key, other.get(key));
		}
	} 
	
	public boolean getBool(String propName, boolean DefaultValue){
		String str = this.get(propName);
		return (str != null)? Boolean.valueOf(str) : DefaultValue;
	}
	
	public boolean getBool(String propName){
		return this.getBool(propName, false);
	}
	
	public int getInt(String propName, int DefaultValue) {
		String str = this.get(propName);
		return (str != null)? Integer.valueOf(str) : DefaultValue;
	}
	
	public int getInt(String propName) {
		return this.getInt(propName, 0);
	}
	
	public float getFloat(String propName, float DefaultValue) {
		String str = this.get(propName);
		return (str != null)? Float.valueOf(str) : DefaultValue;
	}
	
	public float getFloat(String propName) {
		return this.getFloat(propName, 0.0f);
	}
	
	public double getDouble(String propName, double DefaultValue) {
		String str = this.get(propName);
		return (str != null)? Double.valueOf(str) : DefaultValue;
	}
	
	public double getDouble(String propName) {
		return this.getDouble(propName, 0.0d);
	}
	
	public static Config load(String path){
		Config config= new Config();
		try {
			InputStream fileInStream = new FileInputStream(path);
			config.properties.load(fileInStream);
		} catch (Exception e) {
			// TODO Log error
			e.printStackTrace();
		}
		return config;
	}

}
