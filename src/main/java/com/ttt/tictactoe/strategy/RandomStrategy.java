package com.ttt.tictactoe.strategy;

import com.ttt.tictactoe.model.Tabuleiro;
import com.ttt.tictactoe.model.Jogador;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomStrategy implements JogadorStrategy {

    private final Random random = new Random();

    @Override
    public int selecionarJogada(Tabuleiro tabuleiro, Jogador jogador) {
        List<Integer> disponiveis = tabuleiro.getJogadasDisponiveis();
        if (disponiveis.isEmpty()) return -1;
        return disponiveis.get(random.nextInt(disponiveis.size()));
    }
}
      