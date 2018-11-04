package json;

import org.json.JSONObject;

public class Metadata {
	
	private JSONObject responseJson;
	
    public Metadata(String responseText) {
    	this.responseJson = new JSONObject(responseText);
    }
    
    public boolean validateLocation() {
    	if ( ! this.responseJson.has("location")) {
    		System.out.println("There is no location in " + this.responseJson);
    		return false;
    	}
    	return true;
    }
    
    public String getLocation() {
        return this.responseJson.getString("location");
    }
}
