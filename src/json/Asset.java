package json;

import org.json.JSONObject;
import org.json.JSONArray;


public class Asset {
	
	private JSONObject responseJson;
	private JSONObject responseCollection;
	
    public Asset(String responseText) {
    	this.responseJson = new JSONObject(responseText);
    	this.responseCollection = (JSONObject)this.responseJson.get("collection");
    }
    
    public int linksCount() {
        JSONArray items = this.responseCollection.getJSONArray("items");
        return items.length();
    }
    
    public boolean validateHref(String url) {
    	String assetHref = this.responseCollection.getString("href");
    	if ( ! assetHref.equals(url)) {
    		System.out.println("'href' (" + assetHref + ") is different from URL");
    		return false;
    	}
    	return true;
    	
    }
}
