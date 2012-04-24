package com.pellcorp.android.vline.offline;

public class Time {
	private final int hours;
	private final int minutes;
	
	/**
	 * 24 hour time
	 * 
	 * @param hours
	 * @param minutes
	 */
	public Time(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}
	
	
}
