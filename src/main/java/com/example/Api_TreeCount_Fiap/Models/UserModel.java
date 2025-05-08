package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false))
public class UserModel extends ModelBase {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String id;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Override
    public void delete() {
        // Garante o soft delete padrão
        super.delete();

        // Evita conflito de email único ao reutilizar o mesmo email no futuro
        if (this.email != null && this.id != null) {
            this.email = this.email + "-" + this.id;
        }
    }
}
