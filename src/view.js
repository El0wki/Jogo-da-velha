export class View {
  constructor() {
    this.userData.style.display = "none";
    this.userData.register.style.display = "none";
    this.userData.login.style.display = "none";
    this.gameOptions.style.display = "none";
    this.levelGrid.style.display = "none";
    this.ticTacToe.style.display = "none";
    this.difficulty.style.display = "none";
    this.out.style.display = "none";
  }

  get out() {
    const root = document.querySelector(".out");
    root.exit = root.querySelector(".exit");
    root.return = root.querySelector(".return");
    return root;
  }
  get levelGrid() {
    const root = document.querySelector("#level-grid");
    root.exit = root.querySelector(".exit");
    return root;
  }

  get mainScreen() {
    const root = document.querySelector("#main-screen");
    return root;
  }
  get gameOptions() {
    const root = document.querySelector("#game-options");
    root.exit = root.querySelector(".exit");
    root.campanha = root.querySelector(".campanha");
    root.jxb = root.querySelector(".jxb");
    return root;
  }
  get userButtons() {
    const root = document.querySelector("#user-buttons");
    root.register = root.querySelector(".register");
    root.login = root.querySelector(".login");
    return root;
  }

  get userData() {
    const root = document.querySelector("#user-data");
    root.register = root.querySelector(".register");
    root.register.registerUser = root.register.querySelector("#register-user");
    root.register.send = root.register.querySelector('input[type="submit"]');
    root.login = root.querySelector(".login");
    root.login.loginUser = root.login.querySelector("#login-user");
    root.login.send = root.login.querySelector('input[type="submit"]');
    root.return = root.querySelector(".return");
    return root;
  }

  get ticTacToe() {
    const root = document.querySelector("tic-tac-toe");
    if (!root) return null;
    const cells = root.querySelectorAll("[data-index]");
    cells.forEach((el) => {
      const i = Number(el.dataset.index);
      if (!Number.isNaN(i)) root[i] = el;
    });
    return root;
  }

  get difficulty() {
    const root = document.querySelector("#difficulty");
    return root;
  }

  enableTicTacToe() {
    this.gameOptions.style.display = "none";
  }
  enableLevelGrid() {
    this.levelGrid.style.display = "flex";
    this.mainScreen.style.display = "none";
  }
  enableDataFields() {
    this.userData.style.display = "flex";
    this.userButtons.style.display = "none";
  }

  disableDataFields() {
    this.userData.style.display = "none";
    this.userButtons.style.display = "flex";
  }

  enableUserDataFields() {
    this.userData.register.style.display = "none";
    this.userData.login.style.display = "none";
  }

  enableGameOptionsFields() {
    this.userData.style.display = "none";
    this.gameOptions.style.display = "flex";
  }

  enableExitFields() {
    this.userButtons.style.display = "flex";
    this.mainScreen.style.display = "flex";
    this.gameOptions.style.display = "none";
    this.levelGrid.style.display = "none";
  }

  enableRegisterFields() {
    this.userData.register.style.display = "flex";
    this.userData.login.style.display = "none";
  }

  enableLoginFields() {
    this.userData.login.style.display = "flex";
    this.userData.register.style.display = "none";
  }
  enableDifficulty() {
    this.gameOptions.style.display = "none";
    this.difficulty.style.display = "flex";
  }
}
