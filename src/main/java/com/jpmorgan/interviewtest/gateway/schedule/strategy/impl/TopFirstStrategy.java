package com.jpmorgan.interviewtest.gateway.schedule.strategy.impl;

import java.util.Queue;

import com.jpmorgan.interviewtest.gateway.lib.Message;
import com.jpmorgan.interviewtest.gateway.schedule.Scheduler;
import com.jpmorgan.interviewtest.gateway.schedule.strategy.SchedulerStrategy;

/**
 * Strategy to select FIFO element
 * @author Satish Gummadelli
 *
 */
public class TopFirstStrategy implements SchedulerStrategy{

	@Override
	public Message selectFromQueue(Scheduler s, Queue<Message> messageCollection) {
		return messageCollection.element();
	}

}
