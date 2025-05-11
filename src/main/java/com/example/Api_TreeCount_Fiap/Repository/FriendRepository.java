package com.example.Api_TreeCount_Fiap.Repository;

import com.example.Api_TreeCount_Fiap.Models.FriendModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<FriendModel, Long> {
    Optional<FriendModel> findByUser1IdAndUser2IdAndIsPendingTrue(String user1Id, String user2Id);

    List<FriendModel> findByUser1IdOrUser2IdAndIsPendingFalse(String userId, String userId1);
}
