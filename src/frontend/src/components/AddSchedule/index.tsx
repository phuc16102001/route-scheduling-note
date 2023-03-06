import { faEdit } from "@fortawesome/free-regular-svg-icons";
import { faPlus, faTrash } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Divider, List, Modal, Skeleton } from "antd";
import { LatLng } from "leaflet";
import React, { useEffect, useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { useNavigate } from "react-router-dom";
import placeService from "services/place";
import "./index.css";

interface AddScheduleProps {
  setListMarkerCallback: (markers: LatLng[]) => void;
}

const AddSchedule = (props: AddScheduleProps) => {
  const navigate = useNavigate();
  const maxPlaces = 5;
  const setListMarkerCallback = props.setListMarkerCallback;

  const [addModelOpen, setAddModelOpen] = useState<boolean>(false);
  const [listPlace, setListPlace] = useState<Place[]>([]);
  const [numberOfPlace, setNumberOfPlace] = useState<number>(0);
  const [listSelectedPlaceNote, setListSelectedPlaceNote] = useState<
    PlaceNote[]
  >([]);

  const onAddPlace = async (place: Place) => {
    const newPlaceNote: PlaceNote = {
      place: place,
      note: "",
    };
    const newList = [...listSelectedPlaceNote, newPlaceNote];
    setListSelectedPlaceNote(newList);
    setListMarkerCallback(
      newList.map(
        (element) =>
          new LatLng(
            element.place.coordinates.lat,
            element.place.coordinates.lng
          )
      )
    );
    setAddModelOpen(false);
  };

  const fetchInit = async () => {
    try {
      const response = await placeService.getPlaces();
      const { data } = response;
      setListPlace(data);
      setNumberOfPlace(data.length);
    } catch (e) {
      console.log(e);
    }
  };

  const onDeletePlace = async (place: PlaceNote) => {
    setListSelectedPlaceNote(
      listSelectedPlaceNote.filter((item) => item !== place)
    );
  };

  useEffect(() => {
    fetchInit();
  }, []);

  return (
    <div className="floatingPanel" style={{ maxHeight: "400px" }}>
      <Modal
        title="Add place"
        open={addModelOpen}
        onCancel={() => setAddModelOpen(false)}
        footer={null}
      >
        <InfiniteScroll
          dataLength={listPlace.length}
          next={() => {}}
          hasMore={listPlace.length < numberOfPlace}
          endMessage={<Divider>End</Divider>}
          loader={<Skeleton paragraph={{ rows: 1 }} active />}
          scrollableTarget="scrollableDiv"
        >
          <List
            dataSource={listPlace}
            renderItem={(place: Place) => (
              <List.Item>
                <div onClick={() => onAddPlace(place)} className="placeItem">
                  <div>Name: {place.name}</div>
                  <div>Address: {place.address}</div>
                </div>
              </List.Item>
            )}
          ></List>
        </InfiniteScroll>
      </Modal>

      <h3>Add a new schedule</h3>
      <hr />
      <List
        dataSource={listSelectedPlaceNote}
        renderItem={(placeNote: PlaceNote) => (
          <List.Item>
            <div>
              <div>Name: {placeNote.place.name}</div>
              <div>Address: {placeNote.place.address}</div>
              <div>
                Note: {placeNote.note} <FontAwesomeIcon icon={faEdit} />
              </div>
            </div>
            <FontAwesomeIcon
              icon={faTrash}
              onClick={() => onDeletePlace(placeNote)}
              className="deleteIcon"
            />
          </List.Item>
        )}
      ></List>

      {listSelectedPlaceNote.length < maxPlaces ? (
        <div
          className="btnAddPlace"
          onClick={() => setAddModelOpen(true)}
          style={{ textAlign: "center" }}
        >
          <FontAwesomeIcon icon={faPlus} /> Add place
        </div>
      ) : (
        <div style={{ textAlign: "center" }}>You can add maximum 5 places</div>
      )}
      <div className="btnAddGroup">
        <Button onClick={() => navigate("/schedules")} className="btnAddScreen">
          Cancel
        </Button>
        <Button className="btnAddScreen" type="primary">
          Add
        </Button>
      </div>
    </div>
  );
};

export default AddSchedule;
