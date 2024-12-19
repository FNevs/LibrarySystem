package model;

import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemBiblioteca {
    private String titulo;
    private String autor;
    private boolean disponibilidade;
    private List<Observer> observers;

    public ItemBiblioteca(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponibilidade = true;
        this.observers = new ArrayList<>();
    }

    // Métodos para manipular observadores
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

    // Método para alterar disponibilidade e notificar observadores
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
        notifyObservers();
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponibilidade;
    }

    // Método abstrato para retornar o tipo do item
    public abstract String getTipo();
}
