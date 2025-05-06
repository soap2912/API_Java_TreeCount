package com.example.Api_TreeCount_Fiap.Controllers;


import com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO.*;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.ArvoreResponse;
import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import com.example.Api_TreeCount_Fiap.Services.ArvoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/arvores")
public class ArvoreController {

    @Autowired
    private ArvoreService arvoreService;

    @PostMapping("/create")
    public ResponseEntity<ArvoreResponse> criar(@RequestBody ArvoreDTO arvoreDto) {
        ArvoreModel arvore = arvoreService.salvar(arvoreDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ArvoreResponse(arvore));
    }

    @GetMapping()
    public ResponseEntity<List<ArvoreResponse>> listar() {
        return ResponseEntity.ok(arvoreService.listarTodas());
    }

    @GetMapping("/search")
    public ResponseEntity<ArvoreResponse> buscar(@PathVariable Long id) {
        return arvoreService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        arvoreService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
