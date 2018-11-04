package apitests;

import org.testng.annotations.Test;

import httpclient.HttpClient;
import org.json.JSONObject;
import java.util.Iterator;

public class General {

	@Test
	public void Test_A() {
		HttpClient http_client = new HttpClient();
		try {
			http_client.get("https://images-api.nasa.gov/search?q=center");
			JSONObject responseJson = new JSONObject(http_client.responseText());
			JSONObject responseCollection = (JSONObject)responseJson.get("collection");
		    Iterator<String> it = responseCollection.keys();
		    while(it.hasNext()) {
		    	System.out.println(it.next());
		    }
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}
