import api from "utils/axios";

const authService = {
  async signup(username: string, password: string) {
    const response = await api.post("/signup", {
        username: username,
        password: password
    });
    return response.data;
  },
  
  async login(username: string, password: string) {
    const response = await api.post("/login", {
        username: username,
        password: password
    });
    return response.data;
  }
};

export default authService;
