package com.projects.rest.webservices.learnrest.repo;

import com.projects.rest.webservices.learnrest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
