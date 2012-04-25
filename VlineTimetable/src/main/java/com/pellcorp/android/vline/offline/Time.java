package com.pellcorp.android.vline.offline;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		Time other = (Time) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(hours, other.hours);
        builder.append(minutes, other.minutes);
        return builder.isEquals();
	}

	@Override
	public int hashCode() {
	    HashCodeBuilder builder = new HashCodeBuilder();
	    builder.append(hours);
	    builder.append(minutes);
	    return builder.toHashCode();
	}
}
