package factory;
import model.*;

public abstract class ItemBibliotecaFactory {
    public abstract ItemBiblioteca criarItem(String titulo, String autor);
}
