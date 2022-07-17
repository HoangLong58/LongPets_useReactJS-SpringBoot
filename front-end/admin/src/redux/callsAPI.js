import axios from "axios"
import { loginFailure, loginStart, loginSuccess, logoutAdmin } from "./adminRedux";
import qs from 'qs';


// Hàm đăng nhập
export const login = async (dispatch, admin) => {
    dispatch(loginStart());
    try {
        console.log("ADMIN:", admin);
        // TEST:
        const data = { 'employeeEmail': admin.employeeEmail,
        'employeePassword': admin.employeePassword};
        const options = {
          method: 'POST',
          headers: { 'content-type': 'application/x-www-form-urlencoded' },
          data: qs.stringify(data),
          url: "http://localhost:8080/admin/login-admin"
        };
        axios(options)
        .then((res) => {
            if(res.data.employeeEmail == null) {
                dispatch(loginFailure());
            }
            axios.get(`http://localhost:8080/employee/get-employee-by-email/${res.data.employeeEmail}`).then((employeeRes) => {
                console.log("RES: ", res, "employeeRes: ",employeeRes);
            
                dispatch(loginSuccess(employeeRes.data));
                localStorage.setItem("jwtTokenAdmin", res.data.accessTokenAdmin);
                localStorage.setItem("jwtTokenRefreshAdmin", res.data.refreshTokenAdmin);
            })
        }).catch((err) => {
            dispatch(loginFailure());
        });
    }catch(err) {
        dispatch(loginFailure());
    }
}

// Hàm đăng xuất
export const logout = async (dispatch, admin) => {
    // admin gồm: đối tượng admin ở adminRedux
    console.log("Admin: ",admin);
    dispatch(logoutAdmin()); //Khởi tạo lại giỏ hàng
    localStorage.removeItem("jwtTokenAdmin");
    localStorage.removeItem("jwtTokenRefreshAdmin");
}