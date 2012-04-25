package com.pellcorp.android.vline.offline;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
		Document doc = Jsoup.parse(html);
		return doc;
	}
}
