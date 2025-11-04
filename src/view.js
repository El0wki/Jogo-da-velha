export class View {
  constructor() {
    this.userLogged = null;
    this.userData.style.display = "none";
    this.userDataRegister.style.display = "none";
    this.userDataLogin.style.display = "none";
  }

  get userButtonsBtn() {
    return document.querySelectorAll("#user-buttons > *");
  }

  get userData() {
    return document.querySelector("#user-data");
  }

  get userDataInputs() {
    return document.querySelectorAll("#user-data input");
  }

  get userDataRegister() {
    return document.querySelector("#user-data .register");
  }

  get userDataBtns() {
    return document.querySelector("#user-data .register button");
  }

  get userDataLogin() {
    return document.querySelector("#user-data .login");
  }

  get returnBtn() {
    return document.querySelector("#user-data .return");
  }

  showDataFields() {
    this.userData.style.display = "flex";
    this.userButtons.style.display = "none";
  }

  invertDataFields() {
    this.userData.style.display = "none";
    this.userButtons.style.display = "flex";
    this.userDataRegister.style.display = "none";
    this.userDataLogin.style.display = "none";
    this.userDataInputs.forEach((input) => (input.value = ""));
  }
}
