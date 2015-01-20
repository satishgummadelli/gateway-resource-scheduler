package com.jpmorgan.interviewtest.gateway.schedule.strategy.impl;

import org.junit.Before;


/**
 * top first Scheduling
 * @author Satish Gummadelli
 *
 */
public class TopFirstStrategyTest extends StrategyHelper {
	
	
	@Before
	public void init(){
		setSchedulerStrategy(new TopFirstStrategy());
	}
	
	
	

}
