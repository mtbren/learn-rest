package com.projects.rest.webservices.learnrest.repo;

import com.projects.rest.webservices.learnrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
