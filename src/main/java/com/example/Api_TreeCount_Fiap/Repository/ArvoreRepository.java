package com.example.Api_TreeCount_Fiap.Repository;

import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArvoreRepository extends JpaRepository<ArvoreModel, Long> {
    // Aqui você pode adicionar métodos customizados, se quiser


}
