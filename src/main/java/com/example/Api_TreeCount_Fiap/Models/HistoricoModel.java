package com.example.Api_TreeCount_Fiap.Models;

import com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO.CreateHistoricoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historico")
@Getter
@Setter

public class HistoricoModel extends ModelBase {

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "raio_plantio", nullable = false)
    private Double raioPlantio;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "arvore_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private String arvoreId;

    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private String userId;

    public HistoricoModel() {
    }

    public HistoricoModel(CreateHistoricoDTO dto) {
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.raioPlantio = dto.getRaioPlantio();
        this.quantidade = dto.getQuantidade();
        this.arvoreId = dto.getArvoreId();
        this.userId = dto.getUserId();
    }
}
