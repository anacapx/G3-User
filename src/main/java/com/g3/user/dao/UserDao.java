package com.g3.user.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.model.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	@Query(value = "select * from tb_user where user_name like %:name%", nativeQuery = true)
	public List<User> getUsersByName(String name);
	
	public List<User> findByCpfContaining(String cpf);
	
	public List<User> findByEmailContainingIgnoreCase(String email);
	
	public List<User> findByCpf(String cpf) throws CpfOrEmailInUseException;
	
	public List<User> findByEmail(String email) throws CpfOrEmailInUseException;
	
	public List<User> findByEmailIgnoreCase(String email);
	
	public Page<User> findAll(Pageable pageable);
}
