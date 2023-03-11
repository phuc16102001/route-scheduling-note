import api from "utils/axios";

const mapService = {
  async searchGeoText(searchText: string) {
    const response = await api.get("/search", {
      params: { searchText },
    });
    return response.data;
  },
};

export default mapService;
