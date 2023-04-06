package com.socks.socksCounter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping
    public String helloWorld () {
        return "Добро пожаловать в программку обработки и учета носочно-чулочных изделий 'SocksMaster-2000'!";
    }
}
