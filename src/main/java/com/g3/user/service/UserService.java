package com.g3.user.service;

import com.g3.user.exception.customException.UserNotFoundException;
import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.model.User;

import java.util.List;

public interface UserService {

    public User register(User user) throws CpfOrEmailInUseException;
    public List<User> getAll();
    public User update(User newUserData, Integer userId) throws UserNotFoundException;
    public void delete(Integer id) throws UserNotFoundException;
    public List<User> searchByName(String name);
    public User searchById(Integer id) throws UserNotFoundException;
    public List<User> searchByCPF(String CPF);
    public List<User> searchByEmail(String email);
}
