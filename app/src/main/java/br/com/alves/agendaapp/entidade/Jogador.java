package br.com.alves.agendaapp.entidade;

public class Jogador {

    //Atributos -------------------------------------------
    private Integer id;
    private String nome;
    private String clube;
    private Integer idade;
    private Double nota;

    public Jogador(String nome, String clube, Integer idade, Double nota) {
        this.nome = nome;
        this.clube = clube;
        this.idade = idade;
        this.nota = nota;
    }
    public Jogador() {
    }

    //Getters and Setters ---------------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome();
    }
}