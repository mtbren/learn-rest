package com.projects.rest.webservices.learnrest.controller;

import com.projects.rest.webservices.learnrest.entity.Name;
import com.projects.rest.webservices.learnrest.entity.PersonV1;
import com.projects.rest.webservices.learnrest.entity.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    @GetMapping(path="/v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Amitabh Bachhan");
    }

    @GetMapping(path="/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Abhishek","Bachhan"));
    }
}
