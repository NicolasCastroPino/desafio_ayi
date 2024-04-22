package com.ayigroup.desafio.entities;

import com.ayigroup.desafio.enums.Estado;
import com.ayigroup.desafio.enums.Tipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Usuario usuario;
    private Estado estado;
    private Tipo tipo;
}
