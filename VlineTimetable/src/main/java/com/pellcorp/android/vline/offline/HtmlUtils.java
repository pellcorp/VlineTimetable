package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public final class HtmlUtils {
	private static final Whitelist WHITELIST = new Whitelist();
	static {
		WHITELIST.addTags("table", "tr", "td", "div", "a", "input", "select", "option", "meta");
		WHITELIST.addAttributes("div", "id");
		WHITELIST.addAttributes("a", "id", "href");
		WHITELIST.addAttributes("input", "id", "name", "value");
		WHITELIST.addAttributes("select", "id", "name");
		WHITELIST.addAttributes("option", "id", "value");
		WHITELIST.addAttributes("meta", "http-equiv", "content");
	}
	
	private HtmlUtils() {
	}
	
	public static Document clean(String dirtyHtml) {
		String html = Jsoup.clean(dirtyHtml, WHITELIST);
		return Jsoup.parse(html);
	}
	
	public static Document getHtmlPage(String virtualLocation, List<NameValuePair> params) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			URI uri = URIUtils.createURI("http", "tt.ptv.vic.gov.au", -1, virtualLocation, 
				    URLEncodedUtils.format(params, "UTF-8"), null);
			
			HttpGet get = new HttpGet(uri);

			HttpResponse response = httpClient.execute(get);
			
			return clean(EntityUtils.toString(response.getEntity()));
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
}
