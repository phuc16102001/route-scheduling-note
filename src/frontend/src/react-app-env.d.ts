/// <reference types="react-scripts" />

interface Place {
  id?: number;
  name: string;
  address: string;
  coordinates: {
    lat: number;
    lng: number;
  };
}

interface Schedule {
  name: string;
  total_distance: number;
  duration: number;
}

interface PlaceNote {
  place: Place;
  note: string;
}