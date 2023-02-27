import { Button, Descriptions } from "antd";
import Search from "antd/es/input/Search";
import React from "react";
import { useNavigate } from "react-router-dom";
import api from "utils/axios";
import "./index.css";

const AddPlace = () => {
  const navigate = useNavigate();

  const searchPlace = async (value: string) => {
    try {
      const response = await api.get("/search", {
        params: {
          searchText: value,
        },
      });
      const { data } = response;
      console.log(data)
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div className="floatingPanel">
      <h3>Add a new place</h3>
      <Search
        placeholder="Enter name/address"
        allowClear
        enterButton
        size="middle"
        onSearch={searchPlace}
      />
      <Descriptions
        className="placeInfoSection"
        layout="horizontal"
        bordered
        column={1}
        size="middle"
      >
        <Descriptions.Item label="Latitude">12.4</Descriptions.Item>
        <Descriptions.Item label="Longtitude">23.1</Descriptions.Item>
      </Descriptions>
      <div className="btnAddGroup">
        <Button onClick={() => navigate("/places")} className="btnAddScreen">
          Cancel
        </Button>
        <Button className="btnAddScreen" type="primary">
          Add
        </Button>
      </div>
    </div>
  );
};

export default AddPlace;
