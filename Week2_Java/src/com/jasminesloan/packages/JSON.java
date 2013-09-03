package com.jasminesloan.packages;

import org.json.JSONException;
import org.json.JSONObject;

public class JSON {
	
	public static JSONObject buildJSON() {
		
		JSONObject jsonObject = new JSONObject();
		
		try{
			JSONObject query = new JSONObject();
			for (Info products : Info.values()){
				JSONObject newObject = new JSONObject();
				
				newObject.put("screenSize", products.setScreenSize());
				newObject.put("price", products.setPrice());
				newObject.put("processor", products.setProcessor());
				newObject.put("storage", products.setStorage());
				query.put(products.name().toString(), newObject);
			}
			
			jsonObject.put("query", query);
		}catch (JSONException e){
			e.printStackTrace();
		}
		return jsonObject;
		
	}
	
	public static String readJSON(String selected){
		
		String results, screenSize, price, processor, storage;
		JSONObject objectInfo = buildJSON();
		
		try{
			screenSize = objectInfo.getJSONObject("query").getJSONObject(selected).getString("screenSize");
			price = objectInfo.getJSONObject("query").getJSONObject(selected).getString("price");
			processor = objectInfo.getJSONObject("query").getJSONObject(selected).getString("processor");
			storage = objectInfo.getJSONObject("query").getJSONObject(selected).getString("storage");
			
			results = "Screen Size: " + screenSize + "\r\n"
					+ "Price: " + price + "\r\n"
					+ "Processor: " + processor + "\r\n"
					+ "Storage: " + storage;
		}catch (JSONException e){
			e.printStackTrace();
			
			results = e.toString();
		}
		return results;
		
	}
}
