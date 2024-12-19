package model;

public class Usuario {
    private String nome;
    private String matricula;
    private String telefone;
    private String tipo;

    public Usuario(String nome, String matricula, String telefone, String tipo) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getTelefone() { return telefone; }
    public String getTipo() { return tipo; }
}