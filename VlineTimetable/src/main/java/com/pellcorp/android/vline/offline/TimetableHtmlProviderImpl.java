package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.pellcorp.android.vline.offline.TimetableRequest.Direction;
import com.pellcorp.android.vline.offline.TimetableRequest.Period;

public class TimetableHtmlProviderImpl implements TimetableHtmlProvider {
	public TimetableHtmlProviderImpl() {
		
		// itdLPxx_selWDType 
		// T0 = Monday to Friday
		// T2 - Saturday
		// UJ - Sunday
		
		// itdLPxx_selLineDir 
		// R - melbourne
		// H - geelong
		
		// sup - period
		// D - I think gives current period
		
	}
	
	@Override
	public String getTimetable(TimetableRequest request) throws IOException {
		//System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		
		HttpClient httpClient = new DefaultHttpClient();
		
		try {
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("command", "direct"));
			params.add(new BasicNameValuePair("language", "en"));
			params.add(new BasicNameValuePair("outputFormat", "0"));
			params.add(new BasicNameValuePair("net", "vic"));
			params.add(new BasicNameValuePair("project", "ttb"));
			params.add(new BasicNameValuePair("sup", "D"));// todo - might need to fix this
			params.add(new BasicNameValuePair("line", request.getLine().getLineId()));
			params.add(new BasicNameValuePair("itdLPxx_selLineDir", getDirectionCode(request.getDirection())));
			params.add(new BasicNameValuePair("itdLPxx_selWDType", getPeriodCode(request.getPeriod())));
			
			URI uri = URIUtils.createURI("http", "tt.ptv.vic.gov.au", -1, "/att/XSLT_TTB_REQUEST", 
				    URLEncodedUtils.format(params, "UTF-8"), null);
			
			HttpGet get = new HttpGet(uri);

			HttpResponse response = httpClient.execute(get);
			return EntityUtils.toString(response.getEntity());
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			throw new IOException(e);
		}
	}
	
	private String getDirectionCode(Direction direction) {
		if (direction.equals(Direction.INCOMING)) {
			return "R";
		} else {
			return "H";
		}
	}
	
	private String getPeriodCode(Period period) {
		if (period.equals(Period.WEEKDAY)) {
			return "T0";
		} else if (period.equals(Period.SATURDAY)) {
			return "T2";
		} else { // SUNDAY
			return "UJ";
		}
	}
}
