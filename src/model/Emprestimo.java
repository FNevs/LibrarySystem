package model;
import factory.ItemBiblioteca;
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
            
            verificarAtraso();
            if (multa > 0) {
                System.out.println("Multa aplicada: R$ " + multa);
            }
        } else {
            System.out.println("Não é possível devolver um empréstimo que já foi finalizado.");
        }
    }
    

    public void verificarAtraso() {
        if ("Ativo".equals(status) && new Date().after(dataDevolucao)) {
            this.status = "Atrasado";
            calcularMulta();  
            System.out.println("O empréstimo está atrasado! Multa até o momento: R$ " + multa);
        }
    }
    

    public double calcularMulta() {
        Date dataReferencia = (dataDevolvido != null) ? dataDevolvido : new Date();
        if (dataReferencia.after(dataDevolucao)) {
            long diferencaDias = (dataReferencia.getTime() - dataDevolucao.getTime()) / (1000 * 60 * 60 * 24);
            multa = diferencaDias * 2.0;  
        } else {
            multa = 0.0;
        }
        return multa;
    }
    
    public double calcularMulta(Date dataReferencia) {
        if (dataReferencia.after(dataDevolucao)) {
            long diferencaDias = (dataReferencia.getTime() - dataDevolucao.getTime()) / (1000 * 60 * 60 * 24);
            return diferencaDias * 2.0;
        }
        return 0.0;
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
        calcularMulta();
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
