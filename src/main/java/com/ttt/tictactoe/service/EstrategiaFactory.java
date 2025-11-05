package com.ttt.tictactoe.service;

import com.ttt.tictactoe.model.ModoDeJogo;
import com.ttt.tictactoe.strategy.JogadorStrategy;
import com.ttt.tictactoe.strategy.MinimaxStrategy;
import com.ttt.tictactoe.strategy.MonteCarloStrategy;
import com.ttt.tictactoe.strategy.RandomStrategy;

import org.springframework.stereotype.Service;

@Service
public class EstrategiaFactory {

    private final RandomStrategy randomStrategy;
    private final MonteCarloStrategy monteCarloStrategy;
    private final MinimaxStrategy minimaxStrategy;

    public EstrategiaFactory(RandomStrategy randomStrategy, MonteCarloStrategy monteCarloStrategy, MinimaxStrategy minimaxStrategy) {
        this.randomStrategy = randomStrategy;
        this.monteCarloStrategy = monteCarloStrategy;
        this.minimaxStrategy = minimaxStrategy;
    }

    public JogadorStrategy getEstrategia(ModoDeJogo modo) {
        return switch (modo) {
            case RANDOM -> randomStrategy;
            case MONTE_CARLO -> monteCarloStrategy;
            case MINIMAX -> minimaxStrategy;
        };
    }
}
