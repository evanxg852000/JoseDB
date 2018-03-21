package io.josedb.web;

import java.util.Properties;

public class Config {
	private Properties properties;
	
	public Config() {
		this.properties = new Properties();
	}
	
	public String get(String proName) {
		return this.properties.getProperty(proName, null);
	}
	
	public boolean getBool(String proName, boolean DefaultValue){
		String str = this.get(proName);
		return (str != null)? Boolean.valueOf(str) : DefaultValue;
	}
	
	public boolean getBool(String proName){
		return this.getBool(proName, false);
	}
	
	public int getInt(String proName, int DefaultValue) {
		String str = this.get(proName);
		return (str != null)? Integer.valueOf(str) : DefaultValue;
	}
	
	public int getInt(String proName) {
		return this.getInt(proName, 0);
	}
	
	public float getFloat(String proName, float DefaultValue) {
		String str = this.get(proName);
		return (str != null)? Float.valueOf(str) : DefaultValue;
	}
	
	public float getFloat(String proName) {
		return this.getFloat(proName, 0.0f);
	}
	
	public double getDouble(String proName, double DefaultValue) {
		String str = this.get(proName);
		return (str != null)? Double.valueOf(str) : DefaultValue;
	}
	
	public double getDouble(String proName) {
		return this.getDouble(proName, 0.0d);
	}
	
	public static Config load(String path){
		//TODO
		return null;
	}

}
