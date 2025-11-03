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
  }
}
document.addEventListener("DOMContentLoaded", async () => {
  new Controller();
});
