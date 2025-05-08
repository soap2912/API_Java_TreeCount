package com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHistoricoDTO {

    private Double latitude;
    private Double longitude;
    private Double raioPlantio;
    private Integer quantidade;
    private String arvoreId;
    private String userId;

    public CreateHistoricoDTO() {
    }
}