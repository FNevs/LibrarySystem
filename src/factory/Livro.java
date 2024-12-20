package factory;

import observer.Observer;
import observer.UsuarioObserver;

public class Livro extends ItemBiblioteca {
    
    public Livro(String titulo, String autor, String tipo, String area) {
<<<<<<< HEAD
        super(titulo, autor, tipo, area); 
=======
        super(titulo, autor, tipo, area);  
>>>>>>> d2252d0 (implementação para calcular o juros do emprestimo)
    }

    @Override
    public String getTipo() {
        return tipo; 
    }

   
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer); 
    }

    @Override
    public void addObserver(UsuarioObserver alunoObserver) {
        throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
    }
}
