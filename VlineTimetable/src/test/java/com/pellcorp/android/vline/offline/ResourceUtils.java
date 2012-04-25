package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;

public final class ResourceUtils {
	private ResourceUtils() {
	}
	
	public static Document loadResourceAsDocument(String path) throws IOException {
		InputStream is = ResourceUtils.class.getResourceAsStream(path);
		if (is != null) {
			String content = IOUtils.toString(is, "UTF-8");
			is.close();
			return HtmlUtils.clean(content);
		}
		throw new IOException("Resource not found: " + path);
	}
}
