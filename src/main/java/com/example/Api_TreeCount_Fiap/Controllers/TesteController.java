package com.example.Api_TreeCount_Fiap.Controllers;

import com.example.Api_TreeCount_Fiap.Repository.ConnectionTester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    private final ConnectionTester conTester;

    public TesteController(ConnectionTester connectionTester) {
        this. conTester = connectionTester;
    }

    @GetMapping("/")
    public String index() {
       return conTester.testarConexao() ? "Arthur é gay" : "Arthur é bixa";
    }

}
