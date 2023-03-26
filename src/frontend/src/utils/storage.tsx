import { User } from "react-app-env";

const storageUtil = {
  saveToken(token: string) {
    window.localStorage.setItem("token", token);
  },

  loadToken(): string | null {
    return window.localStorage.getItem("token");
  },

  saveUser(user: User) {
    window.localStorage.setItem("user", JSON.stringify(user));
  },

  loadUser(): User | null {
    const jsonString = window.localStorage.getItem("user");
    if (jsonString) return JSON.parse(jsonString);
    return null;
  },

  removeToken() {
    window.localStorage.removeItem("token");
  },

  removeUser() {
    window.localStorage.removeItem("user");
  },
};
export default storageUtil;
