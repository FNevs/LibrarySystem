package model;

import java.util.Date;

public class Emprestimo {
    private Usuario usuario;
    private ItemBiblioteca item;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String status;
    private EmprestimoStrategy strategy;

    public Emprestimo(Usuario usuario, ItemBiblioteca item, EmprestimoStrategy strategy) {
        this.usuario = usuario;
        this.item = item;
        this.strategy = strategy;
        this.dataEmprestimo = new Date();
        this.dataDevolucao = strategy.calcularPrazo(dataEmprestimo);
        this.status = "Ativo";
    }

    public void renovar() {
        this.dataDevolucao = strategy.calcularPrazo(new Date());
    }

    public String getStatus() { return status; }
    public Date getDataDevolucao() { return dataDevolucao; }
}
