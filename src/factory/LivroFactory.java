package factory;

import model.ItemBiblioteca;

public class LivroFactory extends ItemBibliotecaFactory {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor) {
        return new Livro(titulo, autor);  // Certifique-se de que Livro é uma subclasse de ItemBiblioteca
    }
}
