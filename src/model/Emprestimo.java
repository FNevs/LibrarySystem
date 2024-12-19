package model;

import strategy.EmprestimoStrategy;

import java.util.Date;

public class Emprestimo {
    private Usuario usuario;
    private ItemBiblioteca item;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private EmprestimoStrategy strategy;

    public Emprestimo(Usuario usuario, ItemBiblioteca item, EmprestimoStrategy strategy) {
        this.usuario = usuario;
        this.item = item;
        this.strategy = strategy;
        this.dataEmprestimo = new Date();
        this.dataDevolucao = strategy.calcularPrazo(dataEmprestimo);
    }

    public void renovar() {
        this.dataDevolucao = strategy.calcularPrazo(new Date());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ItemBiblioteca getItem() {
        return item;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }
}