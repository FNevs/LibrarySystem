package main;

import factory.*;
import model.*;
import observer.*;
import strategy.*;
import service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        BibliotecaService bibliotecaService = new BibliotecaService();
        HistoricoEmprestimo historicoEmprestimo = new HistoricoEmprestimo();

        boolean running = true;

        while (running) {
            System.out.println("\n==== Menu do Sistema ====");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Gerenciar Itens da Biblioteca");
            System.out.println("3. Registrar Empréstimo");
            System.out.println("4. Registrar Devolução");
            System.out.println("5. Fazer Reserva de Item");
            System.out.println("6. Consultar Histórico de Empréstimos");
            System.out.println("7. Buscar Item no Catálogo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner, usuarioService);
                    break;
                case 2:
                    gerenciarItens(scanner, bibliotecaService);
                    break;
                case 3:
                    registrarEmprestimo(scanner, usuarioService, bibliotecaService, historicoEmprestimo);
                    break;
                case 4:
                    registrarDevolucao(scanner, historicoEmprestimo);
                    break;
                case 5:
                    fazerReserva(scanner, usuarioService, bibliotecaService);
                    break;
                case 6:
                    consultarHistorico(scanner, usuarioService, historicoEmprestimo);
                    break;
                case 7:
                    buscarItem(scanner, bibliotecaService);
                    break;
                case 0:
                    running = false;
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void cadastrarUsuario(Scanner scanner, UsuarioService usuarioService) {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a matrícula/função: ");
        String matricula = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o tipo (Aluno/Professor/Funcionário): ");
        String tipo = scanner.nextLine();

        Usuario usuario = new Usuario(nome, matricula, telefone, tipo);
        if (tipo.equalsIgnoreCase("Aluno")) {
            System.out.print("Digite o curso: ");
            String curso = scanner.nextLine();
            usuario.setCurso(curso);
        } else {
            System.out.print("Digite o departamento: ");
            String departamento = scanner.nextLine();
            usuario.setDepartamento(departamento);
        }

        usuarioService.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void gerenciarItens(Scanner scanner, BibliotecaService bibliotecaService) {
        System.out.println("\n=== Gerenciar Itens ===");
        System.out.println("1. Adicionar Item");
        System.out.println("2. Atualizar Item");
        System.out.println("3. Excluir Item");
        System.out.println("4. Consultar Itens");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcao) {
            case 1:
                System.out.print("Digite o título: ");
                String titulo = scanner.nextLine();
                System.out.print("Digite o autor: ");
                String autor = scanner.nextLine();
                System.out.print("Digite o tipo (Livro/Periódico/Jornal,MaterialAudioVisual): ");
                String tipo = scanner.nextLine();
                System.out.print("Digite a area do item: ");
                String area = scanner.nextLine();

                ItemBiblioteca item;
                if (tipo.equalsIgnoreCase("Livro") || tipo.equalsIgnoreCase("Periodico") || tipo.equalsIgnoreCase("Jornal") || tipo.equalsIgnoreCase("MaterialAudioVisual") || tipo.equalsIgnoreCase("Material Audio Visual") || tipo.equalsIgnoreCase("Material AudioVisual") || tipo.equalsIgnoreCase("Material Audio Visual")) {
                    item = ItemBibliotecaFactory.criaItem(titulo, autor, tipo, area);
                } else {
                    throw new IllegalArgumentException("Tipo de item inválido!");
                }

                bibliotecaService.adicionarItem(item);
                System.out.println("Item adicionado ao catálogo!");
                break;
            case 2:
                System.out.print("Digite o título do item a ser atualizado: ");
                String tituloAtualizar = scanner.nextLine();
                ItemBiblioteca itemAtualizar = bibliotecaService.buscarPorTitulo(tituloAtualizar).get(0);
                System.out.print("Digite o novo titulo: ");
                String novotitulo = scanner.nextLine();
                itemAtualizar.setTitulo(novotitulo);
                System.out.print("Disponibilidade (true/false): ");
                boolean disponibilidade = scanner.nextBoolean();
                itemAtualizar.setDisponibilidade(disponibilidade);
                bibliotecaService.atualizarItem(itemAtualizar);
                System.out.println("Item atualizado!");
                break;
            case 3:
                System.out.print("Digite o título do item a ser excluído: ");
                String tituloExcluir = scanner.nextLine();
                bibliotecaService.excluirItem(tituloExcluir);
                System.out.println("Item excluído!");
                break;
            case 4:
                System.out.println("Itens no catálogo:");
                bibliotecaService.getItens().forEach(it -> {
                System.out.println("Titulo: " + it.getTitulo());
                System.out.println("Autor: " + it.getAutor());
                System.out.println("Tipo: " + it.getTipo());
                System.out.println("Area: " + it.getArea());
                System.out.println("--------------------");
                });
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

private static void registrarEmprestimo(Scanner scanner, UsuarioService usuarioService,
                                         BibliotecaService bibliotecaService, HistoricoEmprestimo historicoEmprestimo) {
    System.out.print("Digite a matrícula do usuário: ");
    String matricula = scanner.nextLine();
    Usuario usuario = usuarioService.consultarUsuario(matricula);

    if (usuario == null) {
        System.out.println("Usuário não encontrado.");
        return;
    }

    System.out.print("Digite o título do item: ");
    String titulo = scanner.nextLine();
    
    List<ItemBiblioteca> itensEncontrados = bibliotecaService.buscarPorTitulo(titulo);
    if (itensEncontrados.isEmpty()) {
        System.out.println("Item não encontrado no catálogo.");
        return;
    }

    ItemBiblioteca item = itensEncontrados.get(0);

    EmprestimoStrategy strategy;
    if (usuario.getTipo().equalsIgnoreCase("Aluno")) {
        strategy = new AlunoEmprestimoStrategy();
    } else {
        strategy = new ProfessorEmprestimoStrategy();
    }

    Emprestimo emprestimo = new Emprestimo(usuario, item, strategy);
    historicoEmprestimo.registrarEmprestimo(emprestimo);

    System.out.println("Empréstimo registrado! Data de devolução: " + emprestimo.getDataDevolucao());
}


public static void registrarDevolucao(Scanner scanner, HistoricoEmprestimo historicoEmprestimo) {
    System.out.print("Digite o título do item devolvido: ");
    String tituloItem = scanner.nextLine();

    System.out.print("Digite a data de devolução (formato: dd/MM/yyyy): ");
    String dataDevolucaoStr = scanner.nextLine();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date dataDevolucao = null;

    try {
        dataDevolucao = dateFormat.parse(dataDevolucaoStr);
    } catch (ParseException e) {
        System.out.println("Data inválida. A devolução não foi registrada.");
        return;
    }

    Emprestimo emprestimo = historicoEmprestimo.buscarEmprestimoPorTitulo(tituloItem);
    if (emprestimo != null) {
        Date dataPrevistaDevolucao = emprestimo.getDataDevolucao();

        long diferencaEmMillis = dataDevolucao.getTime() - dataPrevistaDevolucao.getTime();
        long diasAtraso = diferencaEmMillis / (1000 * 60 * 60 * 24);  // Converte milissegundos para dias

        if (diasAtraso > 0) {
            double taxaMulta = 1.50;
            double multa = diasAtraso * taxaMulta;
            System.out.println("O item está " + diasAtraso + " dias atrasado. Multa: R$ " + multa);
        } else {
            System.out.println("Devolução dentro do prazo. Sem multa.");
        }

        emprestimo.setDataDevolucao(dataDevolucao);
        historicoEmprestimo.registrarDevolucao(emprestimo); 
        System.out.println("Devolução registrada! Data de devolução: " + emprestimo.getDataDevolucao());
    } else {
        System.out.println("Item não encontrado no histórico de empréstimos.");
    }
}


    private static void fazerReserva(Scanner scanner, UsuarioService usuarioService,
                                     BibliotecaService bibliotecaService) {
        System.out.print("Digite a matrícula do usuário: ");
        String matricula = scanner.nextLine();
        Usuario usuario = usuarioService.consultarUsuario(matricula);
        UsuarioObserver observer = new UsuarioObserver(usuario);

        System.out.print("Digite o título do item a ser reservado: ");
        String titulo = scanner.nextLine();
        ItemBiblioteca item = bibliotecaService.buscarPorTitulo(titulo).get(0);
        item.addObserver(observer);

        System.out.println("Reserva registrada! Você será notificado quando o item estiver disponível.");
    }

    private static void consultarHistorico(Scanner scanner, UsuarioService usuarioService,
                                           HistoricoEmprestimo historicoEmprestimo) {
        System.out.print("Digite a matrícula do usuário: ");
        String matricula = scanner.nextLine();
        Usuario usuario = usuarioService.consultarUsuario(matricula);

        System.out.println("Histórico de empréstimos:");
        historicoEmprestimo.consultarHistoricoPorUsuario(usuario).forEach(emp -> {
            System.out.println("- Item: " + emp.getItem().getTitulo() + ", Data de Devolução: " + emp.getDataDevolucao());
        });
    }

    private static void buscarItem(Scanner scanner, BibliotecaService bibliotecaService) {
        System.out.print("Digite o termo de busca (título, autor ou área): ");
        String termo = scanner.nextLine();

        System.out.println("Resultados:");
        bibliotecaService.buscarPorTitulo(termo).forEach(it -> {
            System.out.println("Titulo: " + it.getTitulo());
            System.out.println("Autor: " + it.getAutor());
            System.out.println("Tipo: " + it.getTipo());
            System.out.println("Area: " + it.getArea());
            System.out.println("--------------------");
        });
        bibliotecaService.buscarPorAutor(termo).forEach(it -> {
            System.out.println("Titulo: " + it.getTitulo());
            System.out.println("Autor: " + it.getAutor());
            System.out.println("Tipo: " + it.getTipo());
            System.out.println("Area: " + it.getArea());
            System.out.println("--------------------");
        });
        bibliotecaService.buscarPorArea(termo).forEach(it -> {
            System.out.println("Titulo: " + it.getTitulo());
            System.out.println("Autor: " + it.getAutor());
            System.out.println("Tipo: " + it.getTipo());
            System.out.println("Area: " + it.getArea());
            System.out.println("--------------------");
        });
        bibliotecaService.buscarPorTipo(termo).forEach(it -> {
            System.out.println("Titulo: " + it.getTitulo());
            System.out.println("Autor: " + it.getAutor());
            System.out.println("Tipo: " + it.getTipo());
            System.out.println("Area: " + it.getArea());
            System.out.println("--------------------");
        });
    }
}