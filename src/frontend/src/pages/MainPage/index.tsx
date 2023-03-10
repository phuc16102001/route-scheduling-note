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
import { LatLngWithNote } from "react-app-env";

const MainPage = () => {
  const [listMarker, setListMarker] = useState<LatLng[] | LatLngWithNote[]>([]);
  const [listLinePoint, setListLinePoint] = useState<LatLng[]>([]);
  const [draggable, setDraggable] = useState<boolean>(true);

  const setSingleMarkerCallback = (
    marker: LatLng | LatLngWithNote,
    draggable: boolean = true
  ) => {
    const listMarker: LatLng[] | LatLngWithNote[] = [marker]
    setListMarker(listMarker);
    setDraggable(draggable);
  };

  const setListMarkerCallback = (markers: LatLng[] | LatLngWithNote[]) => {
    setListMarker(markers);
    setDraggable(false);
  };

  const setListLinePointCallback = (points: LatLng[]) => {
    setListLinePoint(points);
  };

  const resetMapCallback = () => {
    setListLinePoint([]);
    setListMarker([]);
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
                  resetMapCallback={resetMapCallback}
                />
              }
            />
            <Route
              path="/places"
              element={
                <ListPlaces
                  setSingleMarker={setSingleMarkerCallback}
                  resetMapCallback={resetMapCallback}
                />
              }
            />
            <Route
              path="/addSchedule"
              element={
                <AddSchedule
                  setListMarkerCallback={setListMarkerCallback}
                  resetMapCallback={resetMapCallback}
                />
              }
            />
            <Route
              path="/addPlace"
              element={
                <AddPlace
                  listMarker={listMarker}
                  setSingleMarkerCallback={setSingleMarkerCallback}
                  resetMapCallback={resetMapCallback}
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
