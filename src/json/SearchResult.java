package json;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchResult {
	
	JSONObject responseJson;
	JSONObject responseCollection;
	int totalHits;
	
	public SearchResult(String responseText) {
		this.responseJson = new JSONObject(responseText);
		this.responseCollection = (JSONObject)this.responseJson.get("collection");
	}
	
	public boolean validateItems() {
		JSONArray responseItems = (JSONArray)this.responseCollection.get("items");
		for(int i=0; i<responseItems.length(); i++) {
		    SearchItem si = new SearchItem(responseItems.getJSONObject(i));
		    if ( ! si.validateItemStructure() ) {
		    	System.out.println("Invalid structure for search item: " + responseItems.getJSONObject(i));
		    	return false;
		    }
		}
		return true;
	}
	
	public boolean validateMetadata() {
		
		if ( ! this.responseCollection.has("metadata")) {
		    System.out.println("No 'metadata' present");
		    return false;
		}
		JSONObject metadata = this.responseCollection.getJSONObject("metadata");
		
		if ( ! metadata.has("total_hits")) {
			System.out.println("No 'total_hits' present");
		    return false;
		}
		
		this.totalHits = (int)metadata.get("total_hits");
		
		return true;
	}
	
	public int getHitsCount() {
		return this.totalHits;
	}
	
	
}
