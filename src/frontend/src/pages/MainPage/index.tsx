import { BrowserRouter, Route, Routes } from "react-router-dom";
import CustomMap from "components/CustomMap";
import ListPlaces from "components/ListPlaces";
import ListSchedules from "components/ListSchedules";
import MenuPage from "pages/MenuPage";
import AddPlace from "components/AddPlace";
import "./index.css";
import AddSchedule from "components/AddSchedule";
import { useState } from "react";
import { LatLng } from "leaflet";
import { MapContainer } from "react-leaflet";

const MainPage = () => {
  const [listMarker, setListMarker] = useState<LatLng[]>();
  const [listLinePoint, setListLinePoint] = useState<LatLng[]>();
  const [draggable, setDraggable] = useState<boolean>(true);

  const setSingleMarkerCallback = (
    marker: LatLng,
    draggable: boolean = true
  ) => {
    setListMarker([marker]);
    setDraggable(draggable);
  };

  const setListMarkerCallback = (markers: LatLng[]) => {
    setListMarker(markers);
    setDraggable(false);
  };

  const setListLinePointCallback = (points: LatLng[]) => {
    setListLinePoint(points);
  };

  return (
    <>
      <BrowserRouter>
        <div className="floatPanel">
          <Routes>
            <Route path="/" element={<MenuPage />} />
            <Route
              path="/schedules"
              element={
                <ListSchedules
                  setListLinePointCallback={setListLinePointCallback}
                  setListMarkerCallback={setListMarkerCallback}
                />
              }
            />
            <Route
              path="/places"
              element={<ListPlaces setSingleMarker={setSingleMarkerCallback} />}
            />
            <Route
              path="/addSchedule"
              element={
                <AddSchedule setListMarkerCallback={setListMarkerCallback} />
              }
            />
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
          draggable={draggable}
          listLinePoint={listLinePoint}
        />
      </MapContainer>
    </>
  );
};

export default MainPage;
