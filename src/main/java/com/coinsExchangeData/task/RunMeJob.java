package com.coinsExchangeData.task;

import java.util.ArrayList;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class RunMeJob implements Job, JobListener {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello Quartz!");
		
		String instName  = context.getJobDetail().getName();
	    String instGroup = context.getJobDetail().getGroup();
	    
	    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
	    String jobSays = dataMap.getString("jobSays");
      float myFloatValue = dataMap.getFloat("myFloatValue");
      ArrayList state = (ArrayList)dataMap.get("myStateData");
      state.add(new Date());

      System.err.println("Instance " + instName + " of DumbJob says: " + jobSays);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		System.out.println("jobToBeExecuted");
		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		System.out.println("jobWasExecuted");
		
	}

	
}
