package apitests;

import org.testng.annotations.Test;

import httpclient.HttpClient;

public class General {

	@Test
	public void Test_A() {
		HttpClient http_client = new HttpClient();
		System.out.println(http_client);
	}
}
