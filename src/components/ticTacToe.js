export class TicTacToe extends HTMLElement {
  async connectedCallback() {
    this.innerHTML = `
        <div data-index="0"></div>
        <div data-index="1"></div>
        <div data-index="2"></div>
        <div data-index="3"></div>
        <div data-index="4"></div>
        <div data-index="5"></div>
        <div data-index="6"></div>
        <div data-index="7"></div>
        <div data-index="8"></div>`;
  }
}

customElements.define("tic-tac-toe", TicTacToe);
