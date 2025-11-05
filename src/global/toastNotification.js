export class ToastNotification {
  constructor() {
    this.container = document.createElement("div");
    this.container.classList.add("toast-container");
    document.body.appendChild(this.container);
  }

  createNotification({ message, color, class: className = "toast-content" }) {
    const content = document.createElement("div");
    content.style.background = color;
    content.innerHTML = message;
    content.classList.add(className);
    this.container.appendChild(content);
    setTimeout(() => {
      content.remove();
    }, 2500);
  }
}
