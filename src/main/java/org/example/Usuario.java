package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String nome;
    protected String senha;
    protected Role role;


    public Usuario(String nome, String senha, Role role) {
        this.nome = nome;
        this.senha = criptografarSenha(senha);  // Exemplo de criptografia simples
        this.role = role;
    }


    private String criptografarSenha(String senha) {
        return Integer.toHexString(senha.hashCode()); // Exemplo básico de hash
    }


    public abstract void adicionarTarefa(Tarefa tarefa, List<Tarefa> listaDeTarefas);
    public abstract void removerTarefa(int id, List<Tarefa> listaDeTarefas);
    public abstract void modificarTarefa(int id, String novaDescricao, List<Tarefa> listaDeTarefas);


    public boolean validarSenha(String senha) {
        return this.senha.equals(criptografarSenha(senha));
    }
}
