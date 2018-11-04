package json;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

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
	private List<String> dataItemLists = new ArrayList<String>();
	
	public SearchItem(JSONObject siJson) {
		this.searchItem = siJson;
		//this.dataItemLists = new ArrayList<String>();
		this.dataItemLists.add("center");
		//this.dataItemLists.add("photographer");
		this.dataItemLists.add("location");
		this.dataItemLists.add("description");
		this.dataItemLists.add("nasa_id");
		this.dataItemLists.add("media_type");
		this.dataItemLists.add("date_created");
		this.dataItemLists.add("title");
		//this.dataItemLists.add("keywords");
	}
	
	private boolean validateLinks(JSONObject links) {
		String href = links.getString("href");
		String render = links.getString("render");
		String rel = links.getString("rel");
		
		if ( ! render.equals("image")) {
			System.out.println("\"render\" is not \"image\" (" + links + ")");
			return false;
		}
		
		if ( ! rel.equals("preview")) {
			System.out.println("\"rel\" is not \"preview\" (" + links + ")");
			return false;
		}
		
		if (! href.startsWith("https://images-assets.nasa.gov")) {
			System.out.println("\"href\" doesn't start with \"https://images-assets.nasa.gov\" (" + links + ")");
			return false;
		}
		
		return true;
	}
	
	/* For simplicity check just all items are present */
	private boolean validateData(JSONObject data) {
		
		for (int i=0; i<this.dataItemLists.size(); i++) {
			if (! data.has(this.dataItemLists.get(i))) {
				System.out.println("Data has no '" + this.dataItemLists.get(i)+ "' item (" + data + ")");
				return false;	
			}
		}
		
		return true;
	}
	
	public boolean validateItemStructure() {
		//System.out.println("Processing: " + this.searchItem);
		String href = this.searchItem.getString("href");
		JSONArray links = this.searchItem.getJSONArray("links");
		JSONArray data = this.searchItem.getJSONArray("data");

		// Validate main href
		if (! href.startsWith("https://images-assets.nasa.gov")) {
			System.out.println("'href' doesn't start with 'https://images-assets.nasa.gov'");
			return false;
		}
		if (! href.endsWith("collection.json")) {
			System.out.println("'href' doesn't end with 'collection.json'");
			return false;
		}
		
		// Validate main links	
		for (int i=0; i<links.length(); i++) {
			if (! this.validateLinks(links.getJSONObject(i))) {
		        return false;
			}
		}
	
		// Validate data
		for (int i=0; i<data.length(); i++) {
			if (! this.validateData(data.getJSONObject(i))) {
		        return false;
			}
		}
		
		return true;
	}
	
}
