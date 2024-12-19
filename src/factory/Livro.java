package factory;

import observer.Observer;

import model.ItemBiblioteca;

public class Livro extends ItemBiblioteca {
    public Livro(String titulo, String autor) {
        super(titulo, autor);  // Chama o construtor da superclasse ItemBiblioteca
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    // Método necessário para implementar a interface ItemBiblioteca (no caso, addObserver já existe na superclasse)
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);  // Chama o método da superclasse
    }
}
