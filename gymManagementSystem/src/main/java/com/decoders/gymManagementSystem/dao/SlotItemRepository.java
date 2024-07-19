package com.decoders.gymManagementSystem.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.decoders.gymManagementSystem.bean.SlotItem;
import com.decoders.gymManagementSystem.bean.SlotItemEmbed;

public interface SlotItemRepository extends JpaRepository<SlotItem, SlotItemEmbed>{
	@Query("SELECT seatBooked FROM SlotItem a WHERE embeddedId=?1")
	public Integer findSeatBookedById(SlotItemEmbed id);
	
	@Query("SELECT embeddedId FROM SlotItem")
	public Set<SlotItemEmbed> findAllEmbeds();
}
