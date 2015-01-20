package com.jpmorgan.interviewtest.gateway.schedule.strategy.impl;

import java.util.Queue;

import com.jpmorgan.interviewtest.gateway.lib.Message;
import com.jpmorgan.interviewtest.gateway.schedule.Scheduler;
import com.jpmorgan.interviewtest.gateway.schedule.strategy.SchedulerStrategy;


/**
 * Strategy to select all the group elements first
 * @author Satish Gummadelli
 *
 */
public class GroupStrategy implements SchedulerStrategy {

	@Override
	public Message selectFromQueue(Scheduler s, Queue<Message> messageCollection) {

		for (Message processingMessage : s.getGateway().getProcessing()) {
			for (Message messagesInQueue : messageCollection) {
				if (processingMessage.getGroup() == messagesInQueue.getGroup()) {
					return messagesInQueue;
				}
			}
		}
		return messageCollection.element();
	}

}
