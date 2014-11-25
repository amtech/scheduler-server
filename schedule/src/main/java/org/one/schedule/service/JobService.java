package org.one.schedule.service;

import org.one.schedule.model.Job;
import org.one.schedule.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
	@Autowired
	private JobRepository jobRepository;

	public List<Job> list(){
		return jobRepository.list();
	}
	
	public List<Job> listByAppId(String appId){
		return jobRepository.listByAppId(appId);
	}
	
	public Job getJob(String jobId){
		return jobRepository.findOne(jobId);
	}
	 
	public void saveJobs( List<Job> jobs){
		jobRepository.save(jobs);
	}
	
	public void save(Job job){
		jobRepository.save(job);
	}
}
