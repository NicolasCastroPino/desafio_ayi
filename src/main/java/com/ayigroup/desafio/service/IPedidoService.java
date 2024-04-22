package com.ayigroup.desafio.service;

import com.ayigroup.desafio.entities.Pedido;
import com.ayigroup.desafio.enums.Estado;

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

    //Comprobación estado anterior (para poder ser validado)
    public boolean estadoAnterior(Estado actual, Estado nuevo);

    //Comprobación estado posterior (para que no se pueda volver atras el estado de un pedido)
    public boolean estadoPosterior(Estado actual, Estado nuevo);

    //Comprobación que se cumplan condiciones para enviar a un Pedido a Pendiente de Aprobacion
    public void enviarAprobacion(Pedido pedido);

    //Comprobación para aprobar un pedido
    public void aprobarPedido(Pedido pedido);

    //Comprobación para rechazar un pedido
    public void rechazarPedido(Pedido pedido);


}
