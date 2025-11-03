export class TicTacToe extends HTMLElement {
  async connectedCallback() {
    this.innerHTML = `
        <div id="0">0</div>
        <div id="1">1</div>
        <div id="2">2</div>
        <div id="3">3</div>
        <div id="4">4</div>
        <div id="5">5</div>
        <div id="6">6</div>
        <div id="7">7</div>
        <div id="8">8</div>`;
  }
}

customElements.define("tic-tac-toe", TicTacToe);
