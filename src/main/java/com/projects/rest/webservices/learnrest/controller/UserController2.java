package com.projects.rest.webservices.learnrest.controller;

import com.projects.rest.webservices.learnrest.entity.Post;
import com.projects.rest.webservices.learnrest.entity.User;
import com.projects.rest.webservices.learnrest.exception.PostNotFoundException;
import com.projects.rest.webservices.learnrest.exception.UserNotFoundException;
import com.projects.rest.webservices.learnrest.repo.PostRepository;
import com.projects.rest.webservices.learnrest.repo.UserRepository;
import com.projects.rest.webservices.learnrest.service.UserDAOService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController2 {

    //@Autowired
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserController2(UserRepository userRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //@RequestMapping(method = RequestMethod.GET, path="jpa/users")
    //@GetMapping(path="jpa/users")
    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id="+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @GetMapping("/jpa/users/{uid}/posts/{pid}")
    public EntityModel<Post> getUserPost(@PathVariable int uid,@PathVariable int pid){
        Optional<Post> post = postRepository.findById(pid);

        if(post.isEmpty())
            throw new PostNotFoundException("id="+uid);



        EntityModel<Post> entityModel = EntityModel.of(post.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getPostsOfUser(uid));

        entityModel.add(link.withRel("all-posts"));

        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id="+id);

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path="/jpa/users/{id}/posts")
    public List<Post> getPostsOfUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id="+id);

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
