package com.decoders.gymManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.decoders.gymManagementSystem.bean.Feedback;

public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
    @Query("select max(id) from Feedback")
    public Long findLastFeedbackId();
}
