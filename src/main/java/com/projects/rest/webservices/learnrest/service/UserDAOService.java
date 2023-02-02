package com.projects.rest.webservices.learnrest.service;

import com.projects.rest.webservices.learnrest.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOService {
    // Currently JPA > Static List
    private static List<User> users = new ArrayList<>();
    private static Integer userCount = 0;

    static{
        users.add(
                new User(++userCount,"Amit",
                        LocalDate.now().minusYears(40)));
        users.add(
                new User(++userCount,"Neelam",
                        LocalDate.now().minusYears(40)));
        users.add(
                new User(++userCount,"Netra",
                        LocalDate.now().minusYears(11)));
        users.add(
                new User(++userCount,"Sharvi",
                        LocalDate.now().minusYears(10)));
        users.add(
                new User(++userCount,"Hema",
                        LocalDate.now().minusYears(60)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int userId){
        Predicate<? super User> userFinder =
                user -> user.getId()==userId;
        return users.stream().filter(userFinder).findFirst().orElse(null);
    }

    public void deleteById(int userId){
        Predicate<? super User> userFinder =
                user -> user.getId()==userId;
        users.removeIf(userFinder);
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
