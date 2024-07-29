package com.banco;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<Movimentacao> movimentacoes;

    public Conta(Cliente cliente) {
        this.agencia = 1;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.movimentacoes = new ArrayList<>();
    }

    public void depositar(double valor) {
        saldo += valor;
        movimentacoes.add(new Movimentacao("Depósito", valor));
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            movimentacoes.add(new Movimentacao("Saque", valor));
        }
    }

    public void transferir(double valor, Conta contaDestino) {
        if (valor <= saldo) {
            sacar(valor);
            contaDestino.depositar(valor);
            movimentacoes.add(new Movimentacao("Transferência para " + contaDestino.getCliente().getNome(), valor));
        }
    }

    public void imprimirExtrato() {
        System.out.println("=== Extrato da Conta ===");
        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println(movimentacao);
        }
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
