package com.example.Api_TreeCount_Fiap.Repository;

import com.example.Api_TreeCount_Fiap.Models.FriendModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<FriendModel, Long> {

    // Consulta para verificar amizade pendente entre dois usuários
    @Query("SELECT f FROM FriendModel f WHERE f.user1_id = :user1Id AND f.user2_id = :user2Id AND f.isPending = true")
    Optional<FriendModel> findPendingFriendship(@Param("user1Id") String user1Id, @Param("user2Id") String user2Id);

    // Consulta para buscar todas as amizades de um usuário, tanto como user1 quanto como user2
    @Query("SELECT f FROM FriendModel f WHERE f.user1_id = :userId OR f.user2_id = :userId")
    List<FriendModel> findAllFriendsByUserId(@Param("userId") String userId);
}
