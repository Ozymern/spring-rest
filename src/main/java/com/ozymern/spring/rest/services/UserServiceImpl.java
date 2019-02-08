package com.ozymern.spring.rest.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ozymern.spring.rest.entity.User;
import com.ozymern.spring.rest.repositories.UserRepository;

@Service("userServiceImp")
@Transactional
public class UserServiceImpl implements UserService {

	// inyecto el repositorio para poder obtener los metodos del crud, gracias a
	// JpaRepository
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Override
	public List<User> listAllUser() {

		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User updateUser(User user, Integer id) {

		return (getUserById(id) != null) ? userRepository.save(user) : null;
	}

	@Override
	public void deleteUser(User user) {
		// validar si existe el usuario lo eliminara
		if (this.getUserById(user.getId()) != null) {
			userRepository.delete(user);
		}

	}

}
