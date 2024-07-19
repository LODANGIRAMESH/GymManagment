package com.decoders.gymManagementSystem.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class SlotItemEmbed implements Serializable {

	@NotNull
	private Long slotId;
	@NotNull
	private Long itemId;

	public SlotItemEmbed(@NotNull Long slotId, @NotNull Long itemId) {
		super();
		this.slotId = slotId;
		this.itemId = itemId;
	}

	public SlotItemEmbed() {
		super();

	}
	public Long getItemId() {
		// TODO Auto-generated method stub
		return itemId;
	}

	public Long getSlotId() {
		// TODO Auto-generated method stub
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	@Override
	public int hashCode() {
		String ss = "" + slotId + itemId;
		int val = Integer.parseInt(ss);
		return val;
	}

	@Override
	public boolean equals(Object obj) {
		SlotItemEmbed other = (SlotItemEmbed) obj;
		if (this.hashCode() == other.hashCode())
			return true;
		else
			return false;
	}
}
