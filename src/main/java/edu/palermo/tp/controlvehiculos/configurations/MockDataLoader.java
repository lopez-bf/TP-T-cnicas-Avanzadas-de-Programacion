package edu.palermo.tp.controlvehiculos.configurations;

import edu.palermo.tp.controlvehiculos.dao.entities.UsuarioEntity;
import edu.palermo.tp.controlvehiculos.dao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

//@Component
public class MockDataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MockDataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Generar y guardar 10 usuarios de ejemplo
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    UsuarioEntity usuario = new UsuarioEntity();
                    usuario.setNombre("Usuario" + i);
                    usuarioRepository.save(usuario);
                });

        System.out.println("Mock data loaded successfully!");
    }
}
