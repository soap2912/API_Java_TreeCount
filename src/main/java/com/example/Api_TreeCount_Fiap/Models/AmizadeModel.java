package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "amizade")
public class AmizadeModel extends ModelBase {

    @Column(name = "user1_id")
    private String user1_id;

    @Column(name = "user2_id")
    private String user2_id;

    @Column(name = "is_pending", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isPending;

    // Getter e Setter para 'user1_id'
    public String getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(String user1_id) {
        this.user1_id = user1_id;
    }

    // Getter e Setter para 'user2_id'
    public String getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(String user2_id) {
        this.user2_id = user2_id;
    }

    // Getter e Setter para 'isPending'
    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean isPending) {
        this.isPending = isPending;
    }
}

