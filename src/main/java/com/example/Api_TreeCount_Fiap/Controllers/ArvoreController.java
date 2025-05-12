package com.example.Api_TreeCount_Fiap.Controllers;


import com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO.*;
import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.Services.ArvoreService;
import com.example.Api_TreeCount_Fiap.Services.Util.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/arvore")
public class ArvoreController {

    @Autowired
    private ArvoreService arvoreService;

    @Autowired
    private StringService stringService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateArvoreDTO arvoreDto) {

        if (arvoreDto == null || StringService.hasEmptyStringFields(arvoreDto)) {

            Map<String, String> response = new HashMap<>();

            response.put("success", Boolean.FALSE.toString());
            response.put("message", "Preencha todos os campos");

            return ResponseEntity.badRequest().body(response);
        }

        CreateArvoreResponseDTO responseDTO = arvoreService.salvar(arvoreDto);

        if (!responseDTO.isSuccess()) {

            Map<String, String> response = StringService.toMapStringString(responseDTO);
            return ResponseEntity.badRequest().body(response);
        }

        Map<String, String> response = new HashMap<>();

        response.put("success", Boolean.TRUE.toString());
        response.put("message", "Árvore criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<ListAllResponseDTO> list() {

       var responseDTO = new ListAllResponseDTO();

        try {
            // Chama o serviço para obter a lista de árvores
            responseDTO = arvoreService.listarTodas();

            // Retorna a resposta com status 200 (OK)
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setSuccess(false);
            responseDTO.setMessage("Erro ao carregar a lista de árvores: " + e.getMessage());

            // Retorna a resposta com status 500 (Erro Interno)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> buscar(@PathVariable Long id) {
        if (id < 1) {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Informe um ID válido");
            return ResponseEntity.badRequest().body(response);
        }

        GetArvoreByIdResponseDTO responseDTO = arvoreService.buscarPorId(id);

        if (!responseDTO.isSuccess()) {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Árvore não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(responseDTO.getArvore());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBaseDTO> delete(@PathVariable Long id) {
        ResponseBaseDTO responseDTO = new ResponseBaseDTO();

        try {
            if (id < 1) {
                // Verifica se o ID é válido
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Informe um ID válido");
                return ResponseEntity.badRequest().body(responseDTO);
            }

            // Chama o serviço para deletar a árvore, agora retornando um ResponseBaseDTO
            responseDTO = arvoreService.deletar(id);

            if (!responseDTO.isSuccess()) {
                // Se a árvore não foi deletada com sucesso (erro no serviço)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
            }

            // Se a árvore foi deletada com sucesso
            return ResponseEntity.noContent().build();  // Retorna status 204 (Sem conteúdo)

        } catch (Exception e) {
            // Captura qualquer exceção e retorna um erro interno
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Erro ao excluir árvore: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseBaseDTO> edit(@PathVariable Long id, @RequestBody CreateArvoreDTO dto) {
        ResponseBaseDTO response = arvoreService.update(id, dto);

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

}
