import { Button, Col, Form, Input, Row } from "antd";
import Card from "antd/es/card/Card";
import React from "react";
import { Link } from "react-router-dom";

const LoginPage = () => {
  return (
    <Row style={{ height: "100%" }} justify="space-around" align="middle">
      <Col span={5}>
        <Card
          title="Route note scheduling"
          type="inner"
          style={{ boxShadow: "2px 5px 5px 0px #828282" }}
        >
          <Row justify="space-around">
            <h3>Login form</h3>
          </Row>
          <Form>
            <Form.Item name="username" label="Username">
              <Input />
            </Form.Item>
            <Form.Item name="password" label="Password">
              <Input.Password />
            </Form.Item>
            <div style={{ margin: 5 }}>
              <Link to="/signup">Create account</Link>
            </div>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                Login
              </Button>
            </Form.Item>
          </Form>
        </Card>
      </Col>
    </Row>
  );
};

export default LoginPage;
