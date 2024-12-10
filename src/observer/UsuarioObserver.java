package observer;

public class UsuarioObserver implements Observer {
    private Usuario usuario;

    public UsuarioObserver(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void update(ItemBiblioteca item) {
        System.out.println("Notificação para " + usuario.getNome() +
                ": O item " + item.getTitulo() + " alterou sua disponibilidade.");
    }
}
