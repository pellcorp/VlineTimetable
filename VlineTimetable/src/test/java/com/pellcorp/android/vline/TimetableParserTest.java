package com.pellcorp.android.vline;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import com.pellcorp.android.vline.offline.StationTimes;
import com.pellcorp.android.vline.offline.TimetableParser;

public class TimetableParserTest {
	@Test
	public void testDownloadTimetable() throws Exception {
		String url = "http://tt.ptv.vic.gov.au/att/XSLT_TTB_REQUEST?command=direct&language=en&outputFormat=0&net=vic&line=01V23&project=ttb&itdLPxx_selLineDir=R&sup=D";
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpGet get = new HttpGet(url);
//		HttpResponse response = httpClient.execute(get);
//		String dirtyHtml = EntityUtils.toString(response.getEntity());
		
		String dirtyHtml = loadResource("/PTV_Timetables.html");
		
		TimetableParser parser = new TimetableParser();
		List<StationTimes> stationTimes = parser.parseTimetable(dirtyHtml);
		assertEquals(12, stationTimes.size());
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
