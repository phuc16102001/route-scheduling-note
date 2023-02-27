import { Button, Divider, List, Skeleton } from "antd";
import { useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { useNavigate } from "react-router-dom";
import ListHeader from "../ListHeader";

interface Schedule {
  name: string;
  total_distance: number;
  duration: number;
}
const ListSchedules = () => {

  const [data, setData] = useState<Schedule[]>([]);
  const numberOfData = 0;

  const loadMoreData = () => {
    setData([
      ...data,
      {
        name: "Day 3",
        total_distance: 100,
        duration: 200,
      },
    ]);
  };

  return (
    <div
      id="scrollableDiv"
      style={{
        height: 400,
        overflow: "auto",
        padding: "0 16px",
        boxShadow: "2px 2px #888888",
        backgroundColor: "#fff",
        border: "1px solid rgba(140, 140, 140, 0.35)",
        borderRadius: "10px",
      }}
    >
      <InfiniteScroll
        dataLength={data.length}
        next={loadMoreData}
        hasMore={data.length < 15}
        endMessage={<Divider>End</Divider>}
        loader={<Skeleton paragraph={{ rows: 1 }} active />}
        scrollableTarget="scrollableDiv"
      >
        <List
          dataSource={data}
          header={<ListHeader headerText="List schedules" />}
          renderItem={(schedule) => (
            <List.Item>
              <div>
                {schedule.name} -{schedule.total_distance} - {schedule.duration}
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
