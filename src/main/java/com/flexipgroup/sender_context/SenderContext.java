package com.flexipgroup.sender_context;
 
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.sender_strategy.SenderStrategy;


public class SenderContext { 
	private SenderStrategy strategy;

	public void setStrategy(SenderStrategy _strategy) {
		this.strategy = _strategy;
	}
	
	public void execute() throws IOException, TimeoutException {
		strategy.execute();
	}
}
