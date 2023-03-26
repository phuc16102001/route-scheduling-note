import api from "utils/axios";

const userService = {
  async getUserInformation() {
    const response = await api.get("/user");
    return response.data;
  },
};

export default userService;
