package com.willaim.springSecurity.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class UserController {
    @GetMapping("autenticado")
    public String autenticado() {
        return "Usuario authenticado";
    }
    
}
