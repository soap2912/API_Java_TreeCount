package com.example.Api_TreeCount_Fiap.Controllers;

import com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO.CreateHistoricoDTO;
import com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO.CreateHistoricoResponseDTO;
import com.example.Api_TreeCount_Fiap.Services.HistoricoService;
import com.example.Api_TreeCount_Fiap.Services.Util.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listAllById(@PathVariable String id) {

        if (StringService.isNullOrWhiteSpace(id)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Id inválido"));
        }

        var responseDTO = historicoService.buscarPorUsuario(id);

        if (!responseDTO.isSuccess()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(StringService.toMapStringString(responseDTO));
        }

        return ResponseEntity.ok(responseDTO.getHistorico());
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateHistoricoDTO model) {
        var responseDTO = new CreateHistoricoResponseDTO();

        // Verificação de campos vazios
        if (StringService.hasEmptyStringFields(model)) {
            responseDTO.setSuccess(Boolean.FALSE);
            responseDTO.setMessage("Preencha todos os campos");

            return ResponseEntity.badRequest().body(StringService.toMapStringString(responseDTO));
        }

        try {
            var historico = historicoService.create(model);

            return ResponseEntity.status(HttpStatus.CREATED).body(StringService.toMapStringString(historico));
        } catch (Exception e) {
            responseDTO.setSuccess(Boolean.FALSE);
            responseDTO.setMessage("Erro ao criar histórico: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringService.toMapStringString(responseDTO));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable long id) {
        Map<String, String> response;

        if (id <= 0) {
            response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "ID inválido para exclusão");
            return ResponseEntity.badRequest().body(response);
        }

        var responseDTO = historicoService.deleteHistorico(id);
        response = StringService.toMapStringString(responseDTO);

        if (!responseDTO.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
