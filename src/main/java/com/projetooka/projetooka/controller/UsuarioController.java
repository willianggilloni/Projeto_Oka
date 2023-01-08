package com.projetooka.projetooka.controller;

import com.projetooka.projetooka.model.Usuario;
import com.projetooka.projetooka.model.UsuarioLogin;
import com.projetooka.projetooka.repository.UsuarioRepository;
import com.projetooka.projetooka.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
    public ResponseEntity <List<Usuario>> getAll(){

        return ResponseEntity.ok(usuarioRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> loginUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin){

        return usuarioService.autenticarUsuario(usuarioLogin)
                .map(respostaLogin -> ResponseEntity.status(HttpStatus.OK).body(respostaLogin))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){

        return usuarioService.cadastrarUsuario(usuario)
                .map(respostaCadastrar -> ResponseEntity.status(HttpStatus.CREATED).body(respostaCadastrar))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {

        return usuarioRepository.findById(id)
                .map(resposta -> {
                    usuarioRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
