package br.com.alura.domain;

public class Pet {

    private Long id;
    private String tipo;
    private String nome;
    private String raca;
    private String cor;
    private Float peso;
    private int idade;

    public Pet(String tipo, String nome, String raca, String cor, Float peso, int idade) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
        this.peso = peso;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }



}