package com.ttt.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro implements Cloneable {

    private int[][] matriz = new int[3][3];

    public List<Integer> getJogadasDisponiveis() {
        List<Integer> jogadas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == 0) {
                    jogadas.add(i * 3 + j);
                }
            }
        }
        return jogadas;
    }

    public boolean aplicarJogada(int posicao, Jogador jogador) {
        int linha = posicao / 3;
        int coluna = posicao % 3;
        if (matriz[linha][coluna] == 0) {
            matriz[linha][coluna] = jogador == Jogador.HUMANO ? 1 : 2;
            return true;
        }
        return false;
    }

    public boolean estaCompleto() {
        return getJogadasDisponiveis().isEmpty();
    }

    public Jogador getVencedor() {
        for (int i = 0; i < 3; i++) {
            if (matriz[i][0] != 0 && matriz[i][0] == matriz[i][1] && matriz[i][1] == matriz[i][2])
                return jogadorPorValor(matriz[i][0]);
            if (matriz[0][i] != 0 && matriz[0][i] == matriz[1][i] && matriz[1][i] == matriz[2][i])
                return jogadorPorValor(matriz[0][i]);
        }
        if (matriz[0][0] != 0 && matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2])
            return jogadorPorValor(matriz[0][0]);
        if (matriz[0][2] != 0 && matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0])
            return jogadorPorValor(matriz[0][2]);
        return null;
    }

    public boolean jogoEncerrado() {
        return getVencedor() != null || estaCompleto();
    }

    private Jogador jogadorPorValor(int valor) {
        return (valor == 1) ? Jogador.HUMANO : Jogador.MAQUINA;
    }

    @Override
    public Tabuleiro clone() {
        Tabuleiro clone = new Tabuleiro();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                clone.matriz[i][j] = this.matriz[i][j];
        return clone;
    }

    public int[][] getMatriz() {
        return matriz;
    }
}
