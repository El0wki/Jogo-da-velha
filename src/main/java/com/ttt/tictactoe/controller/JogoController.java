package com.ttt.tictactoe.controller;

import com.ttt.tictactoe.dto.CriarJogoRequest;
import com.ttt.tictactoe.dto.JogoDto;
import com.ttt.tictactoe.dto.JogadaRequestDto;
import com.ttt.tictactoe.model.Jogo;
import com.ttt.tictactoe.model.Jogador;
import com.ttt.tictactoe.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PostMapping
    public ResponseEntity<JogoDto> criarJogo(@RequestBody CriarJogoRequest request) {
        Jogador jogadorInicial = request.isHumanoInicia() ? Jogador.HUMANO : Jogador.MAQUINA;
        Jogo jogo = jogoService.criarJogo(request.getModo(), jogadorInicial);

        if (!request.isHumanoInicia() && !jogo.isFinalizado()) {
            jogo = jogoService.jogar(jogo.getId(), -1);
        }

        return ResponseEntity.ok(JogoDto.deJogo(jogo));
    }

    @PostMapping("/{id}/movimento")
    public ResponseEntity<JogoDto> fazerMovimento(@PathVariable UUID id, @RequestBody JogadaRequestDto movimentoRequest) {
        Jogo jogo = jogoService.jogar(id, movimentoRequest.getPosicao());
        return ResponseEntity.ok(JogoDto.deJogo(jogo));
    }

    @GetMapping("/placar")
    public ResponseEntity<List<String>> obterPlacar() {
        return ResponseEntity.ok(jogoService.getPlacar());
    }

    @GetMapping("/placar/resumo")
    public ResponseEntity<String> obterResumoPlacar() {
        return ResponseEntity.ok(jogoService.getResumoPlacar());
    }

    @DeleteMapping("/placar")
    public ResponseEntity<String> limparPlacar() {
        jogoService.limparPlacar();
        return ResponseEntity.ok("Placar limpo com sucesso!");
    }
}
