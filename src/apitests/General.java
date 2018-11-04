package apitests;

import org.testng.Assert;
import org.testng.annotations.Test;

import httpclient.HttpClient;
import org.json.JSONObject;
import org.json.JSONArray;
import json.*;


public class General {

	@Test
	public void Test_Search() {
		
		HttpClient http_client = new HttpClient();
		
		try {
			
			http_client.get("https://images-api.nasa.gov/search?q=Ken");
			
			SearchResult searchResult = new SearchResult(http_client.responseText());
			
			if ( ! searchResult.validateMetadata()) {
				System.out.println("Metadata validation failed");
				Assert.fail();
			}
			
			System.out.println("Result count: " + searchResult.getHitsCount());
			
			if ( ! searchResult.validateItems()) {
				System.out.println("Items validation failed");
				Assert.fail();
			}
			
			
		    
			
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	
	@Test
	public void Test_Asset() {
		HttpClient http_client = new HttpClient();
		try {
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
