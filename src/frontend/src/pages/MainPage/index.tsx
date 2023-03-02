import { BrowserRouter, Route, Routes } from "react-router-dom";
import CustomMap from "components/CustomMap";
import ListPlaces from "components/ListPlaces";
import ListSchedules from "components/ListSchedules";
import MenuPage from "pages/MenuPage";
import AddPlace from "components/AddPlace";
import "./index.css";
import AddSchedule from "components/AddSchedule";
import { useState } from "react";
import { LatLng, LatLngExpression } from "leaflet";
import { MapContainer } from "react-leaflet";

const MainPage = () => {
  const [listMarker, setListMarker] = useState<LatLng[]>();
  const [listSegment, setListSegment] = useState<LatLngExpression[]>([]);

  const setSingleMarker = (marker: LatLng) => {
    setListMarker([marker]);
  };

  return (
    <>
      <BrowserRouter>
        <div className="floatPanel">
          <Routes>
            <Route path="/" element={<MenuPage />} />
            <Route path="/schedules" element={<ListSchedules />} />
            <Route path="/places" element={<ListPlaces />} />
            <Route path="/addSchedule" element={<AddSchedule />} />
            <Route
              path="/addPlace"
              element={
                <AddPlace
                  listMarker={listMarker}
                  setSingleMarker={setSingleMarker}
                />
              }
            />
          </Routes>
        </div>
      </BrowserRouter>

      <MapContainer
        className="mapContainer"
        zoom={13}
        center={[1, 1]}
        zoomControl={false}
        scrollWheelZoom={true}
      >
        <CustomMap
          setSingleMarker={setSingleMarker}
          listMarker={listMarker}
          listSegment={listSegment}
        />
      </MapContainer>
    </>
  );
};

export default MainPage;
