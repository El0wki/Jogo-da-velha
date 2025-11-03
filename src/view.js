export class View {
  constructor() {
    this.userLogged = null;
  }

  get ticTacToe() {
    return document.querySelector("tic-tac-toe");
  }

  get register() {
    return document.querySelector(".register");
  }

  get login() {
    return document.querySelector(".login");
  }

  get gameOptions() {
    return document.querySelector(".gameOptions");
  }

  initTicTaCToe() {
    this.ticTacToe.style.display = "none";
  }
}
