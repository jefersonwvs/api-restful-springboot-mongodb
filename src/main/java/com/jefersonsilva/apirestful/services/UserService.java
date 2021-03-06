package com.jefersonsilva.apirestful.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jefersonsilva.apirestful.domain.User;
import com.jefersonsilva.apirestful.dto.UserDTO;
import com.jefersonsilva.apirestful.repository.UserRepository;
import com.jefersonsilva.apirestful.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User oldObj) {
		User newObj = findById(oldObj.getId());
		updateData(newObj, oldObj);
		return repo.save(newObj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	private void updateData(User newObj, User oldObj) {
		newObj.setName(oldObj.getName());
		newObj.setEmail(oldObj.getEmail());
	}

}
