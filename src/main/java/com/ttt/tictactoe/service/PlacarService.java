package com.ttt.tictactoe.service;

import com.ttt.tictactoe.model.Jogo;
import com.ttt.tictactoe.model.Jogador;
import org.springframework.stereotype.Service;

@Service
public class PlacarService {
    private int vitoriasHumanas = 0;
    private int vitoriasMaquina = 0;
    private int empates = 0;

    public void atualizarPlacar(Jogo jogo) {
        if (jogo.getVencedor() == Jogador.HUMANO) vitoriasHumanas++;
        else if (jogo.getVencedor() == Jogador.MAQUINA) vitoriasMaquina++;
        else empates++;
    }

    public String getPlacar() {
        return "Humano: " + vitoriasHumanas + " | Máquina: " + vitoriasMaquina + " | Empates: " + empates;
    }
}
