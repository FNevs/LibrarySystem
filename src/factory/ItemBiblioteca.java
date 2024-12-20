package factory;

import observer.Observer;
import observer.UsuarioObserver;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemBiblioteca {
    protected String titulo;
    protected String autor;
    protected String tipo;
    protected String area;
    protected boolean disponibilidade;
     
    public ItemBiblioteca(String titulo, String autor, String tipo, String area) {
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.area = area;
        this.disponibilidade = true;
        this.observers = new ArrayList<>();
    }

    protected List<Observer> observers;
      
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
    public String getTipo() {
        return tipo;
    } 
    public String getArea() {
        return area;
    }
    public boolean isDisponibilidade() {
        return disponibilidade;
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
    public boolean isDisponivel() {
        return disponibilidade;
    }


    // Método para alterar disponibilidade e notificar observadores
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
        notifyObservers();
    }
    protected abstract void addObserver(UsuarioObserver alunoObserver);
    

}
