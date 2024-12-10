package factory;

import model.ItemBiblioteca;//importando

public class PeriodicoFactory extends ItemBibliotecaFactory {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor) {
        return new Periodico(titulo, autor);
    }
}

class Periodico extends ItemBiblioteca {
    public Periodico(String titulo, String autor) {
        super(titulo, autor);
    }
}
