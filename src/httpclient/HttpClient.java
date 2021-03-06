package httpclient;

import java.net.MalformedURLException;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HttpClient {
	
	private StringBuffer content;
	private int responseCode;
	private String contentType;
	
	public HttpClient() {
		
	}
	
	public void get(String req_url) throws MalformedURLException, IOException {
		URL url = new URL(req_url);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		// Set response code
		this.responseCode = conn.getResponseCode();
		
		if (this.responseCode != 200) {
			return;
		}
		
		// Set content type
		this.contentType = conn.getHeaderField("content-type");
		
		// Read response body
		BufferedReader in = new BufferedReader(
			new InputStreamReader(conn.getInputStream())
		);
		String inputLine;
		this.content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
	}
	
	public int responseCode() {
		return this.responseCode;
	}
	public String responseText() {
		return this.content.toString();
	}
	
	public String contentType() {
		return this.contentType;
	}
	
	public boolean validateContentType() {
		boolean validation = this.contentType.equals("application/json; charset=UTF-8");
		if ( ! validation) {
			System.out.println("Invalid response content type");
		}
		return validation;
	}
}
