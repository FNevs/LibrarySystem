package factory;

import model.ItemBiblioteca;

public abstract class ItemBibliotecaFactory {
    public abstract ItemBiblioteca criarItem(String titulo, String autor);
}
