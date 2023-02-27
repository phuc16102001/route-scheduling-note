import Button from "antd/es/button";
import Result from "antd/es/result";
import React from "react";

const PageNotFound = () => {
  return (
    <Result
      status="404"
      title="404"
      style={{ height: "90vh" }}
      subTitle="Sorry, the page you visited does not exist."
      extra={
        <Button type="primary" href="/">
          Back Home
        </Button>
      }
    />
  );
};

export default PageNotFound;
