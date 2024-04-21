package com.ayigroup.desafio.service;

import com.ayigroup.desafio.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    //CRUD USUARIO


    //Listar todos los usuarios
    public List<Usuario> getUsuarios();

    //Crear un Usuario
    public void newUsuario(Usuario usuario);

    //Traer un usuario por ID
    public Usuario findUsuario(Long id);

    //Borrar un usuario
    public void deleteUsuario(Long id);

    //Editar usuario
    public void editUsuario(Usuario usuario);


}
