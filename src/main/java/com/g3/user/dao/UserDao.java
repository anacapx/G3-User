package com.g3.user.dao;

import com.g3.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByCpfContaining(String cpf);
    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByCpf(String cpf);
    List<User> findByEmailIgnoreCase(String email);
}
