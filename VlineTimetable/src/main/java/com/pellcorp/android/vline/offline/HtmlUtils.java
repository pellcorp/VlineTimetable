package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HtmlUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtils.class);
	
	private static final List<NameValuePair> EMPTY_PARAMS = new ArrayList<NameValuePair>();
	
	private static final Whitelist WHITELIST = new Whitelist();
	static {
		WHITELIST.addTags("table", "tr", "td", "div", "a", "input", "select", "option", "meta", "ul", "li");
		WHITELIST.addAttributes("div", "id", "class");
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
	
	public static Document getHtmlPage(String virtualLocation) throws IOException {
		return getHtmlPage(virtualLocation, EMPTY_PARAMS);
	}
	
	
	public static Document getHtmlPage(String virtualLocation, List<NameValuePair> params) throws IOException {
		try {
			URI uri = URIUtils.createURI("http", "tt.ptv.vic.gov.au", -1, virtualLocation, 
				    URLEncodedUtils.format(params, "UTF-8"), null);
			
			return getHtmlPageWithUrl(uri);
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
	}
	
	private static Document getHtmlPageWithUrl(URI uri) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpGet get = new HttpGet(uri);
			HttpResponse response = httpClient.execute(get);
			Document doc = clean(EntityUtils.toString(response.getEntity()));
			URI redirect = getRedirectUrl(doc);
			if (redirect != null) {
				LOGGER.debug("Redirecting {}", redirect.toString());
				return getHtmlPageWithUrl(redirect);
			} else {
				return doc;
			}
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	//<meta http-equiv="refresh" content="0; url=XSLT_TTB_REQUEST?command=direct&amp;language=en&amp;outputFormat=0&amp;net=vic&amp;line=01V23&amp;project=ttb&amp;itdLPxx_selLineDir=H&amp;sup=A" /> Please wait while loading HTML timetable...
	private static URI getRedirectUrl(Document doc) throws URISyntaxException {
		Elements els = doc.select("div[http-equiv=refresh]");
		if (els != null && els.size() > 0) {
			return new URI(els.get(0).attr("url"));
		} else {
			return null;
		}
	}
}
