import { Button, Descriptions, Input, message } from "antd";
import Search from "antd/es/input/Search";
import { latLng, LatLng } from "leaflet";
import { useNavigate } from "react-router-dom";
import api from "utils/axios";
import "./index.css";

interface AddPlaceProps {
  setSingleMarker: (marker: LatLng) => void;
  listMarker?: LatLng[];
}

const AddPlace = (props: AddPlaceProps) => {
  const navigate = useNavigate();
  const setSingleMarker = props.setSingleMarker;
  const listMarker = props.listMarker;

  const searchPlace = async (value: string) => {
    try {
      const response = await api.get("/search", {
        params: {
          searchText: value,
        },
      });
      const { data } = response;
      setSingleMarker(latLng(data.lat, data.lng));
      await message.success(
        "We have found your place! You can drag to be more precise"
      );
    } catch (e) {
      console.log(e);
      message.error("Sorry we cannot find it.");
    }
  };

  const addPlace = async () => {
    try {
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div className="floatingPanel">
      <h3>Add a new place</h3>
      <Search
        placeholder="Enter name/address"
        allowClear
        enterButton
        size="middle"
        onSearch={searchPlace}
      />
      <Descriptions
        className="placeInfoSection"
        layout="horizontal"
        bordered
        column={1}
        size="middle"
      >
        <Descriptions.Item label="Latitude">
          {listMarker ? listMarker[0].lat.toFixed(3) : "Unselected"}
        </Descriptions.Item>
        <Descriptions.Item label="Longtitude">
          {listMarker ? listMarker[0].lng.toFixed(3) : "Unselected"}
        </Descriptions.Item>
      </Descriptions>
      <Input className="inputMemoName" placeholder="Memo name" />
      <div className="btnAddGroup">
        <Button onClick={() => navigate("/places")} className="btnAddScreen">
          Cancel
        </Button>
        <Button onClick={addPlace} className="btnAddScreen" type="primary">
          Add
        </Button>
      </div>
    </div>
  );
};

export default AddPlace;
