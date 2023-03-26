import { Button, Card, Col, Form, Input, Row } from "antd";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./index.css";

const SignupPage = () => {
  const navigate = useNavigate();

  return (
    <Row style={{ height: "100%" }} justify="space-around" align="middle">
      <Col span={5}>
        <Card
          title="Route note scheduling"
          type="inner"
          style={{ boxShadow: "2px 5px 5px 0px #828282" }}
        >
          <Row justify="space-around">
            <h3>Signup form</h3>
          </Row>
          <Form>
            <Form.Item name="username" label="Username">
              <Input />
            </Form.Item>
            <Form.Item name="password" label="Password">
              <Input.Password />
            </Form.Item>
            <Row justify="space-around">
              <Button className="btn-signup" onClick={() => navigate("/login")}>
                Back
              </Button>
              <Form.Item>
                <Button className="btn-signup" type="primary" htmlType="submit">
                  Signup
                </Button>
              </Form.Item>
            </Row>
          </Form>
        </Card>
      </Col>
    </Row>
  );
};

export default SignupPage;
