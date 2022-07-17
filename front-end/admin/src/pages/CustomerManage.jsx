import React, { useState } from 'react';
import styled from 'styled-components';
import Aside from "../components/Dashboard/Aside";
import CustomerMain from '../components/CustomerManage/CustomerMain';
import CustomerRight from '../components/CustomerManage/CustomerRight';

const Container = styled.div`
    display: grid;
    width: 96%;
    margin: auto;
    gap: 1.8rem;
    grid-template-columns: 14rem auto 23rem;
`

const CustomerManage = () => {
    const [reRenderData, setReRenderData] = useState(true);   // State để Compo CustomerRight và CustomerMain thay đổi Effect
    return (
        <Container>
            <Aside active="customerManage" />
            <CustomerMain reRenderData={reRenderData} setReRenderData={setReRenderData} />
            <CustomerRight reRenderData={reRenderData} setReRenderData={setReRenderData} />
        </Container>
    )
}

export default CustomerManage;