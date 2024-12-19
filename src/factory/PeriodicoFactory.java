package factory;

import model.ItemBiblioteca;

public class PeriodicoFactory extends ItemBibliotecaFactory {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor) {
        return new Periodico(titulo, autor, "");  // Cria e retorna o Periodico, que é um ItemBiblioteca
    }
}
