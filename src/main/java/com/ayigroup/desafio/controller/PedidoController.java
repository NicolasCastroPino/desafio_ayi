package com.ayigroup.desafio.controller;

import com.ayigroup.desafio.entities.Pedido;
import com.ayigroup.desafio.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;


    //Crear un pedido
    @PostMapping("/pedidos/crear")
    public String createPedido(@RequestBody Pedido pedido){
        pedidoService.newPedido(pedido);
        return "Pedido creado con éxito";
    }

    //Listar todos los pedidos
    @GetMapping("/pedidos/listar")
    public List<Pedido> listPedidos(){
        return pedidoService.getPedidos();
    }

    //Editar un pedido por ID
    @PutMapping("/pedidos/editar/{idOriginal}")
    public ResponseEntity<String> editarPedido(@PathVariable Long idOriginal, @RequestBody Pedido pedido){
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
    public ResponseEntity<String> deletePedido(@PathVariable Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.ok("Pedido eliminado con éxito");
    }
}
