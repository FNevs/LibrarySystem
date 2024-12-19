package factory;

public class PeriodicoFactory extends ItemBibliotecaFactory {
    @Override
    public ItemBiblioteca criarItem(String titulo, String autor) {
        return new Periodico(titulo, autor, "");
    }
}
