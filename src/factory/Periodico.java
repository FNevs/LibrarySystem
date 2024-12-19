package factory;

public class Periodico extends ItemBiblioteca {
    private String edicao;

    public Periodico(String titulo, String autor, String edicao) {
        this.titulo = titulo;
        this.autor = autor;
        this.edicao = edicao;
    }

    public String getEdicao() {
        return edicao;
    }
}
