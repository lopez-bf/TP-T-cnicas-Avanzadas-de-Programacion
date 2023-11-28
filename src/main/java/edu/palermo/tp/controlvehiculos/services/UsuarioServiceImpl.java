package edu.palermo.tp.controlvehiculos.services;

import edu.palermo.tp.controlvehiculos.dao.entities.UsuarioEntity;
import edu.palermo.tp.controlvehiculos.dao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity findById(Long id) {

        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);

        return usuario;
    }
}
