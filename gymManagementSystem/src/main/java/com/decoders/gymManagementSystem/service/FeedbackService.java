package com.decoders.gymManagementSystem.service;

import com.decoders.gymManagementSystem.bean.Feedback;
import com.decoders.gymManagementSystem.dao.FeedBackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedBackRepository feedbackRepository;

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
