import { BrowserRouter, Route, Routes } from "react-router-dom";
import CustomMap from "components/CustomMap";
import ListPlaces from "components/ListPlaces";
import ListSchedules from "components/ListSchedules";
import MenuPage from "pages/MenuPage";
import PageNotFound from "pages/PageNotFound";
import AddPlace from "components/AddPlace";
import "./index.css";
import AddSchedule from "components/AddSchedule";

const MainPage = () => {
  return (
    <BrowserRouter>
      <div className="floatPanel">
        <Routes>
          <Route path="/" element={<MenuPage />}></Route>
          <Route path="/schedules" element={<ListSchedules />}></Route>
          <Route path="/places" element={<ListPlaces />}></Route>
          <Route path="/addSchedule" element={<AddSchedule />}></Route>
          <Route path="/addPlace" element={<AddPlace />}></Route>
        </Routes>
      </div>
      <Routes>
        <Route path="/" element={<CustomMap />}></Route>
        <Route path="/schedules" element={<CustomMap />}></Route>
        <Route path="/places" element={<CustomMap />}></Route>
        <Route path="/addPlace" element={<CustomMap />}></Route>
        <Route path="/addSchedule" element={<CustomMap />}></Route>
        <Route path="*" element={<PageNotFound />}></Route>
      </Routes>
    </BrowserRouter>
  );
};

export default MainPage;
