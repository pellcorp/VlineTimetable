package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public final class ResourceUtils {
	private ResourceUtils() {
	}
	
	public static String loadResourceAsString(String path) throws IOException {
		InputStream is = ResourceUtils.class.getResourceAsStream(path);
		if (is != null) {
			String content = IOUtils.toString(is, "UTF-8");
			is.close();
			return content;
		}
		throw new IOException("Resource not found: " + path);
	}
}
