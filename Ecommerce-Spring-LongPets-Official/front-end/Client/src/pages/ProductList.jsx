import styled from "styled-components"
import Announcement from "../components/Announcement"
import Navbar from "../components/Navbar"
import Products from "../components/Products"
import Newsletter from "../components/Newsletter"
import Footer from "../components/Footer"
import { mobile } from "../responsive"
import { useLocation } from "react-router-dom"
import { useEffect, useState } from "react"
import axios from "axios"

const Container = styled.div`

`

const Title = styled.h1`
  margin: 20px;
`

const FilterContainer = styled.div`
  display: flex;
  justify-content: space-between;
`

const Filter = styled.div`
  margin: 20px;
  ${mobile({ width: "0px 20px", display: "flex", flexDirection: "column" })}
`

const FilterText = styled.span`
  font-size: 20px;
  font-weight: 600;
  margin-right: 20px;
  ${mobile({ marginRight: "0px" })}
`

const Select = styled.select`
  padding: 10px;
  margin-right: 20px;
  ${mobile({ margin: "10px 0px" })}
`

const Option = styled.option``

const ProductList = () => {
  const location = useLocation();
  const categoryId = location.pathname.split("/")[2];
  const [filters, setFilters] = useState({});
  const [sort, setSort] = useState("moinhat");
  const [petNameArray, setPetNameArray] = useState([]);
  const [categoryName, setCategoryName] = useState("");

  const handleFilters = (e) => {
    const value = e.target.value;
    setFilters({
      ...filters,
      [e.target.name]: value,
    });
  };
  console.log(filters, sort);

  useEffect(() => {
    const getAllPetNameByCategoryId = async () => {
      const petNameRes = await axios.get(`http://localhost:8080/pet/get-all-pet-name-by-category-id/${categoryId}`);
      console.log("petNameRes: ", petNameRes);
      setPetNameArray(petNameRes.data);
      setCategoryName(petNameRes.data[0].categoryTitle);
    }
    getAllPetNameByCategoryId();
  }, [categoryId])
  return (
    <Container>
      <Navbar />
      <Announcement />
      <Title>Danh mục thú cưng: <span style={{color: "var(--color-primary)"}}>{categoryName}</span></Title>
      <FilterContainer>
        <Filter>
          <FilterText>Bộ lọc thú cưng:</FilterText>
          <Select name="petName" onChange={handleFilters}>
            <Option disabled>
              Tên thú cưng
            </Option>
            <Option value="">Tất cả</Option>
            {
              petNameArray 
              ?
              petNameArray.map((petName, key) => {
                return (
                  <Option value={petName.petName}>{petName.petName}</Option>
                );
              })
              : null
            }
          </Select>
          <Select name="petGender" onChange={handleFilters}>
            <Option disabled>
              Giới tính thú cưng
            </Option>
            <Option value="">Tất cả</Option>
            <Option value="Đực">Đực</Option>
            <Option value="Cái">Cái</Option>
          </Select>
        </Filter>
        <Filter>
          <FilterText>Sắp xếp theo:</FilterText>
          <Select onChange={e => setSort(e.target.value)} >
            <Option value="newest">Mới nhất</Option>
            <Option value="increase">Giá tăng dần</Option>
            <Option value="decrease">Giá giảm dần</Option>
          </Select>
        </Filter>
      </FilterContainer>
      <Products categoryId={categoryId} filters={filters} sort={sort} />
      <Newsletter />
      <Footer />
    </Container>
  )
}

export default ProductList