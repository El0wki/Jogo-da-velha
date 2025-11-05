export class Player {
  constructor(pontos = 0, jogos = 0) {
    this._pontos = Number(pontos) || 0;
    this._jogos = Number(jogos) || 0;
  }

  get pontos() {
    return this._pontos;
  }
  set pontos(v) {
    this._pontos = Number(v) || 0;
  }

  get jogos() {
    return this._jogos;
  }
  set jogos(v) {
    this._jogos = Number(v) || 0;
  }

  incrementarPontos() {
    this.pontos++;
  }

  incrementarJogos() {
    this.jogos++;
  }

  toJSON() {
    return { name: this.name, pontos: this.pontos, jogos: this.jogos };
  }

  static fromJSON(json) {
    const obj = typeof json === "string" ? JSON.parse(json) : json || {};
    return new Player(obj.name || "", obj.pontos || 0, obj.jogos || 0);
  }
}

export class Game {
  constructor() {
    this.id = null;
    this.baseURL = "http://127.0.0.1:8081/api/jogos";
  }

  async criarJogo(modo = "MINIMAX", humanoInicia = true) {
    const res = await fetch(`${this.baseURL}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ modo, humanoInicia }),
    });
    if (!res.ok) throw new Error("Erro ao criar jogo: " + res.status);
    const JsonRes = await res.json();
    this.id = JsonRes.id;
    return JsonRes;
  }

  async fazerMovimento(posicao) {
    const res = await fetch(`${this.baseURL}/${this.id}/movimento`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ posicao }),
    });
    if (!res.ok) throw new Error("Erro no movimento: " + res.status);
    return res.json();
  }

  async obterPlacar() {
    const res = await fetch(`${this.baseURL}/placar`, {
      method: "GET",
      headers: { Accept: "application/json" },
    });
    if (!res.ok) throw new Error(`Erro ao obter placar: ${res.status}`);
    return res.json();
  }

  async obterResumoPlacar() {
    const res = await fetch(`${this.baseURL}/placar/resumo`, {
      method: "GET",
      headers: { Accept: "text/plain" },
    });
    if (!res.ok) throw new Error(`Erro ao obter resumo: ${res.status}`);
    return res.text();
  }
}
