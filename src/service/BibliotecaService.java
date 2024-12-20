package service;

import factory.ItemBiblioteca;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BibliotecaService {
    private final List<ItemBiblioteca> catalogo = new ArrayList<>();

    public void adicionarItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        catalogo.add(item);
    }

    public void atualizarItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getTitulo().equalsIgnoreCase(item.getTitulo())) {
                catalogo.set(i, item);
                return;
            }
        }
        throw new IllegalArgumentException("Item não encontrado no catálogo.");
    }

    public void excluirItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        boolean removido = catalogo.remove(item);
        if (!removido) {
            throw new IllegalArgumentException("Item não encontrado no catálogo.");
        }
    }

    public List<ItemBiblioteca> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio.");
        }
        String tituloLower = titulo.toLowerCase().trim(); 
        System.out.println("Buscando título: " + tituloLower); 
        return buscarPorCriterio(item -> item.getTitulo().toLowerCase().contains(tituloLower)); 
    }
    
    

    public List<ItemBiblioteca> buscarPorAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("O autor não pode ser nulo ou vazio.");
        }
        String autorLower = autor.toLowerCase(); 
        return buscarPorCriterio(item -> item.getAutor().toLowerCase().contains(autorLower)); 
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

    public List<ItemBiblioteca> getItens() {
        return new ArrayList<>(catalogo); 
    }

    // Método auxiliar para busca com critério específico
    private List<ItemBiblioteca> buscarPorCriterio(Predicate<ItemBiblioteca> criterio) {
        return catalogo.stream().filter(criterio).collect(Collectors.toList());
    }
}
