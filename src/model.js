const dificuldade = ["facil", "intermediário", "dificil"];

const placar = ["n° partidas", "modo de jogo", "pontuaçao por jogador"];

const modo = ["player x bot", "bot x bot", "player x player", "avanço"];

export class Jogo {
  constructor() {
    this.grid = {};
    for (let i = 0; i < 9; i++) {
      this.grid[i] = null;
    }
  }

  realizarJogada(player, square) {
    if (this.grid[square] != null) {
      throw new Error("Jogada inválida");
    }

    this.grid[square] = true;
  }
}
