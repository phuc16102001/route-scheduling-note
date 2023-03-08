import api from "utils/axios";

const scheduleService = {
  async addSchedule(schedule: Schedule) {
    return api.post('/schedules', schedule);
  },
};

export default scheduleService;
