import { Button, List } from "antd";

const dummy_data = [
  {
    name: "Day 1",
    total_distance: 100,
    duration: 200,
  },
  {
    name: "Day 2",
    total_distance: 100,
    duration: 200,
  },
  {
    name: "Day 3",
    total_distance: 100,
    duration: 200,
  },
];

const ListSchedules = () => {
  return (
    <List
      itemLayout="horizontal"
      size="large"
      bordered
      style={{ height: "100%", margin: "5px" }}
      dataSource={dummy_data}
      header={<h2 style={{ textAlign: "center" }}>List of schedules</h2>}
      renderItem={(schedule) => (
        <List.Item>
          <div>
            {schedule.name}
            {schedule.total_distance}
            {schedule.duration}
          </div>
          <Button>Delete</Button>
        </List.Item>
      )}
    ></List>
  );
};

export default ListSchedules;
