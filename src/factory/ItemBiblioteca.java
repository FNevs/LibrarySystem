package factory;

import observer.UsuarioObserver;

public abstract class ItemBiblioteca {
    protected String titulo;
    protected String autor;

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public abstract String getTipo(); // Adicionado para diferenciar tipos de itens

    protected abstract void addObserver(UsuarioObserver alunoObserver);
}
