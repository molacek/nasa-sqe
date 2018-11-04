package apitests;

import org.testng.Assert;
import org.testng.annotations.Test;

import httpclient.HttpClient;
import org.json.JSONObject;
import org.json.JSONArray;
import json.*;


public class General {

	@Test(enabled=false)
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
	
	@Test(enabled=false)
	public void Test_Asset() {
		HttpClient http_client = new HttpClient();
		try {
			String url = "https://images-api.nasa.gov/asset/KSC-2013-3022";
			http_client.get(url);
			Asset asset = new Asset(http_client.responseText());
			
			// Validate links count
			if (asset.linksCount() == 0) {
				Assert.fail("Links count is zero");
			}
			
			// Validate returned href
			if ( ! asset.validateHref(url)) {
				Assert.fail("Error validating HREF");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void Test_Metadata() {
		HttpClient http_client = new HttpClient();
		try {
			String url = "https://images-api.nasa.gov/metadata/KSC-2013-3022";
			http_client.get(url);
			Metadata metadata = new Metadata(http_client.responseText());
			if (! metadata.validateLocation()) {
				Assert.fail("No location for metadata (" + url + ")");
			}
			String metadataLocation = metadata.getLocation();
			http_client.get(metadataLocation);
			if (http_client.responseCode() != 200) {
				Assert.fail("Error getting metadata from '" + metadataLocation + "'");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
