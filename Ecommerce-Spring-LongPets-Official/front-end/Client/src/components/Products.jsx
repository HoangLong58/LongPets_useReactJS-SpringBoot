import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { popularProducts } from "../data";
import Product from "./Product";
import axios from "axios";
import ReactPaginate from "react-paginate";
import "../css/main.css";


const Container = styled.div`
    padding: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    
`

const Products = ({ categoryId, filters, sort }) => {
    const [products, setProducts] = useState([]);
    const [Filteredproducts, setFilteredProducts] = useState([]);
    useEffect(() => {
        const getProducts = async () => {
            try {
                const res = await axios.get(
                    categoryId ? `http://localhost:8080/category/${categoryId}`
                        : "http://localhost:8080/category/get-all-pet");
                setProducts(res.data);
                console.log(res.data)
            } catch (err) {
                console.log("Loi gi roi");
            };
        };
        getProducts();
    }, [categoryId]);

    useEffect(() => {
        categoryId && setFilteredProducts(
            products.filter(item =>
                Object.entries(filters).every(([key, value]) =>
                    item[key].includes(value)
                )
            )
        );
    }, [products, categoryId, filters]);

    useEffect(() => {
        if (sort === "newest") {
            setFilteredProducts((prev) =>
                [...prev].sort((a, b) => b.petId - a.petId)
            );
        } else if (sort === "increase") {
            setFilteredProducts((prev) =>
                [...prev].sort((a, b) => a.petPriceDiscount - b.petPriceDiscount)
            );
        } else {
            setFilteredProducts((prev) =>
                [...prev].sort((a, b) => b.petPriceDiscount - a.petPriceDiscount)
            );
        }
    }, [sort]);

    console.log(categoryId, filters, sort);

    // PHÂN TRANG

    const [pageNumber, setPageNumber] = useState(0);

    const productPerPage = 10;
    const pageVisited = pageNumber * productPerPage;

    const displayProducts = Filteredproducts
        .slice(pageVisited, pageVisited + productPerPage)
        .map(item => {
            return (
                <Link
                    to={`/product/${item.petId}`}>
                    <Product item={item} key={item.petId} />
                </Link>
            );
        }
        );


    const pageCount = Math.ceil(Filteredproducts.length / productPerPage);
    const changePage = ({ selected }) => {
        setPageNumber(selected)
    }
    return (
        // <Container>
        //     {
        //         tendanhmuc
        //         ? Filteredproducts.map(item => (
        //             <Link
        //                 to={`/product/${item.mathucung}`}>
        //                 <Product item={item} key={item.mathucung} />
        //             </Link>
        //         ))
        //         : products.slice(0,10).map(item => (
        //             <Link
        //                 to={`/product/${item.mathucung}`}>
        //                 <Product item={item} key={item.mathucung} />
        //             </Link>
        //         ))
        //     }
        // </Container>
        <>
            <Container>
                {
                    categoryId
                        ?
                        displayProducts
                        : products.slice(0, 10).map(item => (
                            <Link
                                to={`/product/${item.petId}`}>
                                <Product item={item} key={item.petId} />
                            </Link>
                        ))
                }
            </Container>
            {
                categoryId
                    ?
                    <ReactPaginate
                        previousLabel={"PREVIOUS"}
                        nextLabel={"NEXT"}
                        pageCount={pageCount}
                        onPageChange={changePage}
                        containerClassName={"paginationBttns"}
                        previousLinkClassName={"previousBttn"}
                        nextLinkClassName={"nextBttn"}
                        disabledClassName={"paginationDisabled"}
                        activeClassName={"paginationActive"}
                        nextClassName={"nextClassName"}
                        pageLinkClassName={"pageLinkClassName"}
                    />
                    :
                    <></>
            }
        </>
    )
}

export default Products