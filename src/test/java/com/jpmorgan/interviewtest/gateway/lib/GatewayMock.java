package com.jpmorgan.interviewtest.gateway.lib;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Gateway Mock for testing
 * @author Satish Gummadelli
 *
 */

public class GatewayMock {

	private Queue<Message> processingQueue = new LinkedList<Message>();

	public Queue<Message> getProcessing() {
		return this.processingQueue;
	}

	public void send(Message m) {
		processingQueue.add(m);
	}

}
