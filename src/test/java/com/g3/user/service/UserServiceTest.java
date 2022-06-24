package com.g3.user.service;

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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.g3.user.dao.UserDao;
import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.exception.customException.UserNotFoundException;
import com.g3.user.model.User;
import com.g3.user.service.impl.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;


    @Test
    void itShouldDeleteUserWithValidId() throws UserNotFoundException {
        // Given
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");
        Optional<User> optionalUser = Optional.of(user);

        // When
        when(userDao.findById(10L)).thenReturn(optionalUser);
        userService.delete(10L);

        // Then
        verify(userDao).deleteById(10L);
    }

    @Test
    void itShouldThrowsAnUserNotFoundExceptionWhenAnInvalidIdIsPassedInFindByIdMethod() throws UserNotFoundException {
        // When
        when(userDao.findById(10L)).thenThrow(UserNotFoundException.class);

        // Then
        assertThrows(UserNotFoundException.class, () -> userService.delete(10L));

        verify(userDao, never()).deleteById(10L);
    }

    @Test
    void itShouldCreateAnewUserWithValidParameters() throws CpfOrEmailInUseException {
        // Given
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");

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
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");

        // When
        when(userDao.findByCpf(user.getCpf())).thenThrow(CpfOrEmailInUseException.class);
        
        // Then
        assertThrows(CpfOrEmailInUseException.class, () -> userService.register(user));

        verify(userDao, never()).save(any());
    }

    @Test
    void itShouldThrowsAnCpfOrEmailInUseExceptionIfEmailAlreadyExists() throws CpfOrEmailInUseException {
        // Given
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");

        // When
        when(userDao.findByEmail(user.getEmail())).thenThrow(CpfOrEmailInUseException.class);
        
        // Then
        assertThrows(CpfOrEmailInUseException.class, () -> userService.register(user));

        verify(userDao, never()).save(any());
    }

    @Test
    void itShouldReturnAuserRelatedToAvalidCpf() {
        // Given
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");

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
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");

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
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");
        Optional<User> optionalUser = Optional.of(user);

        // When
        when(userDao.findById(10L)).thenReturn(optionalUser);

        userService.searchById(10L);

        // Then
        assertEquals(10, optionalUser.get().getId());
    }

    @Test
    void itShouldThrowsAnUserNotFoundExceptionForFindByIdMethod() {
        // When
        when(userDao.findById(10L)).thenThrow(UserNotFoundException.class);

        //then
        assertThrows(UserNotFoundException.class, () -> userService.searchById(10L));
    }

    @Test
    void ItShouldSearchAndReturnAuserByHisName() {
        // Given
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");

        List<User> usersList = new ArrayList<User>();
        usersList.add(user);

        // When
        when(userDao.getUsersByName(user.getName())).thenReturn(usersList);

        userService.searchByName(user.getName());

        // Then
        verify(userDao).getUsersByName(any());
    }

    @Test
    void itShouldUpdateUserInfo() {
        // Given
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");
        Optional<User> optionalUser = Optional.of(user);

        // When
        when(userDao.findById(10L)).thenReturn(optionalUser);

        userService.update(user, 10L);

        // Then
        verify(userDao).save(any());
    }
}
