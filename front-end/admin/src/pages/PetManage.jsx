import React, { useState } from 'react';
import styled from 'styled-components';
import Aside from "../components/Dashboard/Aside";
import PetMain from '../components/PetManage/PetMain';
import PetRight from '../components/PetManage/PetRight';

const Container = styled.div`
    display: grid;
    width: 96%;
    margin: auto;
    gap: 1.8rem;
    grid-template-columns: 14rem auto 23rem;
`

const PetManage = () => {
    const [reRenderData, setReRenderData] = useState(true);   // State để Compo PetRight và PetMain thay đổi Effect
    return (
        <Container>
            <Aside active="petManage" />
            <PetMain reRenderData={reRenderData} setReRenderData={setReRenderData} />
            <PetRight reRenderData={reRenderData} setReRenderData={setReRenderData} />
        </Container>
    )
}

export default PetManage;