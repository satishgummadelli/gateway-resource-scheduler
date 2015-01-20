package com.jpmorgan.interviewtest.gateway.schedule.strategy.impl;

import org.junit.Test;

import com.jpmorgan.interviewtest.gateway.lib.GatewayMock;
import com.jpmorgan.interviewtest.gateway.lib.Message;
import com.jpmorgan.interviewtest.gateway.lib.MessageMock;
import com.jpmorgan.interviewtest.gateway.schedule.Scheduler;
import com.jpmorgan.interviewtest.gateway.schedule.strategy.SchedulerStrategy;

public abstract class StrategyHelper {

	
	private Scheduler scheduler;
	private GatewayMock gateway;
	private SchedulerStrategy schedulerStrategy;

	


	@Test
	public void shouldProcessMessagesForSingleResourceAndSingleMessage() throws InterruptedException{
		createSchedule(1,1);
	}
	
	@Test
	public void shouldProcessMessagesForSingleResourceAndMultipleMessage() throws InterruptedException{
		createSchedule(1,5);
		
	}
	
	@Test
	public void shouldProcessMessagesForMultipleResoucesAndMultipleMessages() throws InterruptedException{
		createSchedule(3,5);
	}
	@Test
	public void shouldProcessMessagesForMultipleResoucesAndMultipleMessagesWithDuplicateGroup() throws InterruptedException{
		createScheduleForDuplicateMessages(2);
	}
	

	protected void completeMessages(Message[] messages) throws InterruptedException {
		for (Message message : messages) {
			Thread.sleep(500);
			message.completed();
		}
		
	}

	private Message[] getMessages(int numberOfMessages){
		Message[] messages = new Message[numberOfMessages];
		for (int index = 0; index < numberOfMessages; index++) {
			messages[index] = new MessageMock("group"+index);
		}
		return messages;
	}
	

	private void createSchedule(int numberOfResources, int numberOfMessages) throws InterruptedException {
		gateway = new GatewayMock();
		scheduler = new Scheduler(1, gateway,getSchedulerStrategy());
		Message[] messages = getMessages(numberOfMessages);
		scheduler.scheduleMessage(messages);
		completeMessages(messages);
	}
	
	private void createScheduleForDuplicateMessages(int numberOfResources) throws InterruptedException {
		gateway = new GatewayMock();
		scheduler = new Scheduler(1, gateway,getSchedulerStrategy());
		Message[] messages = new Message[6];
		messages[0]= new MessageMock("usersGroup");
		messages[1]= new MessageMock("adminGroup");
		messages[2]= new MessageMock("superGroup");
		messages[3]= new MessageMock("newGroup");
		messages[4]= new MessageMock("usersGroup");
		messages[5]= new MessageMock("superGroup");
		scheduler.scheduleMessage(messages);
		completeMessages(messages);
	}

	public SchedulerStrategy getSchedulerStrategy() {
		return schedulerStrategy;
	}

	public void setSchedulerStrategy(SchedulerStrategy schedulerStrategy) {
		this.schedulerStrategy = schedulerStrategy;
	}
	



}
