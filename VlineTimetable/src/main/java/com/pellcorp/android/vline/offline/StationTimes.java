package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;

public class StationTimes {
	private final int stationId;
	private final String stationName;
	private final List<Time> times = new ArrayList<Time>();
	
	public StationTimes(int stationId, String stationName) {
		this.stationId = stationId;
		this.stationName = stationName;
	}

	public int getStationId() {
		return stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void addTime(Time time) {
		this.times.add(time);
	}
}
