import axios from "axios";
import { useEffect, useState } from "react";
import styled from "styled-components";
import { categories } from "../data";
import { mobile } from "../responsive";
import CategoryItem from "./CategoryItem";


const Container = styled.div`
    background-color: #ebe2aa;
    display: flex;
    padding: 20px;
    justify-content: space-between;
    ${mobile({ padding: "0px", flexDirection: "column" })}
`

const Categories = () => {
    useEffect(() => {
        const getCategory = async () => {
            try {
                const res = await axios.get("http://localhost:8080/category")
                console.log("CATEGORY:" , res);
                setCategory(res.data);

            } catch (err) {
                console.log("Loi gi roi");
            };
        };
        getCategory();
    },[]);
    
    const [category, setCategory] = useState([]);
    return (
        <Container>
            {category.map(item => (
                <CategoryItem item={item} key={item.categoryId} />
            ))}
        </Container>
    )
}

export default Categories