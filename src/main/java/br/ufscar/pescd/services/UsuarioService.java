package br.ufscar.pescd.services;

import br.ufscar.pescd.model.Usuario;
import br.ufscar.pescd.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(
                passwordEncoder.encode(
                        usuario.getSenha()
                )
        );

        return repository.save(usuario);
    }
}