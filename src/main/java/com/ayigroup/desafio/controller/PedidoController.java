package com.ayigroup.desafio.controller;

import com.ayigroup.desafio.entities.Pedido;
import com.ayigroup.desafio.exceptions.EstadoInvalidoException;
import com.ayigroup.desafio.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;


    //Crear un pedido
    @PostMapping("/pedidos/crear")
    public ResponseEntity<String> createPedido(@RequestBody Pedido pedido) {
        pedidoService.newPedido(pedido);
        return ResponseEntity.ok("Pedido creado con éxito");
    }

    //Listar todos los pedidos
    @GetMapping("/pedidos/listar")
    public List<Pedido> listPedidos() {
        return pedidoService.getPedidos();
    }

    //Editar un pedido por ID
    @PutMapping("/pedidos/editar/{idOriginal}")
    public ResponseEntity<String> editarPedido(@PathVariable Long idOriginal, @RequestBody Pedido pedido) {
        Pedido pedidoExistente = pedidoService.findPedido(idOriginal);

        if (pedidoExistente == null) {
            return ResponseEntity.notFound().build();
        }


        pedido.setId(idOriginal);

        pedidoService.editPedido(pedido);

        return ResponseEntity.ok("Pedido editado con éxito");
    }

    //Eliminar un pedido
    @DeleteMapping("/pedidos/eliminar/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.ok("Pedido eliminado con éxito");
    }

    //Aprobar pedido
    @PostMapping("/pedidos/{pedidoId}/aprobar")
    public ResponseEntity<String> aprobarPedido(@PathVariable("pedidoId") Long pedidoId) {
        Pedido pedido = pedidoService.findPedido(pedidoId);

        try {
            pedidoService.aprobarPedido(pedido);
            return ResponseEntity.ok("Pedido aprobado con exito");
        } catch (EstadoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Rechazar pedido
    @PostMapping("/pedidos/{pedidoId}/cancelar")
    public ResponseEntity<String> rechazarPedido(@PathVariable("pedidoId") Long pedidoId) {
        Pedido pedido = pedidoService.findPedido(pedidoId);

        try {
            pedidoService.rechazarPedido(pedido);
            return ResponseEntity.ok("Pedido cancelado con exito");
        } catch (EstadoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Enviar a aprobacion
    @PostMapping("/pedidos/{pedidoId}/enviar-aprobacion")
    public ResponseEntity<String> enviarAprobacionPedido(@PathVariable("pedidoId") Long pedidoId) {
        Pedido pedido = pedidoService.findPedido(pedidoId);

        try {
            pedidoService.enviarAprobacion(pedido);
            return ResponseEntity.ok("Pedido enviado a aprobación");
        } catch (EstadoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
