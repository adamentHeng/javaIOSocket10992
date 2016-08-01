package com.hand.Exam3;

public class JSONObject {
	private StringBuilder stringBuilder = new StringBuilder("{");
	public JSONObject(){
		
	}
	
	public void add(String key, String value){
		stringBuilder.append(key+":\""+value+"\",");
	}
	
	@Override
	public String toString() {
		stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "}");
		return stringBuilder.toString();
	}
}
