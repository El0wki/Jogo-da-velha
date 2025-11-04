export class FloatingLabel extends HTMLElement {
  connectedCallback() {
    const id = this.getAttribute("id") || "";

    const text = this.getAttribute("text") || "";

    const type = this.getAttribute("type") || "text";

    // evita XSS simples escapando o texto do label
    const esc = (s) =>
      String(s)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;");

    this.innerHTML = `
      <label for="${id}">${esc(text)}</label>
      <input type=${type} id="${id}" placeholder=""/>
    `;
    if (this.hasAttribute("id")) this.removeAttribute("id");
  }
}

customElements.define("floating-label", FloatingLabel);
