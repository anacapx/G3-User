package com.g3.user.controller;

import com.g3.user.exception.customException.*;
import com.g3.user.model.User;
import com.g3.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try{
            List<User> users = service.getAll();
            return ResponseEntity.ok(users);

        } catch (Exception e){
            throw new ErrorException(e.getMessage());
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> searchUserById(@PathVariable Integer userId) {
        try{
            User user = service.searchById(userId);
            return ResponseEntity.ok(user);

        } catch (UserNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());

        } catch (Exception e){
            throw new ErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user, UriComponentsBuilder uriBuilder){
        try {
            service.register(user);
            URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).body(user);

        }catch(CpfOrEmailInUseException e){
            throw new BadRequestException(e.getMessage());
        }
        catch(Exception e){
            throw new ErrorException(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @Valid @RequestBody User newUserData, UriComponentsBuilder uriBuilder){
        try {
            service.update(newUserData, userId);

            return ResponseEntity.noContent().build();

        }catch(UserNotFoundException e){
            throw new BadRequestException(e.getMessage());
        }
        catch(Exception e){
            throw new ErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userId) {
        try{
            service.delete(userId);
            return ResponseEntity.ok().build();

        } catch (UserNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());

        } catch (Exception e){
            throw new ErrorException(e.getMessage());
        }
    }

    @GetMapping("search")
    public ResponseEntity<List<User>> searchUserByParameters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf ) {

        List<User> users = new ArrayList<>();
        try{

            if(email == null && name == null && cpf == null){
                throw new BadRequestException("Algum parâmetro deve ser informado." );
            }

            if(name != null){
                users = service.searchByName(name);
            }

            if(email != null){
                users = service.searchByEmail(email);
            }

            if(cpf != null){
                users = service.searchByCPF(cpf);
            }

            return ResponseEntity.ok(users);

        }
        catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ErrorException(e.getMessage());
        }
    }

    // ------ Outra forma de fazer as buscas a seguir. Apagar quando refatorar, se for o caso.

//    @GetMapping("getbyname/{userName}")
//    public ResponseEntity<List<User>> searchUserByName(@PathVariable String userName) {
//        try{
//            List<User> users = service.searchByName(userName);
//            return ResponseEntity.ok(users);
//
//        } catch (Exception e){
//            throw new ErrorException(e.getMessage());
//        }
//    }
//
//    @GetMapping("getbycpf/{cpf}")
//    public ResponseEntity<List<User>> searchUserByCpf(@PathVariable String cpf) {
//        try{
//            List<User> users = service.searchByCPF(cpf);
//            return ResponseEntity.ok(users);
//
//        } catch (Exception e){
//            throw new ErrorException(e.getMessage());
//        }
//    }
//
//
//    @GetMapping("getbyemail/{email}")
//    public ResponseEntity<List<User>> searchUserByEmail(@PathVariable String email) {
//        try{
//            List<User> users = service.searchByEmail(email);
//            return ResponseEntity.ok(users);
//
//        } catch (Exception e){
//            throw new ErrorException(e.getMessage());
//        }
//    }



}
