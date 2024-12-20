package model;

import strategy.EmprestimoStrategy;

import java.util.Date;

public class Emprestimo {
    private Usuario usuario;
    private ItemBiblioteca item;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataDevolvido; // Data efetiva da devolução
    private String status; // Status: "Ativo", "Devolvido", "Atrasado"
    private EmprestimoStrategy strategy;
    private double multa; // Multa aplicada caso devolução esteja atrasada

    public Emprestimo(Usuario usuario, ItemBiblioteca item, EmprestimoStrategy strategy) {
        this.usuario = usuario;
        this.item = item;
        this.strategy = strategy;
        this.dataEmprestimo = new Date();
        this.dataDevolucao = strategy.calcularPrazo(dataEmprestimo);
        this.status = "Ativo";
        this.multa = 0.0;
    }

    public void renovar() {
        if ("Ativo".equals(status)) {
            this.dataDevolucao = strategy.calcularPrazo(new Date());
            System.out.println("Empréstimo renovado. Nova data de devolução: " + dataDevolucao);
        } else {
            System.out.println("Não é possível renovar um empréstimo que não está ativo.");
        }
    }

    public void devolver() {
        if ("Ativo".equals(status)) {
            this.dataDevolvido = new Date();
            this.status = "Devolvido";
            calcularMulta();
            this.item.setDisponibilidade(true);
            System.out.println("Item devolvido com sucesso.");
            if (multa > 0) {
                System.out.println("Multa aplicada: R$ " + multa);
            }
        } else {
            System.out.println("Não é possível devolver um empréstimo que já foi finalizado.");
        }
    }

    public void verificarAtraso() {
        if (new Date().after(dataDevolucao) && "Ativo".equals(status)) {
            this.status = "Atrasado";
            calcularMulta();
            System.out.println("O empréstimo está atrasado! Multa até o momento: R$ " + multa);
        }
    }

    private void calcularMulta() {
        if (dataDevolvido != null && dataDevolvido.after(dataDevolucao)) {
            long diferencaDias = (dataDevolvido.getTime() - dataDevolucao.getTime()) / (1000 * 60 * 60 * 24);
            multa = diferencaDias * 2.0; // Multa de R$ 2,00 por dia de atraso
        }
    }

    // Getters
    public Usuario getUsuario() {
        return usuario;
    }

    public ItemBiblioteca getItem() {
        return item;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public double getMulta() {
        return multa;
    }

    @Override
    public String toString() {
        return "Emprestimo {" +
                "usuario=" + usuario.getNome() +
                ", item=" + item.getTitulo() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", status='" + status + '\'' +
                ", multa=" + multa +
                '}';
    }
}
