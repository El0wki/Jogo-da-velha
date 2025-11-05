package com.ttt.tictactoe.dto;

import com.ttt.tictactoe.model.*;

import java.util.UUID;

public class JogoDto {

    private UUID id;
    private String[] tabuleiro;
    private String jogadorAtual;
    private String status;
    private String vencedor;
    private String modo;

    public static JogoDto deJogo(Jogo jogo) {
        JogoDto dto = new JogoDto();
        dto.id = jogo.getId();

        int[][] matriz = jogo.getTabuleiro().getMatriz();
        dto.tabuleiro = new String[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int valor = matriz[i][j];
                dto.tabuleiro[i * 3 + j] = valor == 1 ? "X" : valor == 2 ? "O" : "";
            }
        }

        dto.jogadorAtual = (jogo.getJogadorAtual() != null) ? jogo.getJogadorAtual().name() : "";
        dto.status = jogo.getStatus().name();
        dto.vencedor = (jogo.getVencedor() == null) ? "" : jogo.getVencedor().name();
        dto.modo = jogo.getModo().name();
        return dto;
    }

    public UUID getId() { return id; }
    public String[] getTabuleiro() { return tabuleiro; }
    public String getJogadorAtual() { return jogadorAtual; }
    public String getStatus() { return status; }
    public String getVencedor() { return vencedor; }
    public String getModo() { return modo; }
}
