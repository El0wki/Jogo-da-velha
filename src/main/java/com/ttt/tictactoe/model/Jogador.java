package com.ttt.tictactoe.model;

public enum Jogador {
    HUMANO(1), MAQUINA(2);

    private final int valor;

    Jogador(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static Jogador outro(Jogador jogador) {
        return (jogador == HUMANO) ? MAQUINA : HUMANO;
    }
}
