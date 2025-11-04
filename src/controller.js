import "./components/ticTacToe.js";
import "./components/floatingLabel.js";

class Controller {
  constructor() {
    this.view = null;
    this.mask = null;
    this.addEventListeners();
  }

  async importView() {
    const module = await import("./view.js");
    return new module.View();
  }

  async importModel() {
    const module = await import("./model.js");
    return new module.Jogo();
  }

  async importMasks() {
    const module = await import("./global/utils.js");
    return module.addMasks;
  }

  async addEventListeners() {
    if (!this.view) this.view = await this.importView();
    if (!this.mask) this.mask = await this.importMasks();

    this.view.returnBtn.addEventListener("click", () => {
      this.view.invertDataFields();
    });

    this.view.userButtonsBtn.forEach((btn) => {
      btn.addEventListener("click", () => {
        this.view.showDataFields();
      });

      if (btn.id == "register") {
        btn.addEventListener("click", () => {
          this.view.userDataRegister.style.display = "flex";
        });
      }
      if (btn.id == "login") {
        btn.addEventListener("click", () => {
          this.view.userDataLogin.style.display = "flex";
        });
      }
      console.log(btn);
    });
  }
}
document.addEventListener("DOMContentLoaded", async () => {
  new Controller();
});
