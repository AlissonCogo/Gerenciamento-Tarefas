package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrador extends Usuario {

    public Administrador(String nome, String senha) {
        super(nome, senha, Role.ADMIN);
    }

    @Override
    public void adicionarTarefa(Tarefa tarefa, List<Tarefa> listaDeTarefas) {
        listaDeTarefas.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso.");
    }

    @Override
    public void removerTarefa(int id, List<Tarefa> listaDeTarefas) {
        listaDeTarefas.removeIf(tarefa -> tarefa.getId() == id);
        System.out.println("Tarefa removida com sucesso.");
    }

    @Override
    public void modificarTarefa(int id, String novaDescricao, List<Tarefa> listaDeTarefas) {
        for (Tarefa tarefa : listaDeTarefas) {
            if (tarefa.getId() == id) {
                tarefa.setDescricao(novaDescricao);
                System.out.println("Tarefa modificada com sucesso.");
            }
        }
    }

    public static class ListaDeTarefas {
        public static void main(String[] args) {
            List<Tarefa> listaDeTarefas = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);

            Administrador admin = new Administrador("Admin", "admin123");
            UsuarioComum usuarioComum = new UsuarioComum("Joao", "joao123");


            Usuario usuarioLogado = login(scanner, admin, usuarioComum);

            boolean sair = false;

            while (!sair) {
                System.out.println("\nEscolha uma ação:");
                System.out.println("1. Adicionar tarefa");
                System.out.println("2. Remover tarefa");
                System.out.println("3. Modificar tarefa");
                System.out.println("4. Ver todas as tarefas");
                System.out.println("5. Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Digite a descrição da tarefa:");
                        String descricao = scanner.nextLine();
                        Tarefa novaTarefa = new Tarefa(descricao, usuarioLogado.nome);
                        usuarioLogado.adicionarTarefa(novaTarefa, listaDeTarefas);
                        break;
                    case 2:
                        System.out.println("Digite o ID da tarefa a ser removida:");
                        int idRemover = scanner.nextInt();
                        usuarioLogado.removerTarefa(idRemover, listaDeTarefas);
                        break;
                    case 3:
                        System.out.println("Digite o ID da tarefa a ser modificada:");
                        int idModificar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Digite a nova descrição:");
                        String novaDescricao = scanner.nextLine();
                        usuarioLogado.modificarTarefa(idModificar, novaDescricao, listaDeTarefas);
                        break;
                    case 4:
                        System.out.println("Lista de tarefas:");
                        for (Tarefa tarefa : listaDeTarefas) {
                            System.out.println("ID: " + tarefa.getId() + " | Descrição: " + tarefa.getDescricao() + " | Criador: " + tarefa.getCriador());
                        }
                        break;
                    case 5:
                        sair = true;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
            scanner.close();
        }

        // Foi mais ou menos 3 horas pra eu acertar isso dai
        private static Usuario login(Scanner scanner, Administrador admin, UsuarioComum usuarioComum) {
            System.out.println("Bem-vindo ao sistema de Lista de Tarefas");
            System.out.println("Faça login para continuar");

            while (true) {
                System.out.println("Digite seu nome de usuário:");
                String nome = scanner.nextLine();
                System.out.println("Digite sua senha:");
                String senha = scanner.nextLine();

                if (admin.nome.equals(nome) && admin.validarSenha(senha)) {
                    System.out.println("Login bem-sucedido como Administrador!");
                    return admin;
                } else if (usuarioComum.nome.equals(nome) && usuarioComum.validarSenha(senha)) {
                    System.out.println("Login bem-sucedido como Usuário Comum!");
                    return usuarioComum;
                } else {
                    System.out.println("Usuário ou senha inválidos. Tente novamente.");
                }
            }
        }
    }
}
