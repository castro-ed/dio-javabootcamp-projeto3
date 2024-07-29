package com.banco;

import java.time.LocalDateTime;

public class Movimentacao {
    private String tipo;
    private double valor;
    private LocalDateTime dataHora;

    public Movimentacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f em %s", tipo, valor, dataHora);
    }
}
