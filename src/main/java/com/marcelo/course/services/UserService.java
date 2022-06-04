package com.marcelo.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marcelo.course.entities.User;
import com.marcelo.course.entities.exceptions.DataBaseException;
import com.marcelo.course.entities.exceptions.ResourceNotFoundException;
import com.marcelo.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long Id) {
		Optional<User> obj = repository.findById(Id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(Id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
	} catch (EmptyResultDataAccessException e) {
		throw new ResourceNotFoundException(id);
	}catch (DataIntegrityViolationException e) {
		throw new DataBaseException(e.getMessage());
	}
		
	}
	
	public User update(Long id , User obj) {
		try {
			User entity = repository.getOne(id);
			UpdateData(entity,obj);
			return repository.save(entity);
			
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void UpdateData(User entity, User obj) {
		
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
