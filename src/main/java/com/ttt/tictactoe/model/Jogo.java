package com.ttt.tictactoe.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Jogo {

    private UUID id;
    private Tabuleiro tabuleiro;
    private ModoDeJogo modo;
    private Jogador jogadorAtual;
    private StatusDoJogo status;
    private Jogador vencedor;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Jogo(ModoDeJogo modo, Jogador jogadorInicial) {
        this.id = UUID.randomUUID();
        this.tabuleiro = new Tabuleiro();
        this.modo = modo;
        this.jogadorAtual = jogadorInicial;
        this.status = StatusDoJogo.EM_ANDAMENTO;
        this.inicio = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public ModoDeJogo getModo() {
        return modo;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(Jogador jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public StatusDoJogo getStatus() {
        return status;
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    public void setVencedor(Jogador vencedor) {
        this.vencedor = vencedor;
        this.status = (vencedor == null) ? StatusDoJogo.EMPATE : StatusDoJogo.VITORIA;
        this.fim = LocalDateTime.now();
    }

    public boolean isFinalizado() {
        return status != StatusDoJogo.EM_ANDAMENTO;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void reiniciar(Jogador jogadorInicial) {
        this.tabuleiro = new Tabuleiro();
        this.jogadorAtual = jogadorInicial;
        this.status = StatusDoJogo.EM_ANDAMENTO;
        this.vencedor = null;
        this.inicio = LocalDateTime.now();
        this.fim = null;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "modo=" + modo +
                ", jogadorAtual=" + jogadorAtual +
                ", status=" + status +
                ", vencedor=" + vencedor +
                '}';
    }
}
