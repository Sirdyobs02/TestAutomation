package Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import com.relevantcodes.extentreports.LogStatus;

import Base.Base;

public class APIUtility extends Base {
	
	public String getRequest(String api_endpoint) {

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get_request = new HttpGet(api_endpoint);
		get_request.addHeader("Content-Type", "Application/json");
		HttpResponse response = null;
		try {
			response = client.execute(get_request);
			if (response.getStatusLine().getStatusCode() != 200) {
				test.log(LogStatus.FAIL, "Failed to send request to " + api_endpoint + " [HTTP error code]"
						+ response.getStatusLine().getStatusCode());
				extent.endTest(test);
				return "";
			}
			HttpEntity entity = response.getEntity();
			api_response = EntityUtils.toString(entity);
			ObjectMapper mapper = new ObjectMapper();
			test.log(LogStatus.INFO, "RESPONSE : [  ' " +(api_response) + "  ' ]");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return api_response;
	}
	
	
	public boolean post_request(String api_endpoint, String user_payload ) throws UnsupportedEncodingException {
		try {
			CloseableHttpClient http_client = HttpClientBuilder.create().build();
			HttpPost post_request = new HttpPost(api_endpoint);
			StringEntity params = new StringEntity(user_payload);
			post_request.addHeader("Content-type", "application/json");
			post_request.addHeader("Accept", "application/json");
			post_request.setEntity(params);
			HttpResponse httpResponse = http_client.execute(post_request);
			if (httpResponse.getStatusLine().getStatusCode() != 201) {
				test.log(LogStatus.FAIL, "Failed to send request to " + api_endpoint + " [HTTP error code]"
						+ httpResponse.getStatusLine().getStatusCode());
				extent.endTest(test);
				return false;
			}
			rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			System.out.println("Reading API response...");
			String line ="";
			while ((line  = rd.readLine()) != null) {
				api_response += line;
			}
			test.log(LogStatus.INFO, "RESPONSE : [  ' " + api_response + "  ' ]");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int countElementsInJsonObject() {
		JsonParser parser = new JsonParser(); 
		JsonElement jsonObject = parser.parse(api_response);
		int i = 0;
		for(JsonElement element: jsonObject.getAsJsonArray()) {
			i++;
		}
	    return i;
	}

}
