package com.decoders.gymManagementSystem.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.decoders.gymManagementSystem.bean.Feedback;

 
@Service
@Repository
public class FeedBackDaoImpl implements FeedBackDao {
	
	@Autowired
	private FeedBackRepository repository;
	@Override
	public void save(Feedback feedback) {
		repository.save(feedback);

	}
	@Override
	public Long generateFeedbackId() {
		Long newId=repository.findLastFeedbackId();
		if(newId==null)
		{
			newId=1L;
		}
		else {
			newId=newId+1L;
		}
		return newId;
	}
	@Override
	public List<Feedback> findAll() {
		return repository.findAll();
	}
}