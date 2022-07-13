import { LightMode, DarkMode, ShoppingCart, LocalMall, Person, Add } from "@mui/icons-material";
import { useEffect, useRef, useState } from 'react';
import styled from 'styled-components';
import RightTop from "./RightTop";
import axios from "axios";
import format_money from "../../utils";


const Container = styled.div`
    margin-top: 1.4rem;
`

const Info = styled.div`

`

const ProfilePhoto = styled.div`
    width: 2.8rem;
    height: 2.8rem;
    border-radius: 50%;
    overflow: hidden;
`

const Img = styled.img`
object-fit: cover;
width: 100%;
height: 100%;
`

const Small = styled.small`

`

// RECENT UPDATES
const RecentUpdates = styled.div`
    margin-top: 1rem;
`

const H2 = styled.h2`
    margin-bottom: 0.8rem;
`

const Updates = styled.div`
    background: var(--color-white);
    padding: var(--card-padding);
    border-radius: var(--card-border-radius);
    box-shadow: var(--box-shadow);
    transition: all 300ms ease;
    &:hover {
        box-shadow: none;
    }
`

const Update = styled.div`
    display: flex;
    grid-template-columns: 2.6rem auto;
    gap: 1rem;
    margin-bottom: 1rem;
`

const Message = styled.div`

`

// Sales Analytics
const SalesAnalytics = styled.div`
    margin-top: 2rem;
`

const Icon = styled.div`
    padding: 0.6rem;
    color: var(--color-white);
    border-radius: 50%;
    background: var(--color-primary);
    display: flex;
`

const Item = styled.div`
    background: var(--color-white);
    display: flex;
    align-items: center;
    gap: 1rem;
    margin-bottom: 0.7rem;
    padding: 1.4rem var(--card-padding);
    border-radius: var(--border-radius-3);
    box-shadow: var(--box-shadow);
    transition: all 300ms ease;
    &:hover {
        box-shadow: none;
    }
    &.offline ${Icon} {
        background: var(--color-danger);
        
    }
    &.customers ${Icon} {
        background: var(--color-success);
    }
    &.add-product {
        background-color: transparent;
        border: 2px dashed var(--color-primary);
        color: var(--color-primary);
        display: flex;
        align-items: center;
        justify-content: center;
        &:hover {
            background: var(--color-primary);
            color: white;
        }
        & div {
            display: flex;
            justify-items: center;
            gap: 0.6rem;
        }
    }
`


const ItemRight = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: start;
    margin: 0;
    width: 100%;
`

const Right = () => {
    // Các state cần thiết
    const [adminLog, setAdminLog] = useState();
    const [orderQuantity, setOrderQuantity] = useState("");
    const [totalToday, setTotalToday] = useState("");
    const [orderNeedAllow, setOrderNeedAllow] = useState("");
   
    useEffect(() => {
        const getLog = async () => {
            try {
                const logRes = await axios.get("http://localhost:8080/admin-log");
                console.log("logRes: ", logRes);
                setAdminLog(logRes.data);
            } catch (err) {
                console.log("Lỗi khi lấy adminlog");
            }
        }
        // SỐ ĐƠN HÀNG HÔM NAY
        const getOrderQuantityToday = async () => {
            try {
                const orderQuantityTodayRes = await axios.get("http://localhost:8080/order/get-order-today");
                console.log("orderQuantityTodayRes: ", orderQuantityTodayRes);
                setOrderQuantity(orderQuantityTodayRes.data.orderQuantityToday);
            } catch (err) {
                console.log("Lỗi khi lấy orderQuantityTodayRes");
            }
        }
        // SỐ ĐƠN HÀNG HÔM NAY
        const getTotalToday = async () => {
            try {
                const totalTodayRes = await axios.get("http://localhost:8080/order/get-money-total-today");
                console.log("totalTodayRes: ", totalTodayRes);
                setTotalToday(format_money((totalTodayRes.data.moneyTotalToday).toString()));
            } catch (err) {
                console.log("Lỗi khi lấy totalTodayRes");
            }
        }
        // SỐ ĐƠN HÀNG HÔM NAY
        const getOrderNeedAllow = async () => {
            try {
                const orderNeedAllow = await axios.get("http://localhost:8080/order/get-money-total-today");
                console.log("orderNeedAllow: ", orderNeedAllow);
                setOrderNeedAllow(orderNeedAllow.data.orderQuantityNeedAllow);
            } catch (err) {
                console.log("Lỗi khi lấy orderNeedAllow");
            }
        }
        getLog();
        getOrderQuantityToday();
        getTotalToday();
        getOrderNeedAllow();
        return () => {
            setOrderQuantity("");

        }
    }, [])
    return (
        <Container>
            <RightTop />
            {/* END OF TOP */}

            <RecentUpdates>
                <H2>Recent Updates</H2>
                <Updates>
                    {   
                        adminLog
                        ?
                        adminLog.map((log, key) => {
                            return (
                                <Update>
                                    <ProfilePhoto>
                                        <Img src={log.logAvatar} />
                                    </ProfilePhoto>
                                    <Message>
                                        {/* <p><b>Monkey D Luffy</b> received his order of Hoàng Long tech GPS drone</p> */}
                                        <p>{log.logContent}</p>
                                        <Small class="text-muted">2 Minutes Ago</Small>
                                    </Message>
                                </Update>
                            );
                        })
                        : null
                    }
                </Updates>
            </RecentUpdates>
            {/* END OF RECENT UPDATES */}

            <SalesAnalytics>
                <H2>Sales Analytics</H2>
                <Item className="online">
                    <Icon>
                        <ShoppingCart />
                    </Icon>
                    <ItemRight>
                        <Info>
                            <h3>TODAY ORDERS</h3>
                            <small class="text-muted">Last 24 Hours</small>
                        </Info>
                        <h5 className="success">+39%</h5>
                        <h3>{orderQuantity === "" ? "Chưa có" : orderQuantity}</h3>
                    </ItemRight>
                </Item>
                <Item className="offline">
                    <Icon>
                        <LocalMall />
                    </Icon>
                    <ItemRight>
                        <Info>
                            <h3>TODAY SALES</h3>
                            <small class="text-muted">Last 24 Hours</small>
                        </Info>
                        <h5 className="danger">-17%</h5>
                        <h3>{totalToday === "" ? "Chưa có" : totalToday} <span style={{ textDecoration: "underline" }}><b>đ</b></span></h3>
                    </ItemRight>
                </Item>
                <Item className="customers">
                    <Icon>
                        <Person />
                    </Icon>
                    <ItemRight>
                        <Info>
                            <h3>ĐƠN CẦN DUYỆT</h3>
                            <small class="text-muted">Last 24 Hours</small>
                        </Info>
                        <h5 className="success">+25%</h5>
                        <h3>{ orderNeedAllow === "" ? "Chưa có" : orderNeedAllow }</h3>
                    </ItemRight>
                </Item>
                <Item className="add-product">
                    <Add />
                    <h3>Thêm thú cưng</h3>
                </Item>
            </SalesAnalytics>

        </Container>
    );
};

export default Right;