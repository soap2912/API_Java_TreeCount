package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Grupo")
public class GrupoModel extends ModelBase<Long> {

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "grupo_id")
    private String grupo_id;

    // Getter e Setter para 'nome'
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para 'descricao'
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter e Setter para 'user_id'
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    // Getter e Setter para 'grupo_id'
    public String getGrupo_id() {
        return grupo_id;
    }
    public void setGrupo_id(String grupo_id) {
        this.grupo_id = grupo_id;
    }
}
