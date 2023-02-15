package com.projects.rest.webservices.learnrest.controller;

import com.projects.rest.webservices.learnrest.entity.User;
import com.projects.rest.webservices.learnrest.exception.UserNotFoundException;
import com.projects.rest.webservices.learnrest.service.UserDAOService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController1 {

    //@Autowired
    private UserDAOService userDAOService;

    public UserController1(UserDAOService daoService){
        this.userDAOService = daoService;
    }

    //@RequestMapping(method = RequestMethod.GET, path="/users")
    //@GetMapping(path="/users")
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDAOService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        User user = userDAOService.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id="+id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
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
