package com.ttt.tictactoe.repository;

import com.ttt.tictactoe.model.Jogo;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PlacarRepository {

    private static final String ARQUIVO_PLACAR = "src/main/resources/placar.txt";

    public void salvarResultado(Jogo jogo) {
        try {
            Files.createDirectories(Paths.get("src/main/resources"));
            File arquivo = new File(ARQUIVO_PLACAR);
            if (!arquivo.exists()) arquivo.createNewFile();

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo, true))) {
                String vencedor = (jogo.getVencedor() == null) ? "EMPATE" : jogo.getVencedor().name();
                String modo = jogo.getModo().name();
                String data = jogo.getFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                escritor.write(String.format("[%s] Modo: %s | Resultado: %s", data, modo, vencedor));
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar resultado no placar: " + e.getMessage());
        }
    }

    public List<String> lerTodosResultados() {
        try {
            if (!Files.exists(Paths.get(ARQUIVO_PLACAR))) {
                return List.of("Nenhum jogo registrado ainda.");
            }
            return Files.lines(Paths.get(ARQUIVO_PLACAR)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o placar: " + e.getMessage());
        }
    }

    public String calcularResumo() {
        int vitoriasHumano = 0;
        int vitoriasMaquina = 0;
        int empates = 0;

        try {
            if (!Files.exists(Paths.get(ARQUIVO_PLACAR))) return "H - 0 | M - 0 | E - 0";

            for (String linha : Files.readAllLines(Paths.get(ARQUIVO_PLACAR))) {
                if (linha.contains("HUMANO")) vitoriasHumano++;
                else if (linha.contains("MAQUINA")) vitoriasMaquina++;
                else if (linha.contains("EMPATE")) empates++;
            }

            return String.format("H - %d | M - %d | E - %d", vitoriasHumano, vitoriasMaquina, empates);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao calcular o placar: " + e.getMessage());
        }
    }

    public void limparPlacar() {
        try {
            Files.deleteIfExists(Paths.get(ARQUIVO_PLACAR));
            Files.createFile(Paths.get(ARQUIVO_PLACAR));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao limpar o placar: " + e.getMessage());
        }
    }
}
