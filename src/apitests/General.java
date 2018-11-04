package apitests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.net.URLEncoder;

import httpclient.HttpClient;
import json.*;



public class General {

	@DataProvider(name = "Query")
	public static Object[][] query() {
	    return new Object[][] {
	        {"test", true}, {"Armstrong", true}, {"Apollo", true}, {"blahblahblah", false}
	    };
	}
	
	@Test(enabled=true, dataProvider = "Query")
	public void Test_Search(String query, boolean resultShoudExists) {
		
		HttpClient http_client = new HttpClient();
		
		try {
			
			http_client.get("https://images-api.nasa.gov/search?q=" + URLEncoder.encode(query, "UTF-8"));
			Assert.assertTrue(http_client.responseCode() == 200);
			Assert.assertTrue(http_client.validateContentType());
			
			SearchResult searchResult = new SearchResult(http_client.responseText());
			
			// Validate metadata
			if ( ! searchResult.validateMetadata()) {
				Assert.fail("Metadata validation failed");
			}
			
			if (resultShoudExists && searchResult.getHitsCount() == 0) {
				Assert.fail("No results for query'" + query + "'");
			}
			
			if (! resultShoudExists && searchResult.getHitsCount() > 0) {
				Assert.fail("There shouldn't be results for query'" + query + "'");
			}
			
			// Validate structure of all items
			if ( ! searchResult.validateItems()) {
				Assert.fail("Items validation failed");
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
			Assert.assertTrue(http_client.validateContentType());
			Assert.assertTrue(http_client.responseCode() == 200);
			
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
	
	@Test(enabled=false)
	public void Test_Metadata() {
		HttpClient http_client = new HttpClient();
		try {
			String url = "https://images-api.nasa.gov/metadata/KSC-2013-3022";
			
			http_client.get(url);
			Assert.assertTrue(http_client.validateContentType());
			Assert.assertTrue(http_client.responseCode() == 200);
			
			Metadata metadata = new Metadata(http_client.responseText());
			
			// Validate location
			if (! metadata.validateLocation()) {
				Assert.fail("No location for metadata (" + url + ")");
			}
			
			String metadataLocation = metadata.getLocation();
			http_client.get(metadataLocation);
			
			// Validate response for metadata location
			if (http_client.responseCode() != 200) {
				Assert.fail("Error getting metadata from '" + metadataLocation + "'");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
