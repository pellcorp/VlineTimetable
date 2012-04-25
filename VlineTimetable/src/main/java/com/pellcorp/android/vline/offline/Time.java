package com.pellcorp.android.vline.offline;

public class Time {
	public static final Time EMPTY = new Time(-1, -1);
	
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

	public String toString() {
		if (hours != -1 && minutes != -1) {
			return formatNumber(hours) + ":" + formatNumber(minutes);
		} else {
			return "";
		}
	}
	
	private String formatNumber(int number) {
		if (number < 10) {
			return "0" + number;
		} else {
			return number + "";
		}
	}
}
