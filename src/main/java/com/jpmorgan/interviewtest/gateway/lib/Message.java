package com.jpmorgan.interviewtest.gateway.lib;


/**
 * Message class
 * @author Satish Gummadelli
 *
 */
public abstract class Message {
	
	private String group;
	
	public Message(String groupName) {
		group = groupName;
	}

	public String getGroup() {
		return group;
	}
	
	/**
	 * Should be implemented by 3rd party lib
	 */
	public abstract void completed();

}
