package com.louismeckes.camunda;

import com.github.kkuegler.PermutationBasedHumanReadableIdGenerator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class HiDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.setVariable("hi", new java.util.Random().nextBoolean());
		execution.setProcessBusinessKey(new PermutationBasedHumanReadableIdGenerator().generate());

	}

}