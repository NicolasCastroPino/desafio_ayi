package com.ayigroup.desafio.entities;


import com.ayigroup.desafio.enums.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;
@Getter @Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    @OneToMany
    private List<Pedido> pedidos;
    private Rol rol;

}
