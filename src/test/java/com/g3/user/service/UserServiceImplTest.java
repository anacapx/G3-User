package com.g3.user.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.g3.user.dao.UserDao;
import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.exception.customException.UserNotFoundException;
import com.g3.user.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userDao);
    }


    @Test
    void itShouldDeleteUserWithValidId() throws UserNotFoundException {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");
        Optional<User> optionalUser = Optional.of(user);
        int id = 10;

        // When
        when(userDao.findById(id)).thenReturn(optionalUser);
        userService.delete(id);

        // Then
        verify(userDao).deleteById(id);
    }

    @Test
    void itShouldThrowsAnUserNotFoundExceptionWhenAnInvalidIdIsPassedInFindByIdMethod() throws UserNotFoundException {
        int id = 10;

        // When
        when(userDao.findById(id)).thenThrow(UserNotFoundException.class);

        // Then
        assertThrows(UserNotFoundException.class, () -> userService.delete(id));

        verify(userDao, never()).deleteById(id);
    }

    @Test
    void itShouldReturnAlistOfUsers() {
        // When
        userService.getAll();

        // Then
        verify(userDao).findAll();
    }

    @Test
    void itShouldAddAnewUserWithValidParameters() throws CpfOrEmailInUseException {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");

        // When
        userService.register(user);

        // Then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        
        verify(userDao).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
    
        assertEquals(user, capturedUser);
    }

    @Test
    void itShouldThrowsAnCpfOrEmailInUseExceptionIfCpfAlreadyExists() throws CpfOrEmailInUseException {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");

        // When
        when(userDao.findByCpf(user.getCpf())).thenThrow(CpfOrEmailInUseException.class);
        
        // Then
        assertThrows(CpfOrEmailInUseException.class, () -> userService.register(user));

        verify(userDao, never()).save(any());
    }

    @Test
    void itShouldThrowsAnCpfOrEmailInUseExceptionIfEmailAlreadyExists() throws CpfOrEmailInUseException {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");

        // When
        when(userDao.findByEmail(user.getEmail())).thenThrow(CpfOrEmailInUseException.class);
        
        // Then
        assertThrows(CpfOrEmailInUseException.class, () -> userService.register(user));

        verify(userDao, never()).save(any());
    }

    @Test
    void itShouldReturnAuserRelatedToAvalidCpf() {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");

        List<User> usersList = new ArrayList<User>();
        usersList.add(user);

        // When
        when(userDao.findByCpfContaining(user.getCpf())).thenReturn(usersList);

        userService.searchByCPF(user.getCpf());

        // Then
        verify(userDao).findByCpfContaining(any());
    }

    @Test
    void itShouldReturnAuserRelatedToAvalidEmail() {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");

        List<User> usersList = new ArrayList<User>();
        usersList.add(user);

        // When
        when(userDao.findByEmailContainingIgnoreCase(user.getEmail())).thenReturn(usersList);

        userService.searchByEmail(user.getEmail());

        // Then
        verify(userDao).findByEmailContainingIgnoreCase(any());
    }

    @Test
    void ItShouldSearchAndReturnAuserByID() {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");
        Optional<User> optionalUser = Optional.of(user);
        int id = 10;

        // When
        when(userDao.findById(id)).thenReturn(optionalUser);

        userService.searchById(id);

        // Then
        assertEquals(10, optionalUser.get().getId());
    }

    @Test
    void itShouldThrowsAnUserNotFoundExceptionForFindByIdMethod() {
        // Given
        int id = 10;

        // When
        when(userDao.findById(id)).thenThrow(UserNotFoundException.class);

        //then
        assertThrows(UserNotFoundException.class, () -> userService.searchById(id));
    }

    @Test
    void ItShouldSearchAndReturnAuserByName() {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");

        List<User> usersList = new ArrayList<User>();
        usersList.add(user);

        // When
        when(userDao.findByNameContainingIgnoreCase(user.getName())).thenReturn(usersList);

        userService.searchByName(user.getName());

        // Then
        verify(userDao).findByNameContainingIgnoreCase(any());
    }

    @Test
    void itShouldUpdateAnUserInfo() {
        // Given
        LocalDate birthDate = LocalDate.of(1988, 05, 13);
        User user = new User(10, "Usuário Um", "461.349.700-00", "11999991111", birthDate, "usuarioum@email.com");
        Optional<User> optionalUser = Optional.of(user);
        int id = 10;

        // When
        when(userDao.findById(id)).thenReturn(optionalUser);

        userService.update(user, id);

        // Then
        verify(userDao).save(any());
    }
}
