package com.flexipgroup.sender_strategy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface SenderStrategy {
	public void execute() throws IOException, TimeoutException;
}
