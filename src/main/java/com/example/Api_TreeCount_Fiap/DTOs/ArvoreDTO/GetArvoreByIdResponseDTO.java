package com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO;

import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetArvoreByIdResponseDTO extends ResponseBaseDTO {

    private ArvoreModel arvore;

    public GetArvoreByIdResponseDTO(ArvoreModel arvore) {
        this.arvore = arvore;
    }
    public GetArvoreByIdResponseDTO() {

    }
}
