import { Button, Divider, List, Skeleton } from "antd";
import { useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { useNavigate } from "react-router-dom";
import ListHeader from "components/ListHeader";

interface Schedule {
  name: string;
  total_distance: number;
  duration: number;
}
const ListSchedules = () => {
  const [data, setData] = useState<Schedule[]>([]);
  const numberOfData = 0;
  const navigate = useNavigate();

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
      className="floatingPanel"
      style={{
        height: 400,
        overflow: "auto",
        padding: "0 16px",
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
          header={
            <ListHeader
              backFunction={() => navigate("/")}
              addFunction={() => {}}
              headerText="List schedules"
            />
          }
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
