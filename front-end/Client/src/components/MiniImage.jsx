import axios from 'axios';
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';

const Image = styled.img`
    width: 200px;
    height: 200px;
    object-fit: cover;
`
const MiniImage = ({ item }) => {
    const [image, setImage] = useState([]);
    useEffect(() => {
        const getImage = async () => {
            try {
                const petImage = await axios.get(`http://localhost:8080/image/${item}`);
                setImage(petImage.data[0].imageContent);
                console.log(petImage.data[0].imageContent);
            } catch(err) {
                console.log("Lay miniimage that bai");
            }
        };
        getImage();
    }, []);
    // console.log(image.data)
    return (
        <Image src={image}></Image>
    )
}

export default MiniImage