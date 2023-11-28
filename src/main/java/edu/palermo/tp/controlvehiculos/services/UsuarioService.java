package edu.palermo.tp.controlvehiculos.services;

import edu.palermo.tp.controlvehiculos.dao.entities.UsuarioEntity;

import java.util.Optional;

public interface UsuarioService {

    public UsuarioEntity findById(Long id);

}


