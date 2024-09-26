package org.example;

import java.util.ArrayList;
import java.util.List;

public class UsuarioComum extends Usuario {

    public UsuarioComum(String nome, String senha) {
        super(nome, senha, Role.USUARIO_COMUM);
    }

    @Override
    public void adicionarTarefa(Tarefa tarefa, List<Tarefa> listaDeTarefas) {

        if (tarefa.getCriador().equals(this.nome)) {
            listaDeTarefas.add(tarefa);
            System.out.println("Tarefa adicionada com sucesso.");
        } else {
            System.out.println("Permissão negada: Você só pode adicionar suas próprias tarefas.");
        }
    }

    @Override
    public void removerTarefa(int id, List<Tarefa> listaDeTarefas) {

        listaDeTarefas.removeIf(tarefa -> tarefa.getId() == id && tarefa.getCriador().equals(this.nome));
        System.out.println("Tarefa removida com sucesso.");
    }

    @Override
    public void modificarTarefa(int id, String novaDescricao, List<Tarefa> listaDeTarefas) {

        for (Tarefa tarefa : listaDeTarefas) {
            if (tarefa.getId() == id && tarefa.getCriador().equals(this.nome)) {
                tarefa.setDescricao(novaDescricao);
                System.out.println("Tarefa modificada com sucesso.");
            }
        }
    }
}

