package factory;

import observer.Observer;
import model.ItemBiblioteca;

public class Livro extends ItemBiblioteca {
    public Livro(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }
}
