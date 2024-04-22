package com.ayigroup.desafio.controller;

import com.ayigroup.desafio.entities.Usuario;
import com.ayigroup.desafio.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    //Crear un usuario
    @PostMapping("/usuarios/crear")
    public String createUsuario(@RequestBody Usuario usuario) {
        usuarioService.newUsuario(usuario);
        return "Usuario creado con éxito";
    }

    //Listar todos los usuarios
    @GetMapping("/usuarios/listar")
    public List<Usuario> listUsuarios() {
        return usuarioService.getUsuarios();
    }

    //Editar un usuario por ID
    @PutMapping("/usuarios/editar/{idOriginal}")
    public ResponseEntity<String> editarUsuario(@PathVariable Long idOriginal, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.findUsuario(idOriginal);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }


        usuario.setId(idOriginal);

        usuarioService.editUsuario(usuario);

        return ResponseEntity.ok("Usuario editado con éxito");
    }

    //Eliminar un usuario
    @DeleteMapping("/usuarios/eliminar/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
}
