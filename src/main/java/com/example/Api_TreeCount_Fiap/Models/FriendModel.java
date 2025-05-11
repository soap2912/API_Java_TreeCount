package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "amizade")
@Getter @Setter
public class FriendModel extends ModelBase {

    @Column(name = "user1_id")
    private String user1_id;

    @Column(name = "user2_id")
    private String user2_id;

    @Column(name = "is_pending", nullable = false)
    private boolean isPending;

}

