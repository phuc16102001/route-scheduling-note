import { Place } from "react-app-env";
import api from "utils/axios";

const placeService = {
  async addPlace(place: Place) {
    const response = await api.post("/places", place);
    return response.data;
  },

  async getPlaces() {
    const response = await api.get("/places");
    return response.data;
  },

  async getPlace(place: Place) {
    const response = await api.get(`/places/${place.id}`);
    return response.data;
  },

  async deletePlace(place: Place) {
    const { id } = place;
    const response = await api.delete(`/places/${id}`);
    return response.data;
  },
};

export default placeService;
