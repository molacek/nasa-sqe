package tests;

import org.testng.annotations.Test;
import httpclient.HttpClient;

public class General {
	
	@Test
	public void test_a() {
		HttpClient http_client = new HttpClient();
		System.out.println(http_client);
	}

}
