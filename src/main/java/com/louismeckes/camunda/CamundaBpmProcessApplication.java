package com.louismeckes.camunda;

import java.util.HashMap;
import java.util.Map;

import com.github.kkuegler.PermutationBasedHumanReadableIdGenerator;
import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  private static final String PROCESS_DEFINITION_KEY = "lolcamunda";

  private static final PermutationBasedHumanReadableIdGenerator IDGEN = new PermutationBasedHumanReadableIdGenerator();

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {

        // start an initial process instance
	    //Map<String, Object> variables = new HashMap<String, Object>();
        processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, IDGEN.generate());

  }

}
