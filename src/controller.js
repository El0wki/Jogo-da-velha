import "./components/ticTacToe.js";
import "./components/floatingLabel.js";

class Controller {
  constructor() {
    this.view = null;
    this.mask = null;
    this.toastNotification = null;
    this.player = null;
    this.userData = null;
    this.addEventListeners();
  }

  async importView() {
    const module = await import("./view.js");
    return new module.View();
  }

  async importPlayer() {
    const module = await import("./model.js");
    return new module.Player();
  }

  async importGame() {
    const module = await import("./model.js");
    return new module.Game();
  }

  async importMasks() {
    const module = await import("./global/utils.js");
    return module.addMasks;
  }

  async importToastNotification() {
    const module = await import("./global/toastNotification.js");
    return new module.ToastNotification();
  }

  async addEventListeners() {
    if (!this.view) this.view = await this.importView();
    if (!this.toastNotification)
      this.toastNotification = await this.importToastNotification();

    this.view.userData.return.addEventListener("click", () => {
      this.view.disableDataFields();
      this.view.enableUserDataFields();
    });

    this.view.userButtons.register.addEventListener("click", () => {
      this.view.enableDataFields();
      this.view.enableRegisterFields();
    });

    this.view.userButtons.login.addEventListener("click", () => {
      this.view.enableDataFields();
      this.view.enableLoginFields();
    });

    this.view.userData.register.addEventListener("submit", async (e) => {
      e.preventDefault();
      const username = this.view.userData.register.registerUser.value.trim();
      if (!username) return;

      const { Player } = await import("./model.js");
      this.player = new Player(username);
      this.userData = username;

      localStorage.setItem(
        this.userData,
        JSON.stringify({
          name: this.player.name,
          pontos: this.player.pontos,
          jogos: this.player.jogos,
        })
      );

      this.view.enableGameOptionsFields();
      this.toastNotification.createNotification({
        message: "Username registrado com sucesso!",
        color: "green",
      });
    });

    this.view.userData.login.addEventListener("submit", async (e) => {
      e.preventDefault();
      const username = this.view.userData.login.loginUser.value.trim();
      if (!username) return;

      const raw = localStorage.getItem(username);
      if (!raw) {
        this.toastNotification.createNotification({
          message: "Falha no login: Username não registrado",
          color: "red",
        });
        return;
      }

      let obj;
      try {
        obj = JSON.parse(raw);
      } catch {
        this.toastNotification.createNotification({
          message: "Dados do usuário corrompidos",
          color: "red",
        });
        return;
      }
      const { Player } = await import("./model.js");
      this.player = new Player(
        obj.name || username,
        obj.pontos || 0,
        obj.jogos || 0
      );
      this.userData = username;
      this.view.enableGameOptionsFields();
    });

    this.view.gameOptions.exit.addEventListener("click", () => {
      this.view.enableExitFields();
      this.player = null;
    });

    this.view.levelGrid.exit.addEventListener("click", () => {
      this.view.enableExitFields();
      this.player = null;
    });

    this.view.gameOptions.jxb.addEventListener("click", async () => {
      this.view.enableTicTacToe();
      this.view.enableDifficulty();
    });

    let game;

    this.view.difficulty.addEventListener("submit", async (e) => {
      e.preventDefault();
      game = null;
      this.view.ticTacToe.style.display = "grid";
      this.view.difficulty.style.display = "none";
      game = await this.importGame();
      const sla = await game.criarJogo(e.submitter.dataset.level);
      this.view.out.style.display = "flex";

      console.log(sla.id);
    });

    this.view.out.exit.addEventListener("click", () => {
      this.player = null;
      game = null;
      this.view.userButtons.style.display = "flex";
      this.view.ticTacToe.style.display = "none";
      this.view.out.style.display = "none";
    });

    for (let i = 0; i < 9; i++) {
      this.view.ticTacToe[i].addEventListener("click", async (e) => {
        const el = e.currentTarget;
        const idx = Number(el.dataset.index);
        if (el.dataset.state) return;

        try {
          this.view.ticTacToe.classList.add("disabled");

          const res = await game.fazerMovimento(idx);
          const board =
            res.tabuleiro || res.tabuleiroAtual || res.tabuleiro || [];

          for (let j = 0; j < 9; j++) {
            const cell = this.view.ticTacToe[j];
            const val = board[j];
            if (!cell) continue;
            if (val === "X" || val === "x") cell.dataset.state = "x";
            else if (val === "O" || val === "o") cell.dataset.state = "o";
            else delete cell.dataset.state;
          }
          if (res.vencedor == "HUMANO") {
            this.toastNotification.createNotification({
              message: "PARABÉNS! VOCÊ VENCEU!",
              color: "green",
            });
            this.player.incrementarJogos();
          }
          if (res.status == "EMPATE") {
            this.toastNotification.createNotification({
              message: "EMPATE! Mais sorte na próxima!",
              color: "gray",
            });
          }
          if (res.vencedor == "MAQUINA") {
            this.toastNotification.createNotification({
              message: "DERROTA! Mais sorte na próxima!",
              color: "red",
            });
          }
          console.log(res);
        } catch (err) {
          console.error("Movimento inválido:", err);
          this.toastNotification.createNotification({
            message: "Erro no movimento",
            color: "red",
          });
        } finally {
          if (this.player) {
            localStorage.setItem(
              this.userData,
              JSON.stringify({
                name: this.player.name,
                pontos: this.player.pontos,
                jogos: this.player.jogos,
              })
            );
          }
          console.log(this.player._jogos, this.player._pontos);
          console.log(localStorage);
          this.view.ticTacToe.classList.remove("disabled");
          console.log(await game.obterResumoPlacar());
        }
      });
    }
  }
}
document.addEventListener("DOMContentLoaded", async () => {
  new Controller();
});
