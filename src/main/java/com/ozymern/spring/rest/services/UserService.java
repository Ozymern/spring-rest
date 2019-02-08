package com.ozymern.spring.rest.services;

import java.util.List;

import com.ozymern.spring.rest.entity.User;

public interface UserService {

	public List<User> listAllUser();

	public User addUser(User user);

	public User getUserById(Integer id);

	public User updateUser(User user, Integer id);

	public void deleteUser(User user);

}
