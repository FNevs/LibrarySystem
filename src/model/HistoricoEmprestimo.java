package model;

import java.util.ArrayList;
import java.util.List;

public class HistoricoEmprestimo {
    private List<Emprestimo> historico;

    public HistoricoEmprestimo() {
        this.historico = new ArrayList<>();
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        historico.add(emprestimo);
    }

    public void registrarDevolucao(String titulo) {
        historico.removeIf(emprestimo -> emprestimo.getItem().getTitulo().equalsIgnoreCase(titulo));
    }

    public Emprestimo buscarEmprestimoPorTitulo(String titulo) {
        for (Emprestimo emprestimo : historico) {
            if (emprestimo.getItem().getTitulo().equalsIgnoreCase(titulo)) {
                return emprestimo;
            }
        }
        return null; // Retorna null caso não encontre
    }

    public List<Emprestimo> consultarHistoricoPorUsuario(Usuario usuario) {
        List<Emprestimo> historicoUsuario = new ArrayList<>();
        for (Emprestimo emprestimo : historico) {
            if (emprestimo.getUsuario().getMatricula().equals(usuario.getMatricula())) {
                historicoUsuario.add(emprestimo);
            }
        }
        return historicoUsuario;
    }

    public List<Emprestimo> getHistorico() {
        return historico;
    }
}
