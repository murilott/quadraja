package com.example.authservice.domain.user;

import com.example.authservice.domain.user.vo.Email;
import com.example.authservice.domain.user.vo.Pagamentos;
import com.example.authservice.domain.user.vo.Role;
import com.example.authservice.domain.user.vo.RoleType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "usuario")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Valid
    @Embedded
    private Pagamentos pagamentosLista;
    // private List<String> pagamentosLista = new ArrayList<String>();

    private List<UUID> reservasLista;

    @Valid
    @Embedded
    private Email email;

    @Embedded
    private Role role;

    public void addPagamento(String pagamentoValue) {
        this.pagamentosLista.add(pagamentoValue);
    }

    public User(String name, @Valid Email email, RoleType role, String password) {
        this.name = name;
        this.email = email;
        this.role = Role.of(role);
        this.password = password;
        this.pagamentosLista = new Pagamentos();
        this.reservasLista = new ArrayList<UUID>();
    }
}
