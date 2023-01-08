package com.projetooka.projetooka.controller;

import com.projetooka.projetooka.*;
import com.projetooka.projetooka.model.Categoria;
import com.projetooka.projetooka.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> getBynome(@PathVariable String nome) {

        List<Categoria> categorias = categoriaRepository.findAllByNomeContainingIgnoreCase(nome);
        if (categorias.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categorias);

    }

    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
        if (categoriaRepository.existsById(categoria.getId()))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoriaRepository.save(categoria));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoriaRepository.deleteById(id);
    }
}
