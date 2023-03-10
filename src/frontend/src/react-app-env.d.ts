/// <reference types="react-scripts" />

import { LatLng } from "leaflet";

interface Coordinates {
  lat: number;
  lng: number;
}

interface Place {
  id?: number;
  name: string;
  address: string;
  coordinates?: Coordinates;
}
interface PlaceNote {
  place: Place;
  note: string;
}

interface Stop {
  stopOrder?: number;
  coordinates: Coordinates;
}

interface Schedule {
  id?: number;
  name: string;
  placeNotes?: PlaceNote[];
  stops?: Stops[];
  createAt?: number;
}

interface LatLngWithNote extends LatLng {
  note?: string;
}
