package com.DXC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DXC.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	User getUserByUsername(String username);
}
