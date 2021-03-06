import CloseOutlinedIcon from '@mui/icons-material/CloseOutlined';
import { PersonOutlineOutlined, PetsOutlined, GridViewOutlined, InventoryOutlined, AssignmentIndOutlined, LogoutOutlined, CategoryOutlined } from "@mui/icons-material";
import styled from "styled-components";
import { useState } from 'react';
import "../../css/main.css";
import { Link } from "react-router-dom";
import { logout } from '../../redux/callsAPI';
import { useDispatch, useSelector } from 'react-redux';

const Container = styled.aside`
    height: 100vh;
`;
const Top = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 1.4rem;
`;

const Logo = styled.div`
    display: flex;
    gap: 0.8rem;
`;

const Img = styled.img`
    width: 5rem;
    height: 5rem;
`;

const H2 = styled.h2`
    color: var(--color-primary);
    font-size: 1.4rem;
`;

const Close = styled.div`
    display: none;
`;

// SIDE BAR
const SideBar = styled.div`
    display: flex;
    flex-direction: column;
    height: 86vh;
    position: relative;
    top: 3rem;
`;

const IconSpan = styled.span`
    fontSize: 1.6rem;
    transition: all 300ms ease;

`

const LinkStyled = styled(Link)`
    display: flex;
    color: var(--color-info-dark);
    margin-left: 2rem;
    gap: 1rem;
    align-items: center;
    position: relative;
    height: 3.7rem;
    transition: all 300ms ease;
    &:last-child {
        position: absolute;
        bottom: 2rem;
        width: 100%;
    }
    &.active {
        background: var(--color-light);
        color: var(--color-primary);
        margin-left: 0;
        ${IconSpan} {
            color: var(--color-primary);
            margin-left: calc(1rem - 3px);
        }
        &:before {
            content: "";
            width: 6px;
            height: 100%;
            background: var(--color-primary);
        }
    }
    &:hover {
        color: var(--color-primary);
        cursor: pointer;
        ${IconSpan} {
            margin-left: 1rem;
        }
    }
`;

const H3 = styled.h3`
    font-size: 0.87rem;
`;

// const Count = styled.span`
//     background: var(--color-danger);
//     color: var(--color-white);
//     padding: 2px 10px;
//     font-size: 11px;
//     border-radius: var(--border-radius-1);
// `;

const Aside = (props) => {
    // console.log(props.active==="dashboard")
    // Dashboard
    const [isDashBoardActive, setDashBoardIsActive] = useState(props.active === "dashboard" ? true : false);
    const handleClickDashBoard = () => {
        setDashBoardIsActive(true);
        setCategoryIsActive(false);
        setPetIsActive(false);
        setCustomerIsActive(false);
        setOrderIsActive(false);
    }

    // Qu???n l?? Danh m???c
    const [isDanhMucActive, setCategoryIsActive] = useState(props.active === "categoryManage" ? true : false);
    const handleClickCategory = () => {
        setDashBoardIsActive(false);
        setCategoryIsActive(true);
        setPetIsActive(false);
        setCustomerIsActive(false);
        setOrderIsActive(false);
    }

    // Qu???n l?? Th?? c??ng
    const [isThuCungActive, setPetIsActive] = useState(props.active === "petManage" ? true : false);
    const handleClickPet = () => {
        setDashBoardIsActive(false);
        setCategoryIsActive(false);
        setPetIsActive(true);
        setCustomerIsActive(false);
        setOrderIsActive(false);
    }

    // Qu???n l?? Kh??ch h??ng
    const [isKhachHangActive, setCustomerIsActive] = useState(props.active === "customerManage" ? true : false);
    const handleClickCustomer = () => {
        setDashBoardIsActive(false);
        setCategoryIsActive(false);
        setPetIsActive(false);
        setCustomerIsActive(true);
        setOrderIsActive(false);
    }

    // Qu???n l?? ????n h??ng
    const [isDonHangActive, setOrderIsActive] = useState(props.active === "orderManage" ? true : false);
    const handleClickOrder = () => {
        setDashBoardIsActive(false);
        setCategoryIsActive(false);
        setPetIsActive(false);
        setCustomerIsActive(false);
        setOrderIsActive(true);
    }

    // ????ng xu???t
    const admin = useSelector((state) => state.admin.currentAdmin);
    const dispatch = useDispatch();
    const handleLogout = () => {
        logout(dispatch, admin);
    }
    return (
        <Container>
            <Top>
                <Logo>
                    <Img src="https://upload.wikimedia.org/wikipedia/vi/thumb/6/6c/Logo_Dai_hoc_Can_Tho.svg/2048px-Logo_Dai_hoc_Can_Tho.svg.png" />
                    <H2>LONGPETS <span style={{color: "var(--color-dark)"}}>- ADMIN</span></H2>
                </Logo>
                <Close>
                    <CloseOutlinedIcon></CloseOutlinedIcon>
                </Close>
            </Top>
            <SideBar>
                <LinkStyled to={"/"} className={isDashBoardActive ? "active" : null} onClick={handleClickDashBoard}>
                    <IconSpan>
                        <GridViewOutlined style={{ fontSize: "1.6rem", transition: "all 300ms ease" }} />
                    </IconSpan>
                    <H3>Dashboard</H3>
                </LinkStyled>

                 <LinkStyled to={"/category-manage"} className={isDanhMucActive ? "active" : null} onClick={handleClickCategory}>
                    <IconSpan>
                        <CategoryOutlined style={{ fontSize: "1.6rem", transition: "all 300ms ease" }} />
                    </IconSpan>
                    <H3>Qu???n l?? Danh m???c</H3>
                </LinkStyled>
                <LinkStyled to={"/pet-manage"} className={isThuCungActive ? "active" : null} onClick={handleClickPet}>
                    <IconSpan>
                        <PetsOutlined style={{ fontSize: "1.6rem", transition: "all 300ms ease" }} />
                    </IconSpan>
                    <H3>Qu???n l?? Th?? c??ng</H3>
                </LinkStyled>
                <LinkStyled to={"/customer-manage"} className={isKhachHangActive ? "active" : null} onClick={handleClickCustomer}>
                    <IconSpan>
                        <PersonOutlineOutlined style={{ fontSize: "1.6rem", transition: "all 300ms ease" }} />
                    </IconSpan>
                    <H3>Qu???n l?? Kh??ch h??ng</H3>
                </LinkStyled>
                <LinkStyled to={"/order-manage"} className={isDonHangActive ? "active" : null} onClick={handleClickOrder}>
                    <IconSpan>
                        <InventoryOutlined style={{ fontSize: "1.6rem", transition: "all 300ms ease" }} />
                    </IconSpan>
                    <H3>Qu???n l?? ????n h??ng</H3>
                </LinkStyled> 

                <LinkStyled to={"/"} onClick={() => handleLogout()}>
                    <IconSpan >
                        <LogoutOutlined style={{ fontSize: "1.6rem", transition: "all 300ms ease" }} />
                    </IconSpan>
                    <H3>????ng xu???t</H3>
                </LinkStyled>
            </SideBar>
        </Container>

    );
};

export default Aside;