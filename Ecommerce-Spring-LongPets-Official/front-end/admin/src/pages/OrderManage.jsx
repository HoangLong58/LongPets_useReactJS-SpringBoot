import React, { useState } from 'react';
import styled from 'styled-components';
import Aside from "../components/Dashboard/Aside";
import OrderMain from "../components/OrderManage/OrderMain";
import OrderRight from "../components/OrderManage/OrderRight";

const Container = styled.div`
    display: grid;
    width: 96%;
    margin: auto;
    gap: 1.8rem;
    grid-template-columns: 14rem auto 23rem;
`

const OrderManage = () => {
    const [reRenderData, setReRenderData] = useState(true);   // State để Compo KhachHangRight và KhachHangMain thay đổi Effect
    return (
        <Container>
            <Aside active="orderManage" />
            <OrderMain reRenderData={reRenderData} setReRenderData={setReRenderData} />
            <OrderRight reRenderData={reRenderData} setReRenderData={setReRenderData} />
        </Container>
    )
}

export default OrderManage;