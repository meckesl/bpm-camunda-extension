package com.louismeckes.camunda;

import com.github.kkuegler.PermutationBasedHumanReadableIdGenerator;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import java.util.List;

public class HiDelegate implements JavaDelegate {

	private static final String PROCESS_DEFINITION_KEY = "lolcamunda";

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.setVariable("hi", new java.util.Random().nextBoolean());

		RuntimeService rs = execution.getProcessEngine().getRuntimeService();

		Long count = rs.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).count();
		if (count > 0) {
			List<ProcessInstance> all = rs.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).list();
			all.forEach(i -> {
				Integer d = all.indexOf(i);
				execution.setVariable("inst-" + d,
						all.get(d).getBusinessKey() + ", " +
						execution.getProcessDefinitionId());
			});
		}

	}

}
