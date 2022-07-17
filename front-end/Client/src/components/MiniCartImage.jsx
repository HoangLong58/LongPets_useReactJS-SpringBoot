import axios from 'axios';
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';

const Image = styled.img`
    min-width: 55px;
    width: 55px;
    height: 55px;
    margin: 12px;
    border: 1px solid #E8E8E8;
`
const MiniCartImage = ({ item }) => {
    const [hinhanh, setHinhAnh] = useState([]);
    useEffect(() => {
        const getHinhAnh = async () => {
            try {
                const hinhanhthucung = await axios.get(`http://localhost:8080/image/${item}`);
                setHinhAnh(hinhanhthucung.data[0].imageContent);
                console.log(hinhanhthucung.data[0].imageContent);
            } catch (err) {
                console.log("Lay hinh minicartimage that bai");
            }
        };
        getHinhAnh();
    }, []);
    // console.log(hinhanh.data)
    return (
        <Image src={hinhanh}></Image>
    )
}

export default MiniCartImage