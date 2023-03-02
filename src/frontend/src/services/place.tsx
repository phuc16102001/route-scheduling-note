import api from "utils/axios";

const placeService = {
  async addPlace(place: Place) {
    return api.post("/places", place);
  },

  async getPlaces() {
    return api.get("/places");
  },

  async deletePlace(place: Place) {
    const { id } = place;
    return api.delete(`/places/${id}`);
  },
};

export default placeService;
