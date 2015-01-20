package com.jpmorgan.interviewtest.gateway.lib;

import java.util.Collection;


/**
 * 3rd Party Interface Gateway
 * @author Satish Gummadelli
 *
 */
public interface Gateway {
	public void send(Message msg);

	public Collection<Message> getProcessing();

}
