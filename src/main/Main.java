package main;

import factory.*;
import model.*;
import observer.*;
import strategy.*;
import service.*;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            UsuarioService usuarioService = new UsuarioService();
            BibliotecaService bibliotecaService = new BibliotecaService();
            HistoricoEmprestimo historicoEmprestimo = new HistoricoEmprestimo();

            boolean running = true;

            while (running) {
                exibirMenu();
                int opcao = lerOpcao(scanner);

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
                        break;
                }
            }
        }
    }

    private static void exibirMenu() {
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
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();  
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine();  
            return -1;
        }
    }

    private static void cadastrarUsuario(Scanner scanner, UsuarioService usuarioService) {
        try {
            scanner.nextLine();  
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
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private static void gerenciarItens(Scanner scanner, BibliotecaService bibliotecaService) {
        System.out.println("\n=== Gerenciar Itens ===");
        System.out.println("1. Adicionar Item");
        System.out.println("2. Atualizar Item");
        System.out.println("3. Excluir Item");
        System.out.println("4. Consultar Itens");
        System.out.print("Escolha uma opção: ");
    
        int opcao = lerOpcao(scanner);
        scanner.nextLine(); 
    
        try {
            switch (opcao) {
                case 1:
                    adicionarItem(scanner, bibliotecaService);
                    break;  
                case 2:
                    atualizarItem(scanner, bibliotecaService);
                    break;  
                case 3:
                    excluirItem(scanner, bibliotecaService);
                    break;  
                case 4:
                    consultarItens(bibliotecaService);
                    break;  
                default:
                    System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao gerenciar itens: " + e.getMessage());
        }
    }
    
    private static void adicionarItem(Scanner scanner, BibliotecaService bibliotecaService) {
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine().toLowerCase();  
    
        System.out.print("Digite o autor: ");
        String autor = scanner.nextLine();
    
        System.out.print("Digite o tipo (Livro/Periódico): ");
        String tipo = scanner.nextLine().toLowerCase(); 
    
        System.out.print("Digite a área do item: ");
        String area = scanner.nextLine().toUpperCase(); 
        
        try {
            ItemBiblioteca item = ItemBibliotecaFactory.criaItem(titulo, autor, tipo, area);
            bibliotecaService.adicionarItem(item);
            System.out.println("Item adicionado ao catálogo!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
        }
    }
    
    
    private static void atualizarItem(Scanner scanner, BibliotecaService bibliotecaService) {
        System.out.print("Digite o título do item a ser atualizado: ");
        String titulo = scanner.nextLine().toLowerCase();  

<<<<<<< HEAD
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
=======
        var itens = bibliotecaService.getItens().stream()
                .filter(item -> item.getTitulo().toLowerCase().equals(titulo))  
                .collect(Collectors.toList());

        if (itens.isEmpty()) {
            System.out.println("Item não encontrado.");
            return;
        }

        ItemBiblioteca item = itens.get(0); 
        System.out.print("Digite o novo título: ");
        String novoTitulo = scanner.nextLine();
        item.setTitulo(novoTitulo);

        System.out.print("Disponibilidade (true/false): ");
        boolean disponibilidade = scanner.nextBoolean();
        item.setDisponibilidade(disponibilidade);

        bibliotecaService.atualizarItem(item);
        System.out.println("Item atualizado!");
    }

    private static void excluirItem(Scanner scanner, BibliotecaService bibliotecaService) {
        System.out.print("Digite o título do item a ser excluído: ");
        String titulo = scanner.nextLine();

        ItemBiblioteca item = bibliotecaService.getItens().stream()
                .filter(i -> i.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        if (item != null) {
            bibliotecaService.excluirItem(item);
            System.out.println("Item excluído com sucesso.");
        } else {
            System.out.println("Item com o título '" + titulo + "' não encontrado.");
>>>>>>> d2252d0 (implementação para calcular o juros do emprestimo)
        }
    }

    private static void consultarItens(BibliotecaService bibliotecaService) {
        System.out.println("Itens no catálogo:");
        bibliotecaService.getItens().forEach(it -> System.out.println("- " + it.getTitulo()));
    }
    

    private static void registrarEmprestimo(Scanner scanner, UsuarioService usuarioService, BibliotecaService bibliotecaService, HistoricoEmprestimo historicoEmprestimo) {
        System.out.print("Digite a matrícula do usuário: ");
        String matricula = scanner.nextLine();
        Usuario usuario = usuarioService.consultarUsuario(matricula);
    
        System.out.print("Digite o título do item: ");
        String titulo = scanner.nextLine();
        
      
        var itens = bibliotecaService.buscarPorTitulo(titulo);
        if (itens.isEmpty()) {
            System.out.println("Item não encontrado no catálogo.");
            return;  
        }
    

        ItemBiblioteca item = itens.get(0);
    
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
    
    

    private static void registrarDevolucao(Scanner scanner, HistoricoEmprestimo historicoEmprestimo) {
        System.out.print("Digite o título do item devolvido: ");
        String titulo = scanner.nextLine();
    
        Emprestimo emprestimo = historicoEmprestimo.buscarEmprestimoPorTitulo(titulo);
        if (emprestimo == null) {
            System.out.println("Nenhum empréstimo encontrado para o item: " + titulo);
            return;
        }
    
        
        Date dataDevolucaoReal = new Date(); 
        double multa = emprestimo.calcularMulta(dataDevolucaoReal); 
    
        System.out.println("Devolução registrada!");
        System.out.println("Data esperada de devolução: " + emprestimo.getDataDevolucao());
        System.out.println("Data real de devolução: " + dataDevolucaoReal);
        System.out.println("Multa aplicada: R$ " + multa);
    
        historicoEmprestimo.registrarDevolucao(titulo);
    }

    private static void fazerReserva(Scanner scanner, UsuarioService usuarioService, BibliotecaService bibliotecaService) {
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

    private static void consultarHistorico(Scanner scanner, UsuarioService usuarioService, HistoricoEmprestimo historicoEmprestimo) {
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
