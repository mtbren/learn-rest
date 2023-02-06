package com.projects.rest.webservices.learnrest.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    MessageSource messageSource;
    public HelloWorldController(MessageSource mSource){
        this.messageSource = mSource;
    }

    @GetMapping(path = "/hello")
    public String getHelloWorld(){
        return "Hello World";
    }
    @GetMapping(path = "/hello-i18n")
    public String getHelloWorldI18N(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null, "Default message !!!",locale);

    }
}
