package com.ozymern.spring.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozymern.spring.rest.entity.User;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	// metodos personalizados
	public User findByEmail(String email);

	public User findBySex(String sex);

	public User findUserById(Integer id);

	public User findByAgeAndName(int age, String name);

	public List<User> findByNameOrderByAge(String name);

}
