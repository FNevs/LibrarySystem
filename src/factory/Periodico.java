package factory;


import observer.Observer;
import observer.UsuarioObserver;

public class Periodico extends ItemBiblioteca {
    
    
    public Periodico(String titulo, String autor, String tipo, String area) {
        super(titulo, autor, tipo, area);  // Chama o construtor da superclasse ItemBiblioteca
        
    }
  
    @Override

    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getArea() {
        return area;
    }
public String getTipo() {
        return tipo;
    }

@Override
protected void addObserver(UsuarioObserver alunoObserver) {
    throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
}

    
   
}