package com.projetooka.projetooka.controller;

import com.projetooka.projetooka.model.Produto;
import com.projetooka.projetooka.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List> getByNome(@PathVariable String nome) {
        List<Produto> produtos = produtoRepository.findAllByNomeContainingIgnoreCase(nome);
        if (produtos.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> atualizarProduto(@Valid @RequestBody Produto produto) {
        if (produtoRepository.existsById(produto.getId())) {
            return ResponseEntity.ok(produtoRepository.save(produto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
