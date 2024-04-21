package com.ayigroup.desafio.service;

import com.ayigroup.desafio.entities.Pedido;
import com.ayigroup.desafio.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IPedidoService{

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    public List<Pedido> getPedidos() {
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        return listaPedidos;
    }

    @Override
    public void newPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    public Pedido findPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        return pedido;
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public void editPedido(Pedido pedido) {
        this.pedidoRepository.save(pedido);
    }
}
