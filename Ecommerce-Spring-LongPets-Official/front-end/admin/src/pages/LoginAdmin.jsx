import "../css/main.css";
import { CloseOutlined } from '@mui/icons-material';
import styled from 'styled-components';
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { login, register } from "../redux/callsAPI";

const SignIn = styled.div`
    width: 95%;
    height: 95%;
    box-shadow: 6px 6px 30px #d1d9e6;
    border-radius: 20px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    background-color: #f8f8f8;
    box-sizing: border-box;
    overflow: hidden;
    z-index: 1;
`

const MainPage = styled.div`
    width: 100%;
    height: 100%;
    background-image: url("https://i.pinimg.com/originals/6c/63/82/6c638291a66ddc93b86bf4f43c337701.jpg");
    background-position: center;
    background-size: cover;
    background-repeat: no-repeat; 
    position: relative;

    height: 100%;
    border-radius: 0px;
    /* height: 55%;
    border-radius: 0% 0% 50% 50%/ 0% 0% 30% 30%; */
    box-shadow: 2px 6px 12px #d1d9e6;

    ${SignIn}.active-sign-in & {
        height: 55%;
        border-radius: 0% 0% 50% 50%/0% 0% 20% 20%;
        animation: main 0.3s linear;
        }
    }
    ${SignIn}.active-sign-up & {
        height: 55%;
        border-radius: 0% 0% 50% 50%/0% 0% 20% 20%;
        animation: main 0.3s linear;
        }
    }
`

const TopBar = styled.div`
    width: 220px;
    height: 20px;
    background-color: #ffffff;
    position: absolute;
    left: 50%;
    top: 0px;
    transform: translateX(-50%);
    border-radius: 0px 0px 20px 20px;
`

const Title = styled.div`
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
`

const H1 = styled.h1`
    font-size: 3rem;
    color: var(--color-white);
    margin: 0px;
    letter-spacing: 2px;
`

const P = styled.p`
    color: var(--color-white);
    font-size: 1.2rem;
`

const FormChucNang = styled.div`
    display: flex;
    flex-direction: column;
    position: absolute;
    left: 50%;
    bottom: 60px;
    transform: translateX(-50%);
    text-align: center;
    ${SignIn}.active-sign-in & {
        display: none;
        }
    }
    ${SignIn}.active-sign-up & {
        display: none;
        }
    }
`

const SignInBtn = styled.button`
    width: 230px;
    height: 42px;
    margin: 5px 0px;
    border: none;
    outline: none;
    border-radius: 20px;
    text-transform: uppercase;
    font-size: 0.9rem;
    letter-spacing: 1px;
    font-weight: 700;
    box-shadow: 2px 2px 30px rgba(0, 0, 0, 0.5);
    cursor: pointer;
    color: #171717;
    background-color: #ffffff;

    &:active {
        transform: scale(1.05);
    }
`

const Cancel = styled.div`
    position: absolute;
    left: 50%;
    bottom: -25px;
    transform: translateX(-50%);
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background-color: #f8f8f8;
    justify-content: center;
    align-items: center;
    font-size: 1.2rem;
    box-shadow: 2px 6px 20px rgba(0, 0, 0, 0.12);
    display: none;
    ${SignIn}.active-sign-in & {
        display: flex;
    }
    ${SignIn}.active-sign-up & {
        display: flex;
    }
`

const Icon = styled.a`
    color: #111111;
    animation: cancel 0.5s;
`

const SignInPage = styled.div`
    display: none;
    width: 100%;
    height: 45%;
    ${SignIn}.active-sign-in & {
        display: flex;
}
`

const Form = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
`

const Input = styled.input`
    width: 220px;
    height: 40px;
    margin: 5px 0px;
    border: 1px solid rgba(0, 0, 0, 0.05);
    outline: none;
    color: #191919;
    border-radius: 10px;
    padding: 0px 10px;
    box-sizing: border-box;
    &::placeholder {
        letter-spacing: 2px;

        font-size: 15px;
    }
    &:focus {
        box-shadow: 2px 2px 30px rgba(0, 0, 0, 0.1);
    }
`

const Button = styled.button`
    width: 220px;
    height: 40px;
    margin: 10px 0px;
    border: none;
    outline: none;
    border-radius: 20px;
    background-color: #ffffff;
    box-shadow: 12px 12px 30px rgba(0, 0, 0, 0.1);
    &:active {
        transform: scale(1.05);
    }
    &:hover {
        background-color: var(--color-primary);
        color: #ffffff;
        transition: all ease 0.3s;
    }
`

const SignUpPage = styled.div`
    display: none;
    width: 100%;
    height: 45%;
    ${SignIn}.active-sign-up & {
        display: flex;
    }
    `

const BgCurcule = styled.div`
    width: 500px;
    height: 500px;
    position: absolute;
    top: -40%;
    height: 60%;
    transform: translateX(-60%);
    background-color: #ecf0f3;
    border-radius: 50%;
`
const Error = styled.span`
    color: red;
`

const LoginAdmin = () => {
    // Redux
    const { isFetching, error } = useSelector((state) => state.admin);
    // Giao diện
    const [isSignIn, setIsSignIn] = useState(false);
    const handleClose = () => {
        setIsSignIn(false);
    }
    const handleLogin = () => {
        setIsSignIn(true);
    }

    // Xử lý
    const dispatch = useDispatch();
    const [employeeEmail, setEmployeeEmail] = useState();
    const [employeePassword, setEmployeePassword] = useState();
    console.log("email & mật khẩu nhân viên: ", employeeEmail, employeePassword);

    // Gọi hàm đăng nhập - callAPI.js-redux
    const handleClickLogin = (e) => {
        e.preventDefault();
        login(dispatch, { employeeEmail: employeeEmail, employeePassword: employeePassword });
    }

    return (
        <SignIn className={isSignIn ? "active-sign-in" : null}>
            <MainPage>
                <TopBar />
                {/* Tiêu đề */}
                <Title>
                    <H1>Long Pets - ADMIN</H1>
                    <P>Chào mừng bạn đến với trang web bán thú cưng hàng đầu Việt Nam</P>
                </Title>
                {/* Các nút chức năng */}
                <FormChucNang>
                    <SignInBtn onClick={handleLogin}>Đăng nhập</SignInBtn>
                </FormChucNang>
                {/* Nút đóng */}
                <Cancel onClick={handleClose}>
                    <Icon><CloseOutlined /></Icon>
                </Cancel>
            </MainPage>
            {/* Trang đăng nhập */}
            <SignInPage>
                <Form>
                    {/* Email */}
                    <Input type="email" placeholder="Email của bạn"
                        onChange={(e) => setEmployeeEmail(e.currentTarget.value)}
                    />
                    {/* Mật khẩu */}
                    <Input type="password" placeholder="Mật khẩu của bạn"
                        onChange={(e) => setEmployeePassword(e.currentTarget.value)}
                    />
                    {/* Nút đăng nhập */}
                    <Button
                        onClick={(e) => handleClickLogin(e)}
                        disabled={isFetching}
                    >Đăng nhập</Button>
                    {error && <Error>Something went wrong...</Error>}
                </Form>
            </SignInPage>
            {/* Background */}
            {/* <BgCurcule></BgCurcule> */}
        </SignIn>
    );
};

export default LoginAdmin;