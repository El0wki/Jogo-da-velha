package com.ttt.tictactoe.model;

//import java.time.LocalDateTime;

public class Jogada {

    private int posicao;
    private Jogador jogador;

    public Jogada(int posicao, Jogador jogador) {
        this.posicao = posicao;
        this.jogador = jogador;
    }

    public int getPosicao() {
        return posicao;
    }

    public Jogador getJogador() {
        return jogador;
    }
}
