package com.jpmorgan.interviewtest.gateway.schedule;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.jpmorgan.interviewtest.gateway.lib.Gateway;
import com.jpmorgan.interviewtest.gateway.lib.Message;
import com.jpmorgan.interviewtest.gateway.schedule.strategy.SchedulerStrategy;

/**
 * Scheduler with FIFO implementation
 * @author Satish Gummadelli
 *
 */
public class Scheduler implements Observer {
	
	private static Logger logger = Logger.getLogger(Scheduler.class);

	private Queue<Message> wait_queue;
	private Gateway gateway;
	private AtomicInteger numberOfResources;
	private SchedulerStrategy schedulerStrategy;

	public Scheduler(int numberOfResources, Gateway gateway, SchedulerStrategy strategy){
		this.wait_queue = new LinkedBlockingQueue<>();
		this.gateway = gateway;
		this.numberOfResources = new AtomicInteger(numberOfResources);
		this.schedulerStrategy = strategy;
	}

	public Queue<Message> getWaitQueue() {
		return this.wait_queue;
	}

	public Gateway getGateway() {
		return this.gateway;
	}

	public void scheduleMessage(Message... msgs) {

		Queue<Message> messagesQueue = new LinkedBlockingQueue<>(Arrays.asList(msgs));
		while (!messagesQueue.isEmpty()) {
			Message msg = schedulerStrategy.selectFromQueue(this, messagesQueue);
			messagesQueue.remove(msg);
			if (numberOfResources.get() > 0) {
				numberOfResources.decrementAndGet();
				msg.addObserver(this);
				logger.info("Sending Message "+ msg.getGroup());
				this.gateway.send(msg);
			} else {
				this.wait_queue.add(msg);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		logger.info("at update  messages");
		
		if (o instanceof Message && arg == StatusCode.COMPLETE) {

			this.gateway.getProcessing().remove(o);
			numberOfResources.incrementAndGet();

			if (this.wait_queue.size() > 0) {
				Message m = schedulerStrategy.selectFromQueue(this, wait_queue);
				this.wait_queue.remove(m);
				this.scheduleMessage(m);
			}

		}
	}

}
