package com.decoders.gymManagementSystem.bean;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "slot_item")
public class SlotItem {

	@EmbeddedId
	private SlotItemEmbed embeddedId;
	
	private Integer seatBooked;

	
	public SlotItem() {
		super();
		seatBooked = 0;
	}
	public  SlotItem(SlotItemEmbed embeddedId) {
		super();
		this.embeddedId=embeddedId;
		this.seatBooked=0;
	}
	public SlotItem(SlotItemEmbed embeddedId, Integer seatBooked) {
		super();
		this.embeddedId = embeddedId;
		this.seatBooked = seatBooked;
	}

	public SlotItemEmbed getEmbeddedId() {
		return embeddedId;
	}

	public void setEmbeddedId(SlotItemEmbed embeddedId) {
		this.embeddedId = embeddedId;
	}

	public Integer getSeatBooked() {
		return seatBooked;
	}

	public void setSeatBooked(Integer seatBooked) {
		this.seatBooked = seatBooked;
	}

	

}
