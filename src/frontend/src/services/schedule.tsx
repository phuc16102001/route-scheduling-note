import api from "utils/axios";

const scheduleService = {
  async addSchedule(schedule: Schedule) {
    return api.post('/schedules', schedule);
  },

  async getSchedules() {
    return api.get("/schedules");
  },

  async getSchedule(schedule: Schedule) {
    return api.get(`/schedules/${schedule.id}`);
  }
};

export default scheduleService;
