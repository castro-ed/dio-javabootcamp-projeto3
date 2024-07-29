package com.banco;

import java.util.HashMap;
import java.util.Map;

public class BancoDigital {
    private String nome;
    private Map<String, Conta> contas;

    public BancoDigital(String nome) {
        this.nome = nome;
        this.contas = new HashMap<>();
    }

    public void adicionarConta(Conta conta) {
        contas.put(conta.getCliente().getEmail(), conta);
    }

    public Conta autenticar(String email, String telefone) {
        Conta conta = contas.get(email);
        if (conta != null && conta.getCliente().getTelefone().equals(telefone)) {
            return conta;
        }
        return null;
    }

    public Map<String, Conta> getContas() {
        return contas;
    }
}

