import axios from "axios";
import config from "config/config";

const api = axios.create({
  baseURL: `${config.SERVER_DOMAIN}:${config.SERVER_PORT}`,
});

export default api;
