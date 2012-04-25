package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;

public class StationTimes {
	private final Station station;
	private final List<Time> times = new ArrayList<Time>();
	
	public StationTimes(final Station station) {
		this.station = station;
	}

	public Station getStation() {
		return station;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void addTime(Time time) {
		this.times.add(time);
	}
}
