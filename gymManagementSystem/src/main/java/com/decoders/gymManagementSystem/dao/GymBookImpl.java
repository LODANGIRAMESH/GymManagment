package com.decoders.gymManagementSystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.decoders.gymManagementSystem.bean.GymBook;

@Service
@Repository

public class GymBookImpl implements GymBookDao {
	@Autowired
	private GymBookRepository repository;
	
	@Override
	@Transactional
	public void save(GymBook gymBook) {
		repository.save(gymBook);
	}
	@Override
	public Long generateBookingId() {
		// TODO Auto-generated method stub
		Long newId=repository.findLastBookingId();
		if(newId==null) {
			newId=100000L;
		}
		else {
			newId=newId+1L;
		}
		return newId;
	}
	@Override
	public List<GymBook> getBookList(){
		return repository.findAll();
	}

	@Override
	public GymBook findBookInfoById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	@Override
	public List<GymBook> getEntitiesByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.findByUsername(username);
	}

}
