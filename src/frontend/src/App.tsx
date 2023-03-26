import "./App.css";
import MainPage from "./pages/MainPage";
import Footer from "./components/Footer";
import LoginPage from "pages/LoginPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import SignupPage from "pages/SignupPage";

function App() {
  return (
    <>
      <div className="container">
        <BrowserRouter>
          <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/signup" element={<SignupPage />} />
            <Route path="/*" element={<MainPage />} />
          </Routes>
        </BrowserRouter>
      </div>
      <Footer></Footer>
    </>
  );
}

export default App;
