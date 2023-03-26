import { Button, Card, Col, Form, Input, message, Row } from "antd";
import { isAxiosError } from "axios";
import ExitCode from "config/exitcode";
import React, { useEffect } from "react";
import { User } from "react-app-env";
import { useNavigate } from "react-router-dom";
import authService from "services/auth";
import storageUtil from "utils/storage";
import "./index.css";

const SignupPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (storageUtil.loadToken()) {
      navigate("/");
    }
  }, []);

  const onSubmitSignup = async (value: User) => {
    try {
      await authService.signup(value.username, value.password!);
      message.success("Registered successfully");
      navigate("/login");
    } catch (err) {
      console.log(err);
      if (isAxiosError(err) && err.response) {
        const { error } = err.response.data;
        if (error.code === ExitCode.EXIST_USERNAME) {
          message.info("Username existed");
          return;
        }
      }
      console.log(err);
      message.error("There was some problem!");
    }
  };

  return (
    <Row style={{ height: "100%" }} justify="space-around" align="middle">
      <Col xs={{ span: 18 }} sm={{ span: 14 }} lg={{ span: 8 }}>
        <Card title="Route note scheduling" type="inner" className="signupCard">
          <Row justify="space-around">
            <h3>Signup form</h3>
          </Row>
          <Form onFinish={onSubmitSignup}>
            <Form.Item
              rules={[{ required: true }]}
              name="username"
              label="Username"
            >
              <Input />
            </Form.Item>
            <Form.Item
              rules={[{ required: true }]}
              name="password"
              label="Password"
            >
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
