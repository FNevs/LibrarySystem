package main;

import factory.*;
import model.*;
import observer.*;
import strategy.*;

public class Main {
    public static void main(String[] args) {
        // Cadastro de usuários
        Usuario aluno = new Usuario("João", "12345", "99999-9999", "Aluno");
        Usuario professor = new Usuario("Maria", "54321", "88888-8888", "Professor");

        // Criação de itens
        LivroFactory livroFactory = new LivroFactory();
        PeriodicoFactory periodicoFactory = new PeriodicoFactory();

        ItemBiblioteca livro = livroFactory.criarItem("Aprendendo Java", "Deitel");
        ItemBiblioteca periodico = periodicoFactory.criarItem("Ciência Hoje", "Diversos");

        // Configurando observadores
        UsuarioObserver alunoObserver = new UsuarioObserver(aluno);
        livro.addObserver(alunoObserver);

        // Estratégias de empréstimo
        EmprestimoStrategy alunoStrategy = new AlunoEmprestimoStrategy();
        Emprestimo emprestimoAluno = new Emprestimo(aluno, livro, alunoStrategy);

        EmprestimoStrategy professorStrategy = new ProfessorEmprestimoStrategy();
        Emprestimo emprestimoProfessor = new Emprestimo(professor, periodico, professorStrategy);

        // Simulação
        System.out.println("Empréstimo para aluno vence em: " + emprestimoAluno.getDataDevolucao());
        System.out.println("Empréstimo para professor vence em: " + emprestimoProfessor.getDataDevolucao());

        // Alterar disponibilidade e notificar
        livro.setDisponibilidade(false);
    }
}
