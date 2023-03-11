import { Schedule } from "react-app-env";
import api from "utils/axios";

const scheduleService = {
  async addSchedule(schedule: Schedule) {
    const response = await api.post("/schedules", schedule);
    return response.data;
  },

  async getSchedules() {
    const response = await api.get("/schedules");
    return response.data;
  },

  async getSchedule(schedule: Schedule) {
    const response = await api.get(`/schedules/${schedule.id}`);
    return response.data;
  },

  async deleteSchedule(schedule: Schedule) {
    const response = await api.delete(`/schedules/${schedule.id}`);
    return response.data;
  },
};

export default scheduleService;
