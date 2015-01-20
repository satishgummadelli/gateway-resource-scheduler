package com.jpmorgan.interviewtest.gateway.schedule;

import java.util.Iterator;

import org.junit.Test;

import com.jpmorgan.interviewtest.gateway.lib.Gateway;
import com.jpmorgan.interviewtest.gateway.lib.GatewayMock;
import com.jpmorgan.interviewtest.gateway.lib.Message;
import com.jpmorgan.interviewtest.gateway.lib.MessageMock;


/**
 * tests Scheduling
 * @author Satish Gummadelli
 *
 */
public class SchedulerTest {
	
	private Scheduler scheduler;
	private MessageMock message;
	private GatewayMock gateway;

	@Test
	public void shouldProcessOneMessageForSingleResourceAndSingleMessage(){
		gateway = new GatewayMock();
		message = new MessageMock("products");
		scheduler = new Scheduler(1, gateway);
		scheduler.scheduleMessage(message);
		message.completed();
	}
	
	@Test
	public void shouldProcessOneMessageForSingleResourceAndMultipleMessage() throws InterruptedException{
		gateway = new GatewayMock();
		message = new MessageMock("products");
		scheduler = new Scheduler(1, gateway);
		Message[] messages = getMessages(5);
		scheduler.scheduleMessage(messages);
		completeMessages(messages);
		
	}

	private void completeMessages(Message[] messages) throws InterruptedException {
		for (Message message : messages) {
			Thread.sleep(500);
			message.completed();
		}
		
	}

	private Message[] getMessages(int numberOfMessages){
		Message[] messages = new Message[5];
		for (int index = 0; index < numberOfMessages; index++) {
			messages[index] = new MessageMock("group"+index);
		}
		return messages;
	}
	

}
