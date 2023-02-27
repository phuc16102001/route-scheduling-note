import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleLeft } from "@fortawesome/free-regular-svg-icons";
import { useNavigate } from "react-router-dom";
import Title from "antd/es/typography/Title";

import "./index.css";

interface ListHeaderProps {
  headerText: string;
}

const ListHeader = (props: ListHeaderProps) => {
  const headerText = props.headerText;
  const navigate = useNavigate();

  return (
    <div className="listHeaderContainer">
      <FontAwesomeIcon
        onClick={() => navigate("/")}
        icon={faCircleLeft}
        className="backIcon"
      />
      <span className="listHeader">{headerText}</span>
    </div>
  );
};

export default ListHeader;
