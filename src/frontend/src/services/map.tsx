import api from "utils/axios";

const mapService = {
  async searchGeoText(searchText: string) {
    return await api.get("/search", {
      params: { searchText },
    });
  },
};

export default mapService;
