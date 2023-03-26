import { faSignOut } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button } from "antd";
import React, { useEffect, useState } from "react";
import { User } from "react-app-env";
import { useNavigate } from "react-router-dom";
import storageUtil from "utils/storage";
import "./index.css";

const MenuPage = () => {
  const navigate = useNavigate();
  const [username, setUserName] = useState<string>("");

  useEffect(() => {
    const user: User | null = storageUtil.loadUser();
    if (user) {
      setUserName(user.username);
    }
  }, []);

  const onClickLogout = () => {
    storageUtil.removeToken();
    storageUtil.removeUser();
    navigate("/login");
  };

  return (
    <div className="floatingPanel">
      <FontAwesomeIcon
        icon={faSignOut}
        className="logoutIcon"
        onClick={onClickLogout}
      />
      <h2 className="menuHeader">Menu</h2>
      <p>Hi, {username}! &#128513;</p>
      <p>How was you today?</p>
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
