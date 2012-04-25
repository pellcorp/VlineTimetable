package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.nodes.Document;

import com.pellcorp.android.vline.offline.TimetableRequest.Direction;
import com.pellcorp.android.vline.offline.TimetableRequest.Period;

//http://tt.ptv.vic.gov.au/tt/XSLT_REQUEST?itdLPxx_lineMain=1745&itdLPxx_lineID=4046&itdLPxx_output=html

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
	}
	
	//http://ptv.vic.gov.au/route/view/1751
	@Override
	public Document getRoute(String mainLineId) throws IOException {
		return HtmlUtils.getHtmlPage("/route/view/" + mainLineId);
	}
	
	@Override
	public Document getRouteSelector() throws IOException {
		return HtmlUtils.getHtmlPage("/timetables/v-line");
	}
	
	// http://tt.ptv.vic.gov.au/tt/XSLT_REQUEST?itdLPxx_lineMain=1745&itdLPxx_lineID=4046&itdLPxx_output=html
	@Override
	public Document getTimetable(TimetableLineRequest request) throws IOException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("itdLPxx_lineMain", request.getMainLineId() + ""));
		params.add(new BasicNameValuePair("itdLPxx_lineID", request.getLineId() + ""));
		params.add(new BasicNameValuePair("itdLPxx_output", "html"));
		return HtmlUtils.getHtmlPage("/att/XSLT_REQUEST", params);
	}
	
	// //http://tt.ptv.vic.gov.au/tt/XSLT_TTB_REQUEST?command=direct&language=en&net=vic&line=01V23&project=ttb&outputFormat=0
	@Override
	public Document getTimetable(TimetableRequest request) throws IOException {
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
		
		return HtmlUtils.getHtmlPage("/att/XSLT_TTB_REQUEST", params);
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
