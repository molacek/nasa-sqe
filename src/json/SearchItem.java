package json;

import org.json.JSONObject;

public class SearchItem {
    
	public class Links {
	   
		public String href;
	    public String render;
	    public String rel;
	    
	}
	
	public class Data {
		String center;
		String photographer;
		String location;
		String description;
		String nasa_id;
		String media_type;
		String date_created;
		String title;
		String[] keywords;
	}
	
	public String href;
	public Links links;
	public Data data;
	private JSONObject searchItem;
	
	public SearchItem(JSONObject siJson) {
		this.searchItem = siJson;
	}
	
	public boolean validateItemStructure() {
		String href = this.searchItem.getString("href");
		if (! href.startsWith("https://images-assets.nasa.gov")) {
			return false;
		}
		if (! href.endsWith("collection.json")) {
			return false;
		}
		
		return true;
	}
	
}
