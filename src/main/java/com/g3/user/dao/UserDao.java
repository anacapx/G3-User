package com.g3.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByCpfContaining(String cpf);
    List<User> findByEmailContainingIgnoreCase(String email);
    List<User> findByCpf(String cpf) throws CpfOrEmailInUseException;
    List<User> findByEmail(String email) throws CpfOrEmailInUseException;
    List<User> findByEmailIgnoreCase(String email);
}
