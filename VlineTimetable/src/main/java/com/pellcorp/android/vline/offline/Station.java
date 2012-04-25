package com.pellcorp.android.vline.offline;

public class Station {
	private final int stationId;
	private final String stationName;
	
	public Station(int stationId, String stationName) {
		this.stationId = stationId;
		this.stationName = stationName;
	}
	
	public int getStationId() {
		return stationId;
	}

	public String getStationName() {
		return stationName;
	}
	
	public String toString() {
		return stationId + ":" + stationName;
	}
}
