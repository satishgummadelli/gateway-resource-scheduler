package com.jpmorgan.interviewtest.gateway.lib;

import com.jpmorgan.interviewtest.gateway.schedule.StatusCode;


public class MessageMock extends Message{

	public MessageMock(String groupName) {
		super(groupName);
	}

	@Override
	public void completed() {
		setChanged();
		notifyObservers(StatusCode.COMPLETE);
	}
	
	@Override
	public String toString() {
		return getGroup();
	}
	

}
