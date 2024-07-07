package com.learn.todoapp.presentation;

import com.learn.todoapp.domain.model.HelloWorldBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class HelloWorldControllerReact {

    @GetMapping(path = "/basicAuth")
    public String basicAuthCheck(){
        return "Success";
    }

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public String helloWorldPathVariable(@PathVariable String name){
        return String.format("Hello World, %s", name);
    }



}
