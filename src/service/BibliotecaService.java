package service;

import factory.ItemBiblioteca;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private List<ItemBiblioteca> catalogo = new ArrayList<>();

    public void adicionarItem(ItemBiblioteca item) {
        catalogo.add(item);
    }

    public void atualizarItem(ItemBiblioteca item) {
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getTitulo().equals(item.getTitulo())) {
                catalogo.set(i, item);
                return;
            }
        }
    }

    public void excluirItem(String titulo) {
        catalogo.removeIf(i -> i.getTitulo().equals(titulo));
    }

    public List<ItemBiblioteca> buscarPorTitulo(String titulo) {
        List<ItemBiblioteca> resultado = new ArrayList<>();
        for (ItemBiblioteca item : catalogo) {
            if (item.getTitulo().contains(titulo)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public List<ItemBiblioteca> buscarPorAutor(String autor) {
        List<ItemBiblioteca> resultado = new ArrayList<>();
        for (ItemBiblioteca item : catalogo) {
            if (item.getAutor().contains(autor)) {
                resultado.add(item);
            }
        }
        return resultado;
    }
    public List<ItemBiblioteca> buscarPorTipo(String tipo) {
        List<ItemBiblioteca> resultado = new ArrayList<>();
        for (ItemBiblioteca item : catalogo) {
            if (item.getTipo().contains(tipo)) {
                resultado.add(item);
            }
        }
        return resultado;
    }
    public List<ItemBiblioteca> buscarPorArea(String area) {
        List<ItemBiblioteca> resultado = new ArrayList<>();
        for (ItemBiblioteca item : catalogo) {
            if (item.getArea().contains(area)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    // Adicionando o método getItens() para retornar todos os itens do catálogo
    public List<ItemBiblioteca> getItens() {
        return catalogo;
    }
}