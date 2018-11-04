package apitests;

import org.testng.Assert;
import org.testng.annotations.Test;

import httpclient.HttpClient;
import org.json.JSONObject;
import org.json.JSONArray;
import json.*;


public class General {

	@Test
	public void Test_A() {
		
		HttpClient http_client = new HttpClient();
		
		try {
			http_client.get("https://images-api.nasa.gov/search?q=center");
			JSONObject responseJson = new JSONObject(http_client.responseText());
			JSONObject responseCollection = (JSONObject)responseJson.get("collection");
			JSONArray responseItems = (JSONArray)responseCollection.get("items");
			for(int i=0; i<responseItems.length(); i++) {
			    SearchItem si = new SearchItem(responseItems.getJSONObject(i));
			    if ( ! si.validateItemStructure() ) {
			    	Assert.fail("Invalid structure for search item: " + responseItems.getJSONObject(i));
			    }
			}
		    
			
		} catch (Exception e) {
			//System.out.println(e.getStackTrace());
			System.out.println(e);
		}
	
	}
}
