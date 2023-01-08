package com.projetooka.projetooka.repository;

import com.projetooka.projetooka.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsuario(String usuario);
    //-- Método criado para buscar por usuários!
    //-- SELECT * FROM tb_usuario WHERE usuario = "usuario";
}
