package com.ttt.tictactoe.service;

import com.ttt.tictactoe.model.*;
import com.ttt.tictactoe.repository.PlacarRepository;
import com.ttt.tictactoe.strategy.JogadorStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JogoService {

    private final EstrategiaFactory estrategiaFactory;
    private final PlacarRepository placarRepository;
    private final Map<UUID, Jogo> jogos = new HashMap<>();

    public JogoService(EstrategiaFactory estrategiaFactory, PlacarRepository placarRepository) {
        this.estrategiaFactory = estrategiaFactory;
        this.placarRepository = placarRepository;
    }

    public Jogo criarJogo(ModoDeJogo modo, Jogador jogadorInicial) {
        Jogo jogo = new Jogo(modo, jogadorInicial);
        jogos.put(jogo.getId(), jogo);
        return jogo;
    }

    public Jogo jogar(UUID idJogo, int posicao) {
        Jogo jogo = jogos.get(idJogo);
        if (jogo == null || jogo.isFinalizado()) {
            throw new IllegalStateException("Jogo inválido ou já finalizado.");
        }

        Tabuleiro tabuleiro = jogo.getTabuleiro();
        Jogador atual = jogo.getJogadorAtual();

        if (posicao == -1 && atual == Jogador.MAQUINA) {
            JogadorStrategy estrategia = estrategiaFactory.getEstrategia(jogo.getModo());
            int jogadaIA = estrategia.selecionarJogada(tabuleiro, atual);
            tabuleiro.aplicarJogada(jogadaIA, atual);
            finalizarSeEncerrado(jogo, tabuleiro, atual);
            return jogo;
        }

        boolean jogadaValida = tabuleiro.aplicarJogada(posicao, atual);
        if (!jogadaValida) throw new IllegalArgumentException("Posição inválida!");

        if (finalizarSeEncerrado(jogo, tabuleiro, atual)) return jogo;

        jogo.setJogadorAtual(Jogador.outro(atual));
        JogadorStrategy estrategia = estrategiaFactory.getEstrategia(jogo.getModo());
        int jogadaIA = estrategia.selecionarJogada(tabuleiro, jogo.getJogadorAtual());
        tabuleiro.aplicarJogada(jogadaIA, jogo.getJogadorAtual());

        finalizarSeEncerrado(jogo, tabuleiro, jogo.getJogadorAtual());
        if (!jogo.isFinalizado()) jogo.setJogadorAtual(Jogador.outro(jogo.getJogadorAtual()));

        return jogo;
    }

    private boolean finalizarSeEncerrado(Jogo jogo, Tabuleiro tabuleiro, Jogador atual) {
        if (tabuleiro.getVencedor() == atual) {
            jogo.setVencedor(atual);
            placarRepository.salvarResultado(jogo);
            return true;
        }
        if (tabuleiro.estaCompleto()) {
            jogo.setVencedor(null);
            placarRepository.salvarResultado(jogo);
            return true;
        }
        return false;
    }

    public Jogo getJogo(UUID idJogo) {
        return jogos.get(idJogo);
    }

    public List<String> getPlacar() {
        return placarRepository.lerTodosResultados();
    }

    public String getResumoPlacar() {
        return placarRepository.calcularResumo();
    }

    public void limparPlacar() {
        placarRepository.limparPlacar();
    }
}
