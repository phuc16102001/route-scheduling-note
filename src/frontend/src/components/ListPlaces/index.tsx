import { Button, Divider, List, Modal, Skeleton } from "antd";
import React, { useState, useEffect } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import api from "utils/axios";
import ListHeader from "components/ListHeader";
import { useNavigate } from "react-router-dom";

interface Schedule {
  name: string;
  total_distance: number;
  duration: number;
}

const ListPlaces = () => {
  const [data, setData] = useState<Schedule[]>([]);
  const numberOfData = 0;
  const navigate = useNavigate();

  const loadMoreData = () => {
    setData([]);
  };

  const fetchInit = async () => {
    try {
      const response = await api.get("places");
      const { data } = response;
    } catch (e) {
      console.log(e);
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
                addFunction={() => navigate("/addPlace")}
                headerText="List places"
              />
            }
            renderItem={(schedule) => (
              <List.Item>
                <div>
                  {schedule.name} -{schedule.total_distance} -{" "}
                  {schedule.duration}
                </div>
                <Button danger>Delete</Button>
              </List.Item>
            )}
          ></List>
        </InfiniteScroll>
      </div>
    </>
  );
};

export default ListPlaces;
