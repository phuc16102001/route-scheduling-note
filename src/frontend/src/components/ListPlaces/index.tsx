import { Divider, List, message, Skeleton } from "antd";
import { useState, useEffect } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import ListHeader from "components/ListHeader";
import { useNavigate } from "react-router-dom";
import placeService from "services/place";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { LatLng } from "leaflet";
import "./index.css";

interface ListPlaceInterface {
  setSingleMarker: (marker: LatLng, draggable: boolean) => void;
}

const ListPlaces = (props: ListPlaceInterface) => {
  const setSingleMarker = props.setSingleMarker;
  const [data, setData] = useState<Place[]>([]);
  const [numberOfData, setNumberOfData] = useState<number>(0);
  const navigate = useNavigate();

  const loadMoreData = () => {
    setData([]);
  };

  const fetchInit = async () => {
    try {
      const response = await placeService.getPlaces();
      const { data } = response;
      setData(data);
      setNumberOfData(data.length);
    } catch (e) {
      console.log(e);
    }
  };

  const onClickPlace = async (place: Place) => {
    const coordinates = place.coordinates;
    setSingleMarker(new LatLng(coordinates.lat, coordinates.lng), false);
  };

  const onDeletePlace = async (place: Place) => {
    try {
      await placeService.deletePlace(place);
      message.success("Your place has been removed!");
      fetchInit();
    } catch (e) {
      console.log(e);
      message.error("There was some problem!");
    }
  };

  useEffect(() => {
    fetchInit();
  }, []);

  return (
    <>
      <div
        id="scrollableDiv"
        className="floatingPanel"
        style={{
          height: 400,
          padding: "0 16px",
        }}
      >
        <InfiniteScroll
          dataLength={data.length}
          next={loadMoreData}
          hasMore={data.length < numberOfData}
          endMessage={<Divider>End</Divider>}
          loader={<Skeleton paragraph={{ rows: 1 }} active />}
          scrollableTarget="scrollableDiv"
        >
          <List
            dataSource={data}
            header={
              <ListHeader
                backFunction={() => navigate("/")}
                addFunction={() => navigate("/addPlace")}
                headerText="List places"
              />
            }
            renderItem={(place: Place) => (
              <List.Item>
                <div onClick={() => onClickPlace(place)} className="placeItem">
                  <div>Name: {place.name}</div>
                  <div>Address: {place.address}</div>
                </div>
                <FontAwesomeIcon
                  icon={faTrash}
                  onClick={() => onDeletePlace(place)}
                  className="deleteIcon"
                />
              </List.Item>
            )}
          ></List>
        </InfiniteScroll>
      </div>
    </>
  );
};

export default ListPlaces;
