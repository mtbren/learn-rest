package com.projects.rest.webservices.learnrest.controller;

import com.projects.rest.webservices.learnrest.entity.Name;
import com.projects.rest.webservices.learnrest.entity.PersonV1;
import com.projects.rest.webservices.learnrest.entity.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    // URI versioning
    @GetMapping(path="/v1/person")
    public PersonV1 getPersonURLVersionV1(){
        return new PersonV1("Amitabh Bachhan");
    }

    // URI versioning
    @GetMapping(path="/v2/person")
    public PersonV2 getPersonURLVersioningV2(){
        return new PersonV2(new Name("Abhishek","Bachhan"));
    }

    // Request Parameter versioning
    @GetMapping(path="/person", params="version=1")
    public PersonV1 getPersonRequestParamVersioningV1(){
        return new PersonV1("Akshay Kumar");
    }

    // Request Parameter versioning
    @GetMapping(path="/person", params="version=2")
    public PersonV2 getPersonRequestParamVersioningV2(){
        return new PersonV2(new Name("SaifAli","Khan"));
    }

    // Header Parameter versioning
    @GetMapping(path="/person/header", headers="X-API-VERSION=1")
    public PersonV1 getPersonHeaderParamVersioningV1(){
        return new PersonV1("Twinkle Khanna");
    }

    // Header Parameter versioning
    @GetMapping(path="/person/header", headers="X-API-VERSION=2")
    public PersonV2 getPersonHeaderParamVersioningV2(){
        return new PersonV2(new Name("Kareena","Kapoor"));
    }

    // Media Type versioning
    @GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
    public PersonV1 getPersonMediaTypeVersioningV1(){
        return new PersonV1("ShahRukh Khan");
    }

    // Media Type versioning
    @GetMapping(path="/person/accept", produces="application/vnd.company.app-v2+json")
    public PersonV2 getPersonMediaTypeVersioningV2(){
        return new PersonV2(new Name("Gauri","Khan"));
    }

}
