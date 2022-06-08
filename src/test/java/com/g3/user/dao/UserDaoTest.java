package com.g3.user.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserDaoTest {
    
    @Autowired
    private UserDao userDao;

    @Test
    void testFindByCpf() {

    }

    @Test
    void testFindByCpfContaining() {

    }

    @Test
    void testFindByEmailContainingIgnoreCase() {

    }

    @Test
    void testFindByEmailIgnoreCase() {

    }

    @Test
    void testFindByNameContainingIgnoreCase() {

    }
}
