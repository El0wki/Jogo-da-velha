export class View {
  constructor() {
    this.userLogged = null;
    this.userData.style.display = "none";
    this.userDataRegister.style.display = "none";
    this.userDataLogin.style.display = "none";
  }

  get userButtons(){
    return document.querySelector("#user-buttons")
  }

  get userButtonsBtn(){
    return document.querySelectorAll("#user-buttons > *")
  }

  get userData(){
    return document.querySelector("#user-data")
  }

  get userDataRegister(){
    return document.querySelector("#user-data .register")
  }

  get userDataLogin(){
    return document.querySelector("#user-data .login")
  }

  
  initDataFields(){
    this.userData.style.display = "flex";
    this.userButtons.style.display = "none";
  }


}
