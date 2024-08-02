
package com.decoders.gymManagementSystem.dao;

import java.util.List;

import com.decoders.gymManagementSystem.bean.Feedback;

public interface FeedBackDao {	
	public void save(Feedback feedback);
	public Long generateFeedbackId();
	public List<Feedback> findAll();

}
