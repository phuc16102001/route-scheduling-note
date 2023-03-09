import { Button, Divider, List, Skeleton } from "antd";
import { useEffect, useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { useNavigate } from "react-router-dom";
import ListHeader from "components/ListHeader";
import scheduleService from "services/schedule";
import converter from "utils/converter";
import "./index.css";
import { LatLng } from "leaflet";

interface ListPlaceInterface {
  setListMarkerCallback: (marker: LatLng[]) => void;
  setListLinePointCallback: (points: LatLng[]) => void;
}

const ListSchedules = (props: ListPlaceInterface) => {
  const setListMarkerCallback = props.setListMarkerCallback;
  const setListLinePointCallback = props.setListLinePointCallback;

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
              <Button danger>Delete</Button>
            </List.Item>
          )}
        ></List>
      </InfiniteScroll>
    </div>
  );
};

export default ListSchedules;
