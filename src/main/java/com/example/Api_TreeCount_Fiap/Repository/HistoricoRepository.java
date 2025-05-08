package com.example.Api_TreeCount_Fiap.Repository;

import com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO.ListHistoricoByIdResponse;
import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import com.example.Api_TreeCount_Fiap.Models.HistoricoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel, Long> {

    List<HistoricoModel> findByUserId(String userId);

}
