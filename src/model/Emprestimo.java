package model;
import factory.ItemBiblioteca;
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
        this.dataEmprestimo = new Date();
        this.strategy = strategy;
    }

    // Método para calcular a multa
    public double calcularMulta() {
        // Calcular a data limite de devolução
        Date dataLimite = strategy.calcularPrazo(dataEmprestimo);

        // Verifica se já passou da data de devolução
        if (dataDevolucao != null && dataDevolucao.after(dataLimite)) {
            long diasAtraso = (dataDevolucao.getTime() - dataLimite.getTime()) / (1000 * 60 * 60 * 24);
            double valorMulta = 0;

            // Verifica o tipo de usuário e aplica a multa correspondente
            if ("Aluno".equalsIgnoreCase(usuario.getTipo())) {
                valorMulta = diasAtraso * 2.00; 
            } else if ("Professor".equalsIgnoreCase(usuario.getTipo())) {
                valorMulta = diasAtraso * 1.00; 
            }

            return valorMulta;
        }

        return 0; // Sem multa se não houver atraso
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ItemBiblioteca getItem() {
        return item;
    }

    public void setItem(ItemBiblioteca item) {
        this.item = item;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public EmprestimoStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(EmprestimoStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "Emprestimo {" +
                "usuario=" + usuario +
                ", item=" + item +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
