package com.projetooka.projetooka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table (name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "O Nome é obrigatório.")
    private String nome;

    @NotBlank (message = "O E-mail é obrigatório.")
    @Email
    private String email;

    @NotNull (message = "O Usuário é obrigatório.")
    private String usuario;

    @NotBlank (message = "A Senha deve ter no mínimo 8 caracteres")
    @Size (min = 8)
    private String senha;

    @Size (max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
    private String foto;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @OneToMany (mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties ("usuario")
    private List<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}