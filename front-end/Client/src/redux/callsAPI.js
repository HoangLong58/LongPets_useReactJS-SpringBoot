import axios from "axios"
import { loginFailure, loginStart, loginSuccess, logoutUser } from "./userRedux"
import { logoutCart } from "./cartRedux";
import { Navigate } from "react-router-dom";
import qs from 'qs';


// Hàm đăng ký
export const register = async (dispatch, user) => {
    // user gồm {tenguoimuadangky, emailnguoimuadangky, matkhaudangky}
    try {
        const res = await axios.post("http://localhost:8080/customer/register", user);
        console.log("Thực hiện register, gửi đến api: ", res);
        //Đăng ký thành công thì tự đăng nhập vào
        login(dispatch,{customerEmail: user.customerEmail, customerPassword: user.customerPassword});
    } catch(err) {
        console.log("Lỗi đăng ký ",err);
        user.setWrong(true);
    }
}

// Hàm đăng nhập
export const login = async (dispatch, user) => {
    // user gồm username, mật khẩu
    dispatch(loginStart());
    try {
        console.log("USER:", user);
        // TEST:
        const data = { 'customerEmail': user.customerEmail,
        'customerPassword': user.customerPassword};
        const options = {
          method: 'POST',
          headers: { 'content-type': 'application/x-www-form-urlencoded' },
          data: qs.stringify(data),
          url: "http://localhost:8080/login-customer"
        };
        axios(options)
        .then((res) => {
            if(res.data.customerEmail == null) {
                dispatch(loginFailure());
            }
            axios.get(`http://localhost:8080/customer/find-customer-by-emailCustomer/${res.data.customerEmail}`).then((customerRes) => {
                console.log("RES: ", res, "customerRes: ",customerRes);
            
                dispatch(loginSuccess(customerRes.data));
                localStorage.setItem("jwtToken", res.data.accessToken);
                localStorage.setItem("jwtTokenRefresh", res.data.refreshToken);
            })
        }).catch((err) => {
            dispatch(loginFailure());
        });

        




        // const res = await axios.post("http://localhost:8080/login-customer", user, {withCredentials: true});

        // dispatch(loginSuccess(res.data));

        // // Logout sau 1 khoảng time trước khi Cookie hết hạn (900000000mili giây)
        // const resNguoiMua = await axios.get("http://localhost:3001/api/user/findByEmailNguoiMua/" + user.emailnguoimua);
        // const nguoimua = resNguoiMua.data[0];
        // setTimeout(() => {
        //     console.log("ccc123;");
        //     logout(dispatch, nguoimua);
        // }, 800000000)
    }catch(err) {
        dispatch(loginFailure());
    }
}

// Hàm đăng xuất
export const logout = async (dispatch, user) => {
    // User gồm: đối tượng user ở UserRedux
    console.log("USER: ",user);
    // const res = await axios.post("http://localhost:3001/api/auth/logout", {manguoimua: user.manguoimua}, {withCredentials: true});
    dispatch(logoutCart()); //Khởi tạo lại người dùng
    dispatch(logoutUser()); //Khởi tạo lại giỏ hàng
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("jwtTokenRefresh");
}