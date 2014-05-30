package com.pellcorp.ptv.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONAware;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PtvClient {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final String baseURL = "http://timetableapi.ptv.vic.gov.au";
	private final PtvUrlEncoder encoder;
	
	public PtvClient(final String privateKey, final int developerId) {
		encoder = new PtvUrlEncoder(privateKey, developerId);
	}
	
	public JSONAware doGet(String uri) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			String fullUrl = baseURL + encoder.encodeUri(uri);
			logger.debug(fullUrl);
			HttpGet get = new HttpGet(baseURL + encoder.encodeUri(uri));
			HttpResponse response = httpClient.execute(get);
			String jsonResponse = EntityUtils.toString(response.getEntity());
			JSONParser parser = new JSONParser();
			JSONAware jsonResult = (JSONAware) parser.parse(jsonResponse);
			return jsonResult;
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
}
