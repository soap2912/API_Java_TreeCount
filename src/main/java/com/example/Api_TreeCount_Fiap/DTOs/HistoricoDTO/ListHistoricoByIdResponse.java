package com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO;

import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.Models.HistoricoModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListHistoricoByIdResponse extends ResponseBaseDTO {

    private  List<HistoricoModel> historico;
}
