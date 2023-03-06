import { Button, Descriptions, Input, message } from "antd";
import Search from "antd/es/input/Search";
import { latLng, LatLng } from "leaflet";
import { ChangeEvent, useState } from "react";
import { useNavigate } from "react-router-dom";
import mapService from "services/map";
import placeService from "services/place";
import "./index.css";

interface AddPlaceProps {
  setSingleMarkerCallback: (marker: LatLng) => void;
  listMarker?: LatLng[];
}

const AddPlace = (props: AddPlaceProps) => {
  const navigate = useNavigate();
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [searching, setSearching] = useState<boolean>(false);
  const setSingleMarker = props.setSingleMarkerCallback;
  const listMarker = props.listMarker;

  const searchPlace = async (value: string) => {
    try {
      setSearching(true);
      const response = await mapService.searchGeoText(value);
      const { data } = response;
      setSingleMarker(latLng(data.lat, data.lng));
      message.success("We have found your place!");
    } catch (e) {
      console.log(e);
      message.error("Sorry we cannot find it.");
    } finally {
      setSearching(false);
    }
  };

  const addPlace = async () => {
    try {
      if (!listMarker) {
        message.info("Please search for your place first.");
        return;
      }

      if (name.length < 1) {
        message.info("Please give your place a beautiful name.");
        return;
      }

      const place: Place = {
        name: name,
        address: address,
        coordinates: {
          lat: listMarker[0].lat,
          lng: listMarker[0].lng,
        },
      };
      await placeService.addPlace(place);
      message.success("Your place is added.");
      navigate("/places")
    } catch (e) {
      console.log(e);
      message.error("There was some error occured.");
    }
  };

  const onNameChange = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setName(event.target.value);
  };

  const onAddressChange = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setAddress(event.target.value);
  };

  return (
    <div className="floatingPanel">
      <h3>Add a new place</h3>
      <Search
        placeholder="Enter name/address"
        allowClear
        enterButton
        value={address}
        onChange={onAddressChange}
        size="middle"
        loading={searching}
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
      <Input
        value={name}
        onChange={onNameChange}
        className="inputMemoName"
        placeholder="Memo name"
      />
      <div className="btnAddGroup">
        <Button onClick={() => navigate("/places")} className="btnAddScreen">
          Cancel
        </Button>
        <Button
          onClick={() => addPlace()}
          className="btnAddScreen"
          type="primary"
        >
          Add
        </Button>
      </div>
    </div>
  );
};

export default AddPlace;
