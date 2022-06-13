package com.g3.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.user.dao.UserDao;
import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.exception.customException.UserNotFoundException;
import com.g3.user.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements IUserService{

    @Autowired
    UserDao dao;

    public User register(User user) throws CpfOrEmailInUseException {
        if(!dao.findByCpf(user.getCpf()).isEmpty()){
            throw new CpfOrEmailInUseException();
        }

        if(!dao.findByEmail(user.getEmail()).isEmpty()){
            throw new CpfOrEmailInUseException();
        }

        return dao.save(user);
    }

    public List<User> getAll(){
        return dao.findAll();
    }

    public User update(User newUserData, Integer userId) throws UserNotFoundException {
        if (dao.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = dao.findById(userId).get();

        user.setBirthday(newUserData.getBirthday());
        user.setName(newUserData.getName());
        user.setCpf(newUserData.getCpf());
        user.setEmail(newUserData.getEmail());
        user.setPhone(newUserData.getPhone());

        dao.save(user);

        return user;
    }

    public void delete(Integer id) throws UserNotFoundException {
        if (dao.findById(id).isEmpty()){
            throw new UserNotFoundException();
        }
        dao.deleteById(id);
    }

    public User searchById(Integer id) throws UserNotFoundException {
        if (dao.findById(id).isEmpty()){
            throw new UserNotFoundException();
        }
        return dao.findById(id).get();
    }

    public List<User> searchByName(String name){
        return dao.findByNameContainingIgnoreCase(name);
    }

    public List<User> searchByCPF(String cpf){
        return dao.findByCpfContaining(cpf);
    }

    public List<User> searchByEmail(String email){
        return dao.findByEmailContainingIgnoreCase(email);
    }
}
