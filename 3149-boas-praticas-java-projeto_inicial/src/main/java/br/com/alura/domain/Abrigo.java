package br.com.alura.domain;

public class Abrigo {

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    private Pet[] pets;

    public Abrigo(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;

    }

    public Abrigo(){

    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Pet[] getPets() {
        return pets;
    }
    public String getEndereco() {
        return endereco;
    }
}
