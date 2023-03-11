import { faEdit } from "@fortawesome/free-regular-svg-icons";
import { faPlus, faTrash } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Divider, Input, List, message, Modal, Skeleton } from "antd";
import TextArea from "antd/es/input/TextArea";
import { LatLng } from "leaflet";
import { useEffect, useState } from "react";
import { Place, PlaceNote, Schedule } from "react-app-env";
import InfiniteScroll from "react-infinite-scroll-component";
import { useNavigate } from "react-router-dom";
import placeService from "services/place";
import scheduleService from "services/schedule";
import "./index.css";

interface AddScheduleProps {
  setListMarkerCallback: (markers: LatLng[]) => void;
  resetMapCallback: () => void;
}

const AddSchedule = (props: AddScheduleProps) => {
  const navigate = useNavigate();
  const maxPlaces = 5;
  const setListMarkerCallback = props.setListMarkerCallback;
  const resetMapCallback = props.resetMapCallback;

  const [scheduleName, setScheduleName] = useState<string>("");
  const [note, setNote] = useState<string>("");
  const [editingPlace, setEditingPlace] = useState<PlaceNote>();

  const [confirmScheduleOpen, setConfirmScheduleOpen] =
    useState<boolean>(false);
  const [addPlaceOpen, setAddPlaceOpen] = useState<boolean>(false);
  const [addNoteOpen, setAddNoteOpen] = useState<boolean>(false);
  const [confirmLoading, setConfirmLoading] = useState<boolean>(false);

  const [listPlace, setListPlace] = useState<Place[]>([]);
  const [numberOfPlace, setNumberOfPlace] = useState<number>(0);
  const [listSelectedPlaceNote, setListSelectedPlaceNote] = useState<
    PlaceNote[]
  >([]);

  const resetListMarker = (listMarker: PlaceNote[]) => {
    const markers: LatLng[] = listMarker.map(
      (element) =>
        new LatLng(
          element.place.coordinates!.lat,
          element.place.coordinates!.lng
        )
    );
    setListMarkerCallback(markers);
  };

  const onAddPlace = async (place: Place) => {
    try {
      const response = await placeService.getPlace(place);
      const fetchedPlace: Place = response.data;

      const newPlaceNote: PlaceNote = {
        place: fetchedPlace,
        note: "",
      };
      const newList = [...listSelectedPlaceNote, newPlaceNote];
      setListSelectedPlaceNote(newList);
      resetListMarker(newList);
    } catch (e) {
      console.log(e);
    } finally {
      setAddPlaceOpen(false);
    }
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
    const newList = listSelectedPlaceNote.filter((item) => item !== place);
    setListSelectedPlaceNote(newList);
    resetListMarker(newList);
  };

  const onAddNote = async () => {
    setListSelectedPlaceNote(
      listSelectedPlaceNote.map((element) => {
        if (element === editingPlace) {
          const newPlaceNote: PlaceNote = {
            place: editingPlace.place,
            note: note,
          };
          return newPlaceNote;
        } else {
          return element;
        }
      })
    );

    setAddNoteOpen(false);
    setNote("");
  };

  const onCancelNote = async () => {
    setAddNoteOpen(false);
    setNote("");
  };

  const onEditNote = async (placeNote: PlaceNote) => {
    setNote(placeNote.note);
    setEditingPlace(placeNote);
    setAddNoteOpen(true);
  };

  useEffect(() => {
    resetMapCallback();
    fetchInit();
  }, []);

  const onCancel = async () => {
    navigate("/schedules");
    resetMapCallback();
  };

  const onScheduleAdd = async () => {
    try {
      setConfirmLoading(true);
      const schedule: Schedule = {
        name: scheduleName,
        placeNotes: listSelectedPlaceNote,
      };
      await scheduleService.addSchedule(schedule);
      message.success("Add schedule successfully");
      navigate("/schedules");
    } catch (e) {
      console.log(e);
      message.error("Sorry, something was wrong!");
    } finally {
      setConfirmLoading(true);
    }
  };

  return (
    <div className="floatingPanel" style={{ maxHeight: "400px" }}>
      <Modal
        title="Add note"
        open={addNoteOpen}
        onCancel={onCancelNote}
        onOk={onAddNote}
      >
        <TextArea
          value={note}
          onChange={(e) => setNote(e.target.value)}
          size="large"
          placeholder="Note"
        />
      </Modal>

      <Modal
        title="Confirm add schedule"
        open={confirmScheduleOpen}
        onCancel={() => setConfirmScheduleOpen(false)}
        onOk={onScheduleAdd}
        confirmLoading={confirmLoading}
      >
        <Input
          value={scheduleName}
          onChange={(e) => setScheduleName(e.target.value)}
          placeholder="Schedule name"
        />
      </Modal>

      <Modal
        title="Add place"
        open={addPlaceOpen}
        onCancel={() => setAddPlaceOpen(false)}
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
                Note: {placeNote.note}{" "}
                <FontAwesomeIcon
                  className="editIcon"
                  onClick={() => onEditNote(placeNote)}
                  icon={faEdit}
                />
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
          onClick={() => setAddPlaceOpen(true)}
          style={{ textAlign: "center" }}
        >
          <FontAwesomeIcon icon={faPlus} /> Add place
        </div>
      ) : (
        <div style={{ textAlign: "center" }}>You can add maximum 5 places</div>
      )}
      <div className="btnAddGroup">
        <Button onClick={onCancel} className="btnAddScreen">
          Cancel
        </Button>
        <Button
          onClick={() => setConfirmScheduleOpen(true)}
          className="btnAddScreen"
          type="primary"
        >
          Schedule & Add
        </Button>
      </div>
    </div>
  );
};

export default AddSchedule;
