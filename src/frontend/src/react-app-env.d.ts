/// <reference types="react-scripts" />

interface Coordinates {
  lat: number;
  lng: number;
};

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

interface Schedule {
  id?: number;
  name: string;
  placeNotes: PlaceNote[];
  stops?: Coordinates[];
}

