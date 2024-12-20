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
        for (Emprestimo emprestimo : historico) {
            if (emprestimo.getItem().getTitulo().equals(titulo)) {
                historico.remove(emprestimo);
                break;
            }
        }
    }

    public List<Emprestimo> consultarHistoricoPorUsuario(Usuario usuario) {
        List<Emprestimo> historicoUsuario = new ArrayList<>();
        for (Emprestimo emprestimo : historico) {
            if (emprestimo.getUsuario().getMatricula().equals(usuario)) {
                historicoUsuario.add(emprestimo);
            }
        }
        return historicoUsuario;
    }

    public List<Emprestimo> getHistorico() {
        return historico;
    }
}
