package com.ttt.tictactoe.strategy;

import com.ttt.tictactoe.model.Tabuleiro;
import com.ttt.tictactoe.model.Jogador;

public interface JogadorStrategy {
    int selecionarJogada(Tabuleiro tabuleiro, Jogador jogador);
}
