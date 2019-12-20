package com.flexipgroup.sender_context;
 
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.sender_strategy.SenderStrategy;


public class SenderContext { 
	private SenderStrategy strategy;

	public void setStrategy(SenderStrategy _strategy) {
		this.strategy = _strategy;
	}
	
	public void execute() {
		try {
			strategy.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
