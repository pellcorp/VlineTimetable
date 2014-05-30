package com.pellcorp.ptv;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import com.pellcorp.ptv.http.PtvClient;

public class ApiTest {
	PtvClient client = new PtvClient("96bf335e-bfaa-11e3-8bed-0263a9d0b8a0", 1000128);
	
	@Test
	@Ignore
	public void testHealth() throws Exception {
		System.out.println(client.doGet("/v2/healthcheck?timestamp=2014-05-30T07:36:03Z"));
	}
	
	@Test
	@Ignore
	public void testSearch() throws Exception {
		// 1534
		JSONArray arr = (JSONArray) client.doGet("/v2/search/Lara");
		for (int i = 0; i< arr.size(); i++) {
			JSONObject obj = (JSONObject) arr.get(i);
			JSONObject result = (JSONObject) obj.get("result");
			System.out.println(result.toJSONString());
		}
	}
	
	@Test
	@Ignore
	public void testDeparturesLara() throws Exception {
		System.out.println(client.doGet("/v2/mode/3/stop/1534/departures/by-destination/limit/0").toJSONString());
	}
	
	@Test
	@Ignore
	public void testDeparturesMelbourne() throws Exception {
		System.out.println(client.doGet("/v2/mode/3/stop/1181/departures/by-destination/limit/0").toJSONString());
	}
	
	@Test
	@Ignore
	public void testStopsForGeelongLine() throws Exception {
		// Lara = 1534
		// Southern Cross = 1181
		System.out.println(client.doGet("/v2/mode/3/line/1745/stops-for-line"));
	}
	
	@Test
	@Ignore
	public void testStopsForWarnamboolLine() throws Exception {
		// Lara = 1534
		// Southern Cross = 1181
		System.out.println(client.doGet("/v2/mode/3/line/1853/stops-for-line"));
	}
	
	//1853
	
	@Test
	@Ignore
	public void testGetDeparturesFromLara() throws Exception {
		System.out.println(client.doGet("/v2/mode/3/line/1745/stop/1534/directionid/1/departures/all/limit/5?for_utc=2014-05-30T12:38:37Z"));
	}
	
	@Test
	@Ignore
	public void testGetDeparturesFromMelbourne() throws Exception {
		System.out.println(client.doGet("/v2/mode/3/line/1745/stop/1534/directionid/21/departures/all/limit/5?for_utc=2014-05-30T12:38:37Z"));
	}
	
	@Test
	@Ignore
	public void testGetDeparturesFromMelbourneWarn() throws Exception {
		System.out.println(client.doGet("/v2/mode/3/line/1853/stop/1534/directionid/1/departures/all/limit/5?for_utc=2014-05-30T12:38:37Z"));
	}
}
