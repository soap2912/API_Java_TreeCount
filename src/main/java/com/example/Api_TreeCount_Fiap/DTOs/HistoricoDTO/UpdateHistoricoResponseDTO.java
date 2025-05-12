package com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateHistoricoResponseDTO {

    private String id;
    private Double latitude;
    private Double longitude;
    private Double raioPlantio;
    private Integer quantidade;
    private String arvoreId;
    private String userId;
}