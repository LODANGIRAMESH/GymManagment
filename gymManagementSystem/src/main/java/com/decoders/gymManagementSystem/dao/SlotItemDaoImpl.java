package com.decoders.gymManagementSystem.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.decoders.gymManagementSystem.bean.SlotItem;
import com.decoders.gymManagementSystem.bean.SlotItemEmbed;

@Service
@Repository
public class SlotItemDaoImpl implements SlotItemDao {
	@Autowired
	private SlotItemRepository repository;
	@Override
	public void saveSlotItem(SlotItem slotItem) {
		// TODO Auto-generated method stub
		repository.save(slotItem);
		
	}
	@Override
	
	public Integer findSeatBookedById(SlotItemEmbed id) {
		// TODO Auto-generated method stub
		return repository.findSeatBookedById(id);
	}
	@Override
	public Set<SlotItemEmbed> findAllEmbeds() {
		// TODO Auto-generated method stub
		return repository.findAllEmbeds();
	}
	/*@Override
	public SlotItem findItemById(SlotItemEmbed embed) {
		// TODO Auto-generated method stub
		return repository.findItemById();
	}*/
	@Override
	public SlotItem findItemById(SlotItemEmbed id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}
}
