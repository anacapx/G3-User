package com.g3.user.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.g3.user.exception.customException.CpfOrEmailInUseException;
import com.g3.user.exception.customException.ResourceNotFoundException;
import com.g3.user.exception.customException.UserNotFoundException;
import com.g3.user.model.User;
import com.g3.user.service.impl.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
	private MockMvc mockMvc; 

    @MockBean
    private UserService userService;

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).delete(3L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/3"))
               .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(userService).delete(3L);
    }

    @Test
    void testDeleteUser2() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).delete(-1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/-1"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        assertThrows(UserNotFoundException.class, () -> userService.delete(-1L));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getall"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print());

        verify(userService).getAll();
    }

    @Test
    void testRegisterUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"Usuário Um\", \"cpf\": \"461.349.700-00\", \"phone\": \"11999991111\", \"birthday\":\"1988-05-13\", \"email\":\"usuarioum@email.com\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isCreated());
  
      verify(userService).register(any(User.class));
    }

    @Test
    void testRegisterUser2() throws Exception {        
        when(userService.register(mock(User.class))).thenThrow(CpfOrEmailInUseException.class);

        this.mockMvc
        .perform(
          MockMvcRequestBuilders.post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"cpf\": \"461.349.700-00\",  \"phone\": \"11999991111\", \"birthday\":\"1988-05-13\", \"email\":\"usuarioum@email.com\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testRegisterUser3() throws Exception {        
        when(userService.register(mock(User.class))).thenThrow(CpfOrEmailInUseException.class);

        this.mockMvc
        .perform(
          MockMvcRequestBuilders.post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"Usuário Um\", \"phone\": \"11999991111\", \"birthday\":\"1988-05-13\", \"email\":\"usuarioum@email.com\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testSearchUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
		       .andExpect(MockMvcResultMatchers.status().isOk());
        
               verify(userService).searchById(1L);
    }

    @Test
    void testSearchUserById2() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).searchById(-1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/-1"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(userService).searchById(-1L);
    }

    @Test
    void testSearchUserById3() throws Exception {
        when(userService.searchById(400L)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/order/400"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(userService, never()).searchById(any());
    }

    @Test
    void testSearchUserByParametersSearchByName() throws Exception {
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");

        List<User> usersList = new ArrayList<User>();
        usersList.add(user);

        when(userService.searchByName(user.getName())).thenReturn(usersList);

        System.out.println(user.getName());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/search?name=" + user.getName()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).searchByName(user.getName());
    }

    @Test
    void testSearchUserByParametersSearchByCpf() throws Exception {
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");
        List<User> usersList = new ArrayList<User>();
        usersList.add(user);

        when(userService.searchByCPF(user.getCpf())).thenReturn(usersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/search?cpf=" + user.getCpf()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).searchByCPF(user.getCpf());
    }

    @Test
    void testSearchUserByParametersSearchByEmail() throws Exception {
        User user = new User(10L, "Usuário Teste", "461.349.700-00", "11999991111", LocalDate.of(2000, 12, 31), "usuarioteste@email.com");
        List<User> usersList = new ArrayList<User>();
        usersList.add(user);

        when(userService.searchByEmail(user.getEmail())).thenReturn(usersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/search?email=" + user.getEmail()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).searchByEmail(user.getEmail());
    }
}
