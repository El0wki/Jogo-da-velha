package com.ttt.tictactoe.strategy;

import com.ttt.tictactoe.model.Tabuleiro;
import com.ttt.tictactoe.model.Jogador;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MonteCarloStrategy implements JogadorStrategy {

    private final Random random = new Random();
    private final int simulacoesPorJogada = 100; // qntd de simulacoes

    @Override
    public int selecionarJogada(Tabuleiro tabuleiro, Jogador jogador) {
        List<Integer> jogadas = tabuleiro.getJogadasDisponiveis();
        if (jogadas.isEmpty()) return -1;

        double melhorTaxaDeVitoria = -1;
        int melhorJogada = jogadas.get(0);

        for (int jogada : jogadas) {
            int vitorias = 0;
            int empates = 0;

            for (int i = 0; i < simulacoesPorJogada; i++) {
                Tabuleiro simulado = tabuleiro.clone();
                simulado.aplicarJogada(jogada, jogador);

                Jogador resultado = simularPartidaAleatoria(simulado, Jogador.outro(jogador));

                if (resultado == jogador) vitorias++;
                else if (resultado == null) empates++;
            }

            double taxaDeVitoria = (vitorias + 0.5 * empates) / simulacoesPorJogada;

            if (taxaDeVitoria > melhorTaxaDeVitoria) {
                melhorTaxaDeVitoria = taxaDeVitoria;
                melhorJogada = jogada;
            }
        }

        return melhorJogada;
    }

    private Jogador simularPartidaAleatoria(Tabuleiro tabuleiro, Jogador jogadorAtual) {
        while (!tabuleiro.jogoEncerrado()) {
            List<Integer> jogadas = tabuleiro.getJogadasDisponiveis();
            if (jogadas.isEmpty()) break;

            int jogada = jogadas.get(random.nextInt(jogadas.size()));
            tabuleiro.aplicarJogada(jogada, jogadorAtual);
            jogadorAtual = Jogador.outro(jogadorAtual);
        }
        return tabuleiro.getVencedor();
    }
}
