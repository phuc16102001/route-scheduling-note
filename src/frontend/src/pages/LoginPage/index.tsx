import { Button, Col, Form, Input, Row } from "antd";
import Card from "antd/es/card/Card";
import { useEffect } from "react";
import { User } from "react-app-env";
import { Link, useNavigate } from "react-router-dom";
import authService from "services/auth";
import storageUtil from "utils/storage";
import "./index.css";

const LoginPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (storageUtil.loadToken()) {
      navigate("/");
    }
  }, []);

  const onSubmitLogin = async (value: User) => {
    try {
      const response = await authService.login(value.username, value.password!);
      const { data } = response;
      storageUtil.saveToken(data.token);
      storageUtil.saveUser(data.user);
      navigate("/");
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <Row style={{ height: "100%" }} justify="space-around" align="middle">
      <Col xs={{ span: 18 }} sm={{ span: 14 }} lg={{ span: 8 }}>
        <Card title="Route note scheduling" type="inner" className="loginCard">
          <Row justify="space-around">
            <h3>Login form</h3>
          </Row>
          <Form onFinish={onSubmitLogin}>
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
