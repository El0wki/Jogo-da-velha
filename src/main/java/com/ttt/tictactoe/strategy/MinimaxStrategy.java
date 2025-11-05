package com.ttt.tictactoe.strategy;

import com.ttt.tictactoe.model.Tabuleiro;
import com.ttt.tictactoe.model.Jogador;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinimaxStrategy implements JogadorStrategy {

    @Override
    public int selecionarJogada(Tabuleiro tabuleiro, Jogador jogador) {
        int melhorJogada = -1;
        int melhorPontuacao = Integer.MIN_VALUE;

        for (int jogada : tabuleiro.getJogadasDisponiveis()) {
            Tabuleiro simulado = tabuleiro.clone();
            simulado.aplicarJogada(jogada, jogador);
            int pontuacao = minimax(simulado, Jogador.outro(jogador), false, jogador);
            if (pontuacao > melhorPontuacao) {
                melhorPontuacao = pontuacao;
                melhorJogada = jogada;
            }
        }
        return melhorJogada;
    }

    private int minimax(Tabuleiro tabuleiro, Jogador jogadorAtual, boolean maximizando, Jogador jogadorIA) {
        Jogador vencedor = tabuleiro.getVencedor();
        if (vencedor != null) {
            if (vencedor == jogadorIA) return 10;
            else return -10;
        } 
        if (tabuleiro.estaCompleto()) return 0;

        List<Integer> jogadas = tabuleiro.getJogadasDisponiveis();

        if (maximizando) {
            int melhorPontuacao = Integer.MIN_VALUE;
            for (int jogada : jogadas) {
                Tabuleiro novoTabuleiro = tabuleiro.clone();
                novoTabuleiro.aplicarJogada(jogada, jogadorAtual);
                int pontuacao = minimax(novoTabuleiro, Jogador.outro(jogadorAtual), false, jogadorIA);
                melhorPontuacao = Math.max(melhorPontuacao, pontuacao);
            }
            return melhorPontuacao;
        } else {
            int melhorPontuacao = Integer.MAX_VALUE;
            for (int jogada : jogadas) {
                Tabuleiro novoTabuleiro = tabuleiro.clone();
                novoTabuleiro.aplicarJogada(jogada, jogadorAtual);
                int pontuacao = minimax(novoTabuleiro, Jogador.outro(jogadorAtual), true, jogadorIA);
                melhorPontuacao = Math.min(melhorPontuacao, pontuacao);
            }
            return melhorPontuacao;
        }
    }
}
