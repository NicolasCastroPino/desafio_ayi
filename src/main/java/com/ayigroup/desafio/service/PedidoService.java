package com.ayigroup.desafio.service;

import com.ayigroup.desafio.entities.Pedido;
import com.ayigroup.desafio.enums.Estado;
import com.ayigroup.desafio.exceptions.EstadoInvalidoException;
import com.ayigroup.desafio.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    public List<Pedido> getPedidos() {
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        return listaPedidos;
    }

    @Override
    public void newPedido(Pedido pedido) {
        pedido.setEstado(Estado.BORRADOR);
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

    @Override
    public boolean estadoAnterior(Estado actualEstado, Estado nuevoEstado) {
        return nuevoEstado.ordinal() < actualEstado.ordinal();
    }

    @Override
    public boolean estadoPosterior(Estado actualEstado, Estado nuevoEstado) {
        return nuevoEstado.ordinal() > actualEstado.ordinal();
    }

    @Override
    public void enviarAprobacion(Pedido pedido) {
        if (estadoPosterior(pedido.getEstado(), Estado.PENDIENTE_APROBACION)) {
            pedido.setEstado(Estado.PENDIENTE_APROBACION);
        } else {
            throw new EstadoInvalidoException("El pedido debe pasar a estado Pend. Aprobación");
        }
    }

    @Override
    public void aprobarPedido(Pedido pedido) {
        if (estadoPosterior(pedido.getEstado(), Estado.APROBADO)) {
            pedido.setEstado(Estado.APROBADO);
        } else {
            throw new EstadoInvalidoException("El pedido debe pasar a estado Aprobado");
        }
    }

    @Override
    public void rechazarPedido(Pedido pedido) {
        if (estadoPosterior(pedido.getEstado(), Estado.RECHAZADO)) {
            pedido.setEstado(Estado.RECHAZADO);
        } else {
            throw new EstadoInvalidoException("Estado invalido");
        }
    }
}
