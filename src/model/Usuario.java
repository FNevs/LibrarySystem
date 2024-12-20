package model;

public class Usuario {
    private String nome;
    private String matricula;
    private String telefone;
    private String tipo;
    private String curso;      
    private String departamento; 

    public Usuario(String nome, String matricula, String telefone, String tipo) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", telefone='" + telefone + '\'' +
                ", tipo='" + tipo + '\'' +
                (curso != null ? ", curso='" + curso + '\'' : "") +
                (departamento != null ? ", departamento='" + departamento + '\'' : "") +
                '}';
    }
}
