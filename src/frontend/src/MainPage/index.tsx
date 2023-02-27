import { Row } from "antd";
import { Col } from "antd/es/grid";
import { LatLngExpression } from "leaflet";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import { Route, Routes } from "react-router-dom";
import ListPlaces from "../ListPlaces";
import ListSchedules from "../ListSchedules";

const position: LatLngExpression = [51.505, -0.09];

const MainPage = () => {
  return (
    <MapContainer
      style={{ height: "100vh" }}
      center={position}
      zoom={13}
      scrollWheelZoom={false}
    >
      <TileLayer url="https://tile.openstreetmap.org/{z}/{x}/{y}.png" />
    </MapContainer>
  );
};

export default MainPage;
