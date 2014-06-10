package com.pellcorp.android.vline.offline;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TimeTableResult implements Serializable {
	private static final long serialVersionUID = -8445426032593156662L;
	
	private List<Date> toMelbourne;
	private List<Date> fromMelbourne;
	private String errorMessage;
	
	public TimeTableResult(List<Date> toMelbourne, List<Date> fromMelbourne) {
		this.toMelbourne = toMelbourne;
		this.fromMelbourne = fromMelbourne;
	}
	
	public TimeTableResult(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Date> getToMelbourneTimetable() {
		return toMelbourne;
	}

	public List<Date> getFromMelbourneTimetable() {
		return fromMelbourne;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
