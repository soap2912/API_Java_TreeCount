package com.example.Api_TreeCount_Fiap.Repository;

import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import com.example.Api_TreeCount_Fiap.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface  UserRepository extends JpaRepository<UserModel, Long>{

}
