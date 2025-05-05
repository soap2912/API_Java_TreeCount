package com.example.Api_TreeCount_Fiap.Services;


import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.ArvoreDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.ArvoreResponse;
import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import com.example.Api_TreeCount_Fiap.Repository.ArvoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArvoreService {

    @Autowired
    private ArvoreRepository arvoreRepository;

    public ArvoreModel salvar(ArvoreDTO dto) {
        ArvoreModel arvore = new ArvoreModel();
        arvore.setNomePopular(dto.getNomePopular());
        arvore.setNomeCientifico(dto.getNomeCientifico());
        arvore.setDescricao(dto.getDescricao());
        arvore.setFormulaCarbono(dto.getFormulaCarbono());
        arvore.setTipo(dto.getTipo());
        return arvoreRepository.save(arvore);
    }

    public List<ArvoreResponse> listarTodas() {
        return arvoreRepository.findAll()
                .stream()
                .map(ArvoreResponse::new)
                .collect(Collectors.toList());
    }

    public Optional<ArvoreResponse> buscarPorId(Long id) {
        return arvoreRepository.findById(id)
                .map(ArvoreResponse::new);
    }

    public void deletar(Long id) {
        arvoreRepository.deleteById(id);
    }
}
