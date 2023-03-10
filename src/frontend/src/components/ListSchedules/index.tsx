import { Button, Divider, List, message, Skeleton } from "antd";
import { useEffect, useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { useNavigate } from "react-router-dom";
import ListHeader from "components/ListHeader";
import scheduleService from "services/schedule";
import converter from "utils/converter";
import "./index.css";
import { LatLng } from "leaflet";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";

interface ListPlaceInterface {
  setListMarkerCallback: (marker: LatLng[]) => void;
  setListLinePointCallback: (points: LatLng[]) => void;
  resetMapCallback: () => void;
}

const ListSchedules = (props: ListPlaceInterface) => {
  const setListMarkerCallback = props.setListMarkerCallback;
  const setListLinePointCallback = props.setListLinePointCallback;
  const resetMapCallback = props.resetMapCallback;

  const [data, setData] = useState<Schedule[]>([]);
  const [numberOfData, setNumberOfData] = useState<number>(0);
  const navigate = useNavigate();

  const loadMoreData = () => {
    setData([]);
  };

  const fetchSchedules = async () => {
    try {
      const response = await scheduleService.getSchedules();
      const schedules: Schedule[] = response.data;
      setData(schedules);
      setNumberOfData(schedules.length);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    resetMapCallback();
    fetchSchedules();
  }, []);

  const onClickSchedule = async (schedule: Schedule) => {
    try {
      const response = await scheduleService.getSchedule(schedule);
      const fetchedSchedule: Schedule = response.data;
      setListMarkerCallback(
        fetchedSchedule.placeNotes!.map(
          (element) =>
            new LatLng(
              element.place.coordinates!.lat,
              element.place.coordinates!.lng
            )
        )
      );
      setListLinePointCallback(
        fetchedSchedule.stops!.map(
          (element: Stop) =>
            new LatLng(element.coordinates.lat, element.coordinates.lng)
        )
      );
    } catch (e) {
      console.log(e);
    }
  };

  const onDeleteSchedule = async (schedule: Schedule) => {
    try {
      await scheduleService.deleteSchedule(schedule);
      await fetchSchedules();
      message.success("Boom, it has disappear!")
    } catch (e) {
      console.log(e);
      message.error("Oops! Something is not correct")
    }
  }

  return (
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
              addFunction={() => navigate("/addSchedule")}
              headerText="List schedules"
            />
          }
          renderItem={(schedule) => (
            <List.Item>
              <div>
                <div
                  onClick={() => onClickSchedule(schedule)}
                  className="scheduleItem"
                >
                  <div>Name: {schedule.name}</div>
                  <div>
                    Create at:{" "}
                    {converter
                      .instantToDateTime(schedule.createAt!)
                      .toDateString()}
                  </div>
                </div>
              </div>
              <FontAwesomeIcon
                icon={faTrash}
                onClick={() => onDeleteSchedule(schedule)}
                className="deleteIcon"
              />
            </List.Item>
          )}
        ></List>
      </InfiniteScroll>
    </div>
  );
};

export default ListSchedules;
