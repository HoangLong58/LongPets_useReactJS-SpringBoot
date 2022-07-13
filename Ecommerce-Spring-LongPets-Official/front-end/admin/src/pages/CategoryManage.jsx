import React, { useState } from 'react';
import styled from 'styled-components';
import Aside from "../components/Dashboard/Aside";
import CategoryMain from '../components/CategoryManage/CategoryMain';
import CategoryRight from '../components/CategoryManage/CategoryRight';

const Container = styled.div`
    display: grid;
    width: 96%;
    margin: auto;
    gap: 1.8rem;
    grid-template-columns: 14rem auto 23rem;
`

const CategoryManage = () => {
    const [reRenderData, setReRenderData] = useState(true);   // State để Compo CategoryRight và CategoryMain thay đổi Effect
    return (
        <Container>
            <Aside active="categoryManage" />
            <CategoryMain reRenderData={reRenderData} setReRenderData={setReRenderData} />
            <CategoryRight reRenderData={reRenderData} setReRenderData={setReRenderData} />
        </Container>
    )
}

export default CategoryManage;