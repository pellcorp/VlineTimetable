package com.pellcorp.ptv.timetable;

public class TimetableRequest {
	private final int lineId;
	private final int departureId;
	private final int directionId;
	
	public TimetableRequest(final int lineId, final int departureId, final int directionId) {
		this.lineId = lineId;
		this.departureId = departureId;
		this.directionId = directionId;
	}

	public int getLineId() {
		return lineId;
	}

	public int getDepartureId() {
		return departureId;
	}

	public int getDirectionId() {
		return directionId;
	}
}
