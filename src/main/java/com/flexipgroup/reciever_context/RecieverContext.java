package com.flexipgroup.reciever_context;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.reciever_strategy.RecieverStrategy;


public class RecieverContext {
	private RecieverStrategy strategy;

	public void setStrategy(RecieverStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void execute() throws IOException, TimeoutException {
		strategy.execute();
	}

}
