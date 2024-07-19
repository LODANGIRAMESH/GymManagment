package com.decoders.gymManagementSystem.dao;

import java.util.Set;

import com.decoders.gymManagementSystem.bean.SlotItem;
import com.decoders.gymManagementSystem.bean.SlotItemEmbed;

public interface SlotItemDao {
	public void saveSlotItem(SlotItem slotItem);

	public Integer findSeatBookedById(SlotItemEmbed id);

	public Set<SlotItemEmbed> findAllEmbeds();

	public SlotItem findItemById(SlotItemEmbed embed);
}
