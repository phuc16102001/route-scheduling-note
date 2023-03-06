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
  const [draggable, setDraggable] = useState<boolean>(true);

  const setSingleMarkerCallback = (marker: LatLng, draggable: boolean = true) => {
    setListMarker([marker]);
    setDraggable(draggable);
  };

  const setListMarkerCallback = (markers: LatLng[]) => {
    setListMarker(markers);
    setDraggable(false);
  }

  return (
    <>
      <BrowserRouter>
        <div className="floatPanel">
          <Routes>
            <Route path="/" element={<MenuPage />} />
            <Route path="/schedules" element={<ListSchedules />} />
            <Route
              path="/places"
              element={<ListPlaces setSingleMarker={setSingleMarkerCallback} />}
            />
            <Route path="/addSchedule" element={<AddSchedule setListMarkerCallback={setListMarkerCallback}/>} />
            <Route
              path="/addPlace"
              element={
                <AddPlace
                  listMarker={listMarker}
                  setSingleMarkerCallback={setSingleMarkerCallback}
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
          setSingleMarker={setSingleMarkerCallback}
          listMarker={listMarker}
          listSegment={listSegment}
          draggable={draggable}
        />
      </MapContainer>
    </>
  );
};

export default MainPage;
