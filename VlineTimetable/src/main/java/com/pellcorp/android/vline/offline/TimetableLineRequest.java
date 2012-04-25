package com.pellcorp.android.vline.offline;

public class TimetableLineRequest {
	private final String mainLineId;
	private final String lineId;
	
	/**
	 * The initial route request has a different lineId to what we eventually
	 * want, so that why its not domain objects here.
	 * 
	 * @param mainLineId
	 * @param lineId
	 */
	public TimetableLineRequest(final String mainLineId, final String lineId) {
		this.mainLineId = mainLineId;
		this.lineId = lineId;
	}

	public String getMainLineId() {
		return mainLineId;
	}

	public String getLineId() {
		return lineId;
	}
}
