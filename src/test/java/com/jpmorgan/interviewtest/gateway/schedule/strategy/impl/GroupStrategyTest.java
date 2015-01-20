package com.jpmorgan.interviewtest.gateway.schedule.strategy.impl;

import org.junit.Before;

public class GroupStrategyTest extends StrategyHelper{
	
	@Before
	public void init(){
		setSchedulerStrategy(new GroupStrategy());
	}

}
