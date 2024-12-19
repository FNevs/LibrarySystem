package factory;

public class LivroFactory extends ItemBibliotecaFactory {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor) {
        return new Livro(titulo, autor);
    }
}