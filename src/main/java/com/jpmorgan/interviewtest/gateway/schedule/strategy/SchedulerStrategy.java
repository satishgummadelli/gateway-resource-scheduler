package com.jpmorgan.interviewtest.gateway.schedule.strategy;

import java.util.Queue;

import com.jpmorgan.interviewtest.gateway.lib.Message;
import com.jpmorgan.interviewtest.gateway.schedule.Scheduler;


/**
 * Strategy for Scheduling
 * @author Satish Gummadelli
 *
 */
public interface SchedulerStrategy {
	
	 public Message selectFromQueue(Scheduler s,
             Queue<Message> messageCollection);

}
