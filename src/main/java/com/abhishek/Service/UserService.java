package com.abhishek.Service;

import java.util.List;

import com.abhishek.entity.User;

public interface UserService {

	public void saveUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUser(int id);
	
	public void deleteUser(int id);
	
	
}
