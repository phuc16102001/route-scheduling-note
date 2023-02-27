import { Button } from "antd";
import React from "react";
import { useNavigate } from "react-router-dom";
import "./index.css";

const MenuPage = () => {
  const navigate = useNavigate();

  return (
    <div className="floatingPanel">
      <h2 className="menuHeader">Menu</h2>
      <div className="menuBtnGroup">
        <Button
          onClick={() => navigate("/places")}
          type="primary"
          className="menuBtn"
          size="large"
        >
          Places
        </Button>
        <Button
          onClick={() => navigate("/schedules")}
          type="primary"
          className="menuBtn"
          size="large"
        >
          Schedules
        </Button>
      </div>
    </div>
  );
};

export default MenuPage;
