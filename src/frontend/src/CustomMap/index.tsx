import { LatLngExpression } from "leaflet";
import React from "react";
import { MapContainer, TileLayer } from "react-leaflet";
import "./index.css";

const position: LatLngExpression = [51.505, -0.09];

const CustomMap = () => {
  return (
    <MapContainer
      className="mapContainer"
      center={position}
      zoom={13}
      zoomControl={false}
      scrollWheelZoom={true}
    >
      <TileLayer url="https://tile.openstreetmap.org/{z}/{x}/{y}.png" />
    </MapContainer>
  );
};

export default CustomMap;
