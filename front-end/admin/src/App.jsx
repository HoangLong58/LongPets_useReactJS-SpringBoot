import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import CategoryManage from "./pages/CategoryManage";
import PetManage from "./pages/PetManage";
import OrderManage from "./pages/OrderManage";
import CustomerManage from "./pages/CustomerManage";
import { useState } from "react";
import LoginAdmin from "./pages/LoginAdmin";
import { useSelector } from "react-redux";


const App = () => {
    // Lấy admin từ Redux
    const admin = useSelector((state) => state.admin.currentAdmin);
    const [isLogin, setIsLogin] = useState(false);
    return (
        <Router>
            <Routes>
                <Route path='/' element={admin ? <Home /> : <LoginAdmin />} />

                <Route path='/category-manage' element={<CategoryManage />} />
                <Route path='/pet-manage' element={<PetManage />} />
                <Route path='/customer-manage' element={<CustomerManage />} />
                <Route path='/order-manage' element={<OrderManage />} />
            </Routes>
        </Router>
    );
};

export default App;