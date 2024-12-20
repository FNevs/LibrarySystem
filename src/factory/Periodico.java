package factory;

import model.ItemBiblioteca;
import observer.Observer;

public class Periodico extends ItemBiblioteca {
    private String edicao;

    public Periodico(String titulo, String autor, String edicao) {
        super(titulo, autor);  // Chama o construtor da superclasse ItemBiblioteca
        this.edicao = edicao;
    }

    public String getEdicao() {
        return edicao;
    }
    
    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    @Override
    public String getTipo() {
        return "Periódico";
    }

    // Método necessário para implementar a interface ItemBiblioteca (no caso, addObserver já existe na superclasse)
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);  // Chama o método da superclasse
    }
}