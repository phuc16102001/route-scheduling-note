import axios from "axios";
import config from "config/config";
import storageUtil from "./storage";

const api = axios.create({
  baseURL: `${config.SERVER_DOMAIN}:${config.SERVER_PORT}`,
});

api.interceptors.request.use((config) => {
  const token = storageUtil.loadToken();
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }

  return config;
});

export default api;
