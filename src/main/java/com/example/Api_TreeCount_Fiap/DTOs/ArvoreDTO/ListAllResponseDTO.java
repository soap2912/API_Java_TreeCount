package com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO;

import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListAllResponseDTO extends ResponseBaseDTO {

    private List<ArvoreModel> arvores;
}
