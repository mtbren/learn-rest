package com.projects.rest.webservices.learnrest.controller;

import com.projects.rest.webservices.learnrest.entity.User;
import com.projects.rest.webservices.learnrest.exception.UserNotFoundException;
import com.projects.rest.webservices.learnrest.service.UserDAOService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    //@Autowired
    private UserDAOService userDAOService;

    public UserController(UserDAOService daoService){
        this.userDAOService = daoService;
    }

    //@RequestMapping(method = RequestMethod.GET, path="/users")
    //@GetMapping(path="/users")
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDAOService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        User user = userDAOService.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id="+id);

        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userDAOService.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userDAOService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
