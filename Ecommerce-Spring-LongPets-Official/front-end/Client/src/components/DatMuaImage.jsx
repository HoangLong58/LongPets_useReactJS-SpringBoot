import axios from 'axios';
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';

const Image = styled.img`
    width: 100%;
`
const DatMuaImage = ({ item }) => {
    const [image, setImage] = useState([]);
    useEffect(() => {
        const getImage = async () => {
            try {
                const petImage = await axios.get(`http://localhost:8080/image/${item}`);
                setImage(petImage.data[0].imageContent);
                console.log(petImage.data[0].imageContent);
            } catch(err) {
                console.log("Lay datmuaimage that bai");
            }
        };
        getImage();
    }, []);
    return (
        <Image src={image}></Image>
    )
}

export default DatMuaImage