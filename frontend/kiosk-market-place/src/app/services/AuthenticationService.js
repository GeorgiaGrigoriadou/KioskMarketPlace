// import { myaxios } from "@/app/util/myaxios";

// import { GET_QUIZ_ANSWER_ENDPOINT } from "@/app/api/endpoints.js";

class AuthenticationService {
  constructor() {
    this.userId = localStorage.getItem('userId') ?? "2";
    this.admin = this.userId == "1" || this.userId == 1;
  }

  getUserId() {
    return this.userId;
  }

  setUserId(value) {
    this.userId = value;
    this.admin = this.userId == "1" || this.userId == 1;

    localStorage.setItem('userId', value);
    localStorage.setItem('admin', this.admin);
  }

  isAdministrator() {
    return this.admin;
  }
}

export default new AuthenticationService();