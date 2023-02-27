import { Button, Divider, List, Skeleton } from "antd";
import React, { useState, useEffect } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import api from "utils/axios";
import ListHeader from "components/ListHeader";

interface Schedule {
  name: string;
  total_distance: number;
  duration: number;
}

const ListPlaces = () => {
  const [data, setData] = useState<Schedule[]>([]);
  const numberOfData = 0;

  const loadMoreData = () => {
    setData([
    ]);
  };

  const fetchInit = async () => {
    try {
      const response = await api.get('places')
      const { data } = response
    } catch (e) {
      console.log(e)
    }
  };

  useEffect(() => {
    fetchInit();
  }, []);

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
        borderRadius: "5px",
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
          header={<ListHeader headerText="List places" />}
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

export default ListPlaces;
