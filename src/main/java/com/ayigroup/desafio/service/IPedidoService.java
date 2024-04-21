package com.ayigroup.desafio.service;

import com.ayigroup.desafio.entities.Pedido;

import java.util.List;

public interface IPedidoService {

    //CRUD PEDIDO


    //Listar todos los pedidos
    public List<Pedido> getPedidos();

    //Crear un pedido
    public void newPedido(Pedido pedido);

    //Traer un pedido por ID
    public Pedido findPedido(Long id);

    //Borrar un pedido
    public void deletePedido(Long id);

    //Editar un pedido
    public void editPedido(Pedido pedido);



}
