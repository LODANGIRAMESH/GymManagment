package com.decoders.gymManagementSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.decoders.gymManagementSystem.bean.SlotItem;

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
}
