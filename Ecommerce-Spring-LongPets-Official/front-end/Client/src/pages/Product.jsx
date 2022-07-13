import { Add, AssignmentTurnedInOutlined, BorderColorOutlined, CakeOutlined, HealingOutlined, PetsOutlined, Remove, Restaurant, WcOutlined } from "@material-ui/icons"
import axios from "axios"
import { useEffect, useRef, useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"
import styled from "styled-components"
import Announcement from "../components/Announcement"
import Footer from "../components/Footer"
import Navbar from "../components/Navbar"
import Newsletter from "../components/Newsletter"
import { mobile } from "../responsive"
import { Col, Row } from "react-bootstrap"
import SliderImage from "../components/SliderImage"
import "../css/main.css"
import format_money from "../utils";
import { addProduct, updateProduct } from "../redux/cartRedux";
import { useDispatch, useSelector } from "react-redux";
import Toast from "../components/Toast"

const Container = styled.div`

`

const Wrapper = styled.div`
    padding: 50px;
    display: flex;
    ${mobile({ padding: "10px", flexDirection: "column" })}
`

const ImgContainer = styled.div`
    flex: 1;
`

const Image = styled.img`
    width: 100%;
    height: 70vh;
    object-fit: cover;
    ${mobile({ height: "40vh" })}
`

const InfoContainer = styled.div`
    flex: 1;
    padding: 0px 50px;
    ${mobile({ padding: "10px" })}
`

const Title = styled.h1`
    font-weight: 200;
`

const Desc = styled.p`
    margin: 20px 0px;
`

const Price = styled.span`
    font-weight: 100;
    font-size: 40px;
`

// Bộ lọc
// const FilterContainer = styled.div`
//     width: 50%;
//     margin: 30px 0px;
//     display: flex;
//     justify-content: space-between;
//     ${mobile({ width: "100%" })}
// `

// const Filter = styled.div`
//     display: flex;
//     align-items: center;
// `

// const FilterTitle = styled.span`
//     font-size: 20px;
//     font-weight: 200;
// `

// const FilterColor = styled.div`
//     width: 20px;
//     height: 20px;
//     border-radius: 50%;
//     background-color: ${props => props.color};
//     margin: 0px 5px;
//     cursor: pointer;
// `

// const FilterSize = styled.select`
//     margin-left: 10px;
//     padding: 5px;
// `

// const FilterSizeOption = styled.option``

const AddContainer = styled.div`
    width: 80%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    ${mobile({ width: "100%" })}
`

const AmountContainer = styled.div`
    display: flex;
    align-items: center;
    font-weight: 700;
`

const Amount = styled.span`
    width: 30px;
    height: 30px;
    border-radius: 10px;
    border: 1px solid teal;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0px 5px;
`

const ButtonContainer = styled.div`
    position: relative;
    &::after {
        content: "";
        border: 2px solid teal;
        position: absolute;
        top: 5px;
        left: 5px;
        right: 20px;
        background-color: transperent;
        width: 100%;
        height: 100%;
        z-index: -1;
    }
`

const Button = styled.button`
    padding: 15px;
    border: 2px solid teal;
    background-color: white;
    cursor: pointer;
    font-weight: 500;
    &:hover {
        background-color: #f8f4f4;
    }
    &:active {
        background-color: #f8f4f4;
        transform: translate(5px, 5px);
        transition: transform 0.25s;
    }
`

const MoreImage = styled.div`
    width: 650px;
`

const DetailProduct = styled.div`
    width: 600px;
    margin: 20px 0;
`

const DetailHeader = styled.h1`
    font-weight: 300;
`

const DetailInfo = styled.div`
    width: 100%;

`

const Product = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const petId = location.pathname.split("/")[2];
    const [product, setProduct] = useState({});
    const [image, setPetImage] = useState([]);
    const [petTitle, setPetTitle] = useState();
    const [petDescription, setPetDescription] = useState();
    const [petName, setPetName] = useState();
    const [categoryName, setCategoryName] = useState();
    const [petGender, setPetGender] = useState();
    const [petAge, setPetAge] = useState();
    const [petVaccinated, setPetVaccinated] = useState();
    const [petHealthWarranty, setPetHealthWarranty] = useState();
    const [petPriceDiscount, setPetPriceDiscount] = useState();
    const [petPriceDiscountString, setPetPriceDiscountString] = useState();
    const [petQuantity, setPetQuantity] = useState();
    const [petQuantityBuy, setPetQuantityBuy] = useState(1);
    const dispatch = useDispatch();

    const cart = useSelector(state => state.cart);
    useEffect(() => {
        const getProduct = async () => {
            const pet = await axios.get(`http://localhost:8080/pet/${petId}`);
            setProduct(pet);
            const petImage = await axios.get(`http://localhost:8080/image/${petId}`);
            petImage.data.map((petImage, index) => {
                setPetImage(prev => {
                    const isHave = image.includes(petImage.imageContent);
                    if (isHave) {
                        return [...prev];
                    } else {
                        return [...prev, petImage.imageContent];
                    }
                });
            })
            setPetTitle(pet.data[0].petTitle);
            setPetDescription(pet.data[0].petDescription);
            setPetName(pet.data[0].petName);
            setCategoryName(pet.data[0].categoryName);
            setPetGender(pet.data[0].petGender);
            setPetAge(pet.data[0].petAge);
            setPetVaccinated(pet.data[0].petVaccinated);
            setPetHealthWarranty(pet.data[0].petHealthWarranty);
            setPetPriceDiscountString(format_money(pet.data[0].petPriceDiscount.toString()));
            setPetPriceDiscount(pet.data[0].petPriceDiscount);
            setPetQuantity(pet.data[0].petQuantity);
        };
        getProduct();
    }, [petId]);

    const handlePetQuantityBuy = (type) => {
        if (type === "decrease") {
            petQuantityBuy > 1 && setPetQuantityBuy(petQuantityBuy - 1);
        } else {
            petQuantityBuy < petQuantity && setPetQuantityBuy(petQuantityBuy + 1);
        }
    }

    // ===== TOAST =====
    const [dataToast, setDataToast] = useState({ message: "alo alo", type: "success" });
    const toastRef = useRef(null);  // useRef có thể gọi các hàm bên trong của Toast
    // bằng các dom event, javascript, ...

    const showToastFromOut = (dataShow) => {
        console.log("showToastFromOut da chay", dataShow);
        setDataToast(dataShow);
        toastRef.current.show();
    }

    const handleAddCart = () => {
        // // Cập nhật lại giỏ hàng


        // let i, isHave, soluongmuonmua = 0;
        // for(i=0; i< cart.products.length; i++){
        //     if(cart.products[i].data[0].petId === parseInt(petId)){
        //         isHave = true;
        //         soluongmuonmua = cart.products[i].soluongmua;
        //         break;
        //     }
        // }
        // console.log(soluongmuonmua)
        // let soluongcothemua = soluong - soluongmuonmua;
        // if(soluongcothemua > 0){
        //     if(isHave){
        //         dispatch(updateProduct({ ...product, soluongmua }));
        //     }else {
        //         dispatch(addProduct({ ...product, soluongmua }));
        //     } 
        // }else{
        //     console.log("Đã hết thú cưng!")
        // }




        // let i, isHave, soluongmua;
        // for(i=0; i< cart.products.length; i++){
        //     if(cart.products[i].data[0].petId === parseInt(petId)){
        //         isHave = true;
        //         break;
        //     }
        // }
        // if(isHave){
        //     dispatch(updateProduct({ ...product, soluongmua }));
        // }else {
        //     dispatch(addProduct({ ...product, soluongmua }));
        // }
        dispatch(addProduct({ ...product, petQuantityBuy }));
        const dataShow = { message: "Đã thêm thú cưng vào giỏ hàng", type: "success" };
        showToastFromOut(dataShow); //Hiện toast thông báo
    }

    const handleBuyNow = () => {
        handleAddCart();
        navigate("/order");
    }

    console.log(cart);
    return (
        <Container>
            <Navbar />
            <Announcement />
            <Wrapper>
                <ImgContainer>
                    {/* <Image src={image[0]} /> */}
                    <MoreImage >
                        <SliderImage image={image} />
                    </MoreImage>
                </ImgContainer>
                <InfoContainer>
                    <Title>{petTitle}</Title>
                    <Desc>
                        {petDescription}
                    </Desc>
                    <Price>{petPriceDiscountString}<u>đ</u></Price>
                    {/* <FilterContainer>
                        <Filter>
                            <FilterTitle>Color</FilterTitle>
                            <FilterColor color="black" />
                            <FilterColor color="blue" />
                            <FilterColor color="red" />
                        </Filter>
                        <Filter>
                            <FilterTitle>Size</FilterTitle>
                            <FilterSize>
                                <FilterSizeOption>XS</FilterSizeOption>
                                <FilterSizeOption>S</FilterSizeOption>
                                <FilterSizeOption>M</FilterSizeOption>
                                <FilterSizeOption>L</FilterSizeOption>
                                <FilterSizeOption>XL</FilterSizeOption>
                            </FilterSize>
                        </Filter>
                    </FilterContainer> */}

                    {/* Chi tiết */}
                    <DetailProduct>
                        <DetailHeader>Thông tin chi tiết</DetailHeader>
                        <DetailInfo>
                            <Row style={{ margin: "20px 0px" }}>
                                <Col xl="6"><BorderColorOutlined /><span style={{ marginLeft: "10px" }}>Tên thú cưng</span></Col>
                                <Col xl="6">{petName}</Col>
                            </Row>
                            <Row style={{ margin: "20px 0px" }}>
                                <Col xl="6"><PetsOutlined /><span style={{ marginLeft: "10px" }}>Danh mục</span></Col>
                                <Col xl="6">{categoryName}</Col>
                            </Row>
                            <Row style={{ margin: "20px 0px" }}>
                                <Col xl="6"><WcOutlined /><span style={{ marginLeft: "10px" }}>Giới tính</span></Col>
                                <Col xl="6">{petGender}</Col>
                            </Row>
                            <Row style={{ margin: "20px 0px" }}>
                                <Col xl="6"><CakeOutlined /><span style={{ marginLeft: "10px" }}>Tuổi</span></Col>
                                <Col xl="6">{petAge}</Col>
                            </Row>
                            <Row style={{ margin: "20px 0px" }}>
                                <Col xl="6"><HealingOutlined /><span style={{ marginLeft: "10px" }}>Tiêm chủng</span></Col>
                                <Col xl="6">{petVaccinated}</Col>
                            </Row>
                            <Row style={{ margin: "20px 0px 50px 0px" }}>
                                <Col xl="6"><AssignmentTurnedInOutlined /><span style={{ marginLeft: "10px" }}>Bảo hành</span></Col>
                                <Col xl="6">{petHealthWarranty}</Col>
                            </Row>
                            <Row style={{ margin: "20px 0px 50px 0px", fontSize: "18px" }}>
                                <Col xl="3"></Col>
                                <Col xl="6">Hiện có <span style={{ fontWeight: "500", fontSize: "20px", color: "#fe6433" }}>{petQuantity}</span> thú cưng tại shop</Col>
                                <Col xl="3"></Col>

                            </Row>
                        </DetailInfo>
                    </DetailProduct>

                    <AddContainer>
                        <AmountContainer>
                            <Remove onClick={() => handlePetQuantityBuy("decrease")} />
                            <Amount>{petQuantityBuy}</Amount>
                            <Add onClick={() => handlePetQuantityBuy("increase")} />
                        </AmountContainer>
                        <ButtonContainer>
                            <Button onClick={handleAddCart}>Thêm vào giỏ hàng</Button>
                        </ButtonContainer>
                        <ButtonContainer>
                            <Button onClick={handleBuyNow}>Mua ngay</Button>
                        </ButtonContainer>
                    </AddContainer>
                </InfoContainer>
            </Wrapper>
            <Newsletter />
            {/* === TOAST === */}
            <Toast
                ref={toastRef}
                dataToast={dataToast}   // Thông tin cần hiện lên: Đối tượng { message,type }
            />
            <Footer />
        </Container>
    )
}

export default Product