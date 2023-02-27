import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleLeft } from "@fortawesome/free-regular-svg-icons";

import "./index.css";
import { faAdd } from "@fortawesome/free-solid-svg-icons";

interface ListHeaderProps {
  headerText: string;
  addFunction: () => void;
  backFunction: () => void;
}

const ListHeader = (props: ListHeaderProps) => {
  const headerText = props.headerText;
  const addFunction = props.addFunction;
  const backFunction = props.backFunction;

  return (
    <div className="listHeaderContainer">
      <FontAwesomeIcon
        onClick={backFunction}
        icon={faCircleLeft}
        className="icon"
      />
      <span className="listHeader">{headerText}</span>
      <FontAwesomeIcon onClick={addFunction} className="icon" icon={faAdd} />
    </div>
  );
};

export default ListHeader;
