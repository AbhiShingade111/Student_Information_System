package com.abhishek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhishek.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u  where u.email =:email")
	public User findByEmail(String email);
	
	@Query("select u from User u where u.role =:role")
	public List<User> findByRole(String role);
}
