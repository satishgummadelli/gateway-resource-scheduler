package com.jpmorgan.interviewtest.gateway.lib;


public class MessageMock extends Message{

	public MessageMock(String groupName) {
		super(groupName);
	}

	@Override
	public void completed() {
		setChanged();
	}
	
	

}
