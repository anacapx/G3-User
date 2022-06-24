package com.g3.user.service.interfaces;

import java.util.List;

import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.exception.customException.UserNotFoundException;
import com.g3.user.model.User;

public interface IUserService {
    public User register(User user) throws CpfOrEmailInUseException;
    public List<User> getAll(int page, int size);
    public User update(User newUserData, Long userId) throws UserNotFoundException;
    public void delete(Long id) throws UserNotFoundException;
    public List<User> searchByName(String name);
    public User searchById(Long id) throws UserNotFoundException;
    public List<User> searchByCPF(String CPF);
    public List<User> searchByEmail(String email);
}
