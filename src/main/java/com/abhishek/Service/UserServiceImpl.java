package com.abhishek.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.entity.Marksheet;
import com.abhishek.entity.User;
import com.abhishek.repo.UserJpaRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	public UserServiceImpl(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public void saveUser(User user) {

		userJpaRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userJpaRepository.findAll();
	}

	@Override
	public User getUser(int id) {
		
		User theUser = null;
		
		Optional<User> result = userJpaRepository.findById(id);
		
		if(result.isPresent()) {
			theUser = result.get();
		}
		else {
			throw new RuntimeException("User not found");
		}
		
		return theUser;
	}

	@Override
	public void deleteUser(int id) {
		userJpaRepository.deleteById(id);

	}

	

}
