package com.flexipgroup.reciever_strategy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface RecieverStrategy {

	public void execute() throws IOException, TimeoutException;

}
