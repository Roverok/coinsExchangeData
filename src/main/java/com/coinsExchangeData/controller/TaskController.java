package com.coinsExchangeData.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coinsExchangeData.contract.IOrderBookService;
import com.coinsExchangeData.contract.IPairsService;
import com.coinsExchangeData.task.RunMeJob;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;


@Controller
@RequestMapping(value="tasks", method = RequestMethod.GET) 
public class TaskController {
	
	@Autowired
	private IPairsService pairsService;
	@Autowired
	private IOrderBookService orderBookService;
	
	@RequestMapping(value="pairs", method = RequestMethod.GET) 
	public String tasks() {
		pairsService.fetch();
		return "fetch";
	}
	
	@RequestMapping(value="orderbook", method = RequestMethod.GET) 
	public String orderbook() {
		orderBookService.fetch();
		return "fetch";
	}
	
	@RequestMapping(value="test", method = RequestMethod.GET) 
	public String test() {
		
//		JobDetail job = new JobDetail();
//		job.setName("runMeJob");
//    	job.setJobClass(RunMeJob.class);
		JobDetail jobDetail = new JobDetail("runMeJob", RunMeJob.class);
		jobDetail.getJobDataMap().put("jobSays", "Hello World!");
		jobDetail.getJobDataMap().put("myFloatValue", 3.141f);
		jobDetail.getJobDataMap().put("myStateData", new ArrayList());
    	
    	//configure the scheduler time
    	SimpleTrigger trigger = new SimpleTrigger();
    	trigger.setName("runMeJobTrigger");
    	trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
    	trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    	trigger.setRepeatInterval(30000);
    	
		
    	/*CronTrigger trigger = new CronTrigger();
    	trigger.setName("dummyTriggerName");
    	trigger.setCronExpression("0/30 * * * * ?");*/
    	
    	//schedule it
    	try {
    		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        	scheduler.start();
        	scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
		return "test";
	}
	
}
