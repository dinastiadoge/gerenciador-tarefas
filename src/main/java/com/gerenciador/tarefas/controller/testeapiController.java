package com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testeapiController {


    @GetMapping("/teste-api")
    private String teste(){
        return "Sucesso";
    }

}
