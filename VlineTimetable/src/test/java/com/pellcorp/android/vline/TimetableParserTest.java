package com.pellcorp.android.vline;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.pellcorp.android.vline.offline.Timetable;
import com.pellcorp.android.vline.offline.TimetableParser;

public class TimetableParserTest {
	@Test
	public void testDownloadTimetable() throws Exception {
		String url = "http://tt.ptv.vic.gov.au/att/XSLT_TTB_REQUEST?command=direct&language=en&outputFormat=0&net=vic&line=01V23&project=ttb&itdLPxx_selLineDir=R&sup=D";
		
		// itdLPxx_selWDType 
		// T0 = Monday to Friday
		// T2 - Saturday
		// UJ - Sunday
		
		// itdLPxx_selLineDir 
		// R - melbourne
		// H - geelong
		
		// sup - period
		// D - I think gives current period
		
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpGet get = new HttpGet(url);
//		HttpResponse response = httpClient.execute(get);
//		String dirtyHtml = EntityUtils.toString(response.getEntity());
		
		String dirtyHtml = loadResource("/PTV_Timetables.html");
		
		TimetableParser parser = new TimetableParser();
		Timetable timetable = parser.parseTimetable(dirtyHtml);
		//assertEquals(12, timetable.getServices().size());
	}

	private String loadResource(String path) throws IOException {
		InputStream is = TimetableParserTest.class.getResourceAsStream(path);
		if (is != null) {
			String content = IOUtils.toString(is, "UTF-8");
			is.close();
			return content;
		}
		throw new IOException("Resource not found: " + path);
	}
}
