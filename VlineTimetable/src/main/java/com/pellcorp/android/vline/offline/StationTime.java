package com.pellcorp.android.vline.offline;

public class StationTime {
	private final Station station;
	private final Time time;
	
	public StationTime(final Station station, final Time time) {
		this.station = station;
		this.time = time;
	}

	public Station getStation() {
		return station;
	}

	public Time getTime() {
		return time;
	}
}
