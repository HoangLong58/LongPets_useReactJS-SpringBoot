import axios from 'axios';
import { useEffect, useRef, useState } from 'react';
import { Carousel } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Announcement from '../components/Announcement';
import Footer from '../components/Footer';
import OrderImage from '../components/OrderImage';
import Navbar from '../components/Navbar';
import "../css/main.css";
import format_money from "../utils"
import { logoutCart } from "../redux/cartRedux";
import Toast from "../components/Toast";

const Container = styled.div`

`

const Wrapper = styled.div`
max-width: 1200px;
margin: 20px auto;
overflow: hidden;
background-color: #f8f9fa;
box-shadow: 0 2px 3px #e0e0e0;
display: flex;
`

const Box1 = styled.div`
max-width: 600px;
padding: 10px 40px;
user-select: none;
`

const Box2 = styled.div`
width: 100%;
padding: 10px 40px;
`

const Title1 = styled.div`
display: flex;
justify-content: space-between;
`

const CartItem = styled.div`
display: flex;
width: 100%;
font-size: 1.1rem;
background: #ddd;
margin-top: 10px;
padding: 10px 12px;
border-radius: 5px;
cursor: pointer;
border: 1px solid transparent;
`

const Circle = styled.span`
height: 12px;
width: 12px;
background: #ccc;
border-radius: 50%;
margin-right: 15px;
border: 4px solid transparent;
display: inline-block
`

const Course = styled.div`
width: 100%
`

const Content = styled.div`
display: flex;
align-items: center;
justify-content: space-between;
`

const InfomationTitle = styled.div`
    font-size: 1.2rem;
`

const InfomationForm = styled.div`

`

const ModalChiTietItem = styled.div`
margin: 2px 0px;
display: flex;
flex-direction: column;
`

const FormSpan = styled.span`
font-size: 1.1rem;
font-weight: 700;
color: var(--color-dark-light);
margin-bottom: 3px;
`
const FormInput = styled.input`
background-color: var(--color-white);
color: var(--color-dark);
width: auto;
padding: 8px 20px;
margin: 5px 0;
display: inline-block;
outline: 0;
border: 1px solid #ccc;
border-radius: 4px;
box-sizing: border-box;
&:focus {
    border: 1px solid var(--color-success);
    box-shadow: var(--color-success) 0px 1px 4px, var(--color-success) 0px 0px 0px 3px;
}
`

const FormSelect = styled.select`
    background-color: var(--color-white);
    color: var(--color-dark);
    width: auto;
    padding: 8px 20px;
    margin: 5px 0;
    outline: 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    &:focus {
        border: 1px solid var(--color-success);
        box-shadow: var(--color-success) 0px 1px 4px, var(--color-success) 0px 0px 0px 3px;
    }
`

const FormOption = styled.option`
    margin: auto;
`

const FormTextArea = styled.textarea`
background-color: var(--color-white);
color: var(--color-dark);
width: auto;
padding: 8px 20px;
margin: 5px 0;
display: inline-block;
outline: 0;
border: 1px solid #ccc;
border-radius: 4px;
box-sizing: border-box;
&:focus {
    border: 1px solid var(--color-success);
    box-shadow: var(--color-success) 0px 1px 4px, var(--color-success) 0px 0px 0px 3px;
}
`
const Total = styled.div`
display: flex;
flex-direction: column;
`

const TotalItem = styled.div`
display: flex;
align-items: center;
justify-content: space-between;
`

const ButtonContainer = styled.div`
    justify-content: center;
    position: relative;
    float: right;
    margin: 10px 22px 22px 0;
    display: flex;
    &::after {
        content: "";
        border: 2px solid black;
        position: absolute;
        top: 5px;
        right: -5px;
        background-color: transperent;
        width: 150px;
        height: 100%;
        z-index: 5;
    }
`

const Button = styled.button`
    padding: 10px;
    width: 150px;
    border: 2px solid black;
    background-color: black;
    color: white;
    cursor: pointer;
    font-weight: 500;
    z-index: 10;
    &:hover {
        background-color: #fe6430;
    }
    &:active {
        background-color: #333;
        transform: translate(5px, 5px);
        transition: transform 0.25s;
    }
`


const Order = () => {
    // Page success sau khi đặt mua thành công
    const navigate = useNavigate();

    // ===== TOAST =====
    const [dataToast, setDataToast] = useState({ message: "alo alo", type: "success" });
    const toastRef = useRef(null);  // useRef có thể gọi các hàm bên trong của Toast
    // bằng các dom event, javascript, ...

    const showToastFromOut = (dataShow) => {
        console.log("showToastFromOut da chay", dataShow);
        setDataToast(dataShow);
        toastRef.current.show();
    }


    // User, Cart từ redux
    const user = useSelector(state => state.user.currentUser);
    const cart = useSelector(state => state.cart);
    const dispatch = useDispatch();

    // Các state khởi tạo
    // ---- Có user (Đã đăng nhập)
    const [wardId, setWardId] = useState("");
    const [customerId, setCustomerId] = useState("");
    const [customerEmail, setCustomerEmail] = useState("");
    const [customerName, setCustomerName] = useState("");
    const [customerPhone, setCustomerPhone] = useState("");
    const [customerAddress, setCustomerAddress] = useState("");
    const [customerNote, setCustomerNote] = useState("");

    const [districtId, setDistrictId] = useState("");
    const [cityId, setCityId] = useState("");
    const [wardName, setWardName] = useState("");
    const [districtName, setDistrictName] = useState("");
    const [cityName, setCityName] = useState("");

    // Useeffect lấy dữ liệu từ USer
    useEffect(() => {
        setCustomerId(user ? user.customerId : "");
        setCustomerEmail(user ? user.customerEmail : "");
        setCustomerName(user ? user.customerName : "");
        setCustomerPhone(user ? user.customerPhone : "");
        setCustomerAddress(user ? user.customerAddress : "");

        setWardId(user ? user.wardCustomer.wardId : "");
        setDistrictId(user ? user.wardCustomer.districtWard.districtId : "");
        setCityId(user ? user.wardCustomer.districtWard.cityDistrict.cityId : "");
        setWardName(user ? user.wardCustomer.wardName : "");
        setDistrictName(user ? user.wardCustomer.districtWard.districtName : "");
        setCityName(user ? user.wardCustomer.districtWard.cityDistrict.cityName : "");
    }, [])

    // Effect Tỉnh - Huyện - Xã (Có user)
    const [cityArray, setCityArray] = useState([]);
    const [districtArray, setDistrictArray] = useState([]);
    const [wardArray, setWardArray] = useState([]);
    useEffect(() => {
        const getCity = async () => {
            const cityRes = await axios.get("http://localhost:8080/city");
            setCityArray(cityRes.data);
            console.log("Tỉnh TP [res]: ", cityRes.data);
        }
        getCity();
    }, [])

    useEffect(() => {
        const getDistrict = async () => {
            const districtRes = await axios.get(`http://localhost:8080/district/${cityId}`);
            setDistrictArray(districtRes.data);
            console.log("Quận huyện  [res]: ", districtRes.data);
        }
        getDistrict();
    }, [cityId])

    useEffect(() => {
        const getWard = async () => {
            const wardRes = await axios.get(`http://localhost:8080/ward/${districtId}`);
            setWardArray(wardRes.data);
            console.log("Xã phường  res: ", wardRes.data);
        }
        getWard();
    }, [districtId])

    // XỬ LÝ ĐẶT MUA - CÓ USER (có mã người mua) - Ko USer thì mã người mua = 0;
    const Order = async ({
        customerId,
        wardId,
        orderName,
        orderEmail,
        orderPhone,
        orderAddress,
        orderNote,
        orderTotal,
        cart
    }) => {
        console.log("Đầu vào ĐẶT MUA: ", {
            customerId,
            wardId,
            orderName,
            orderEmail,
            orderPhone,
            orderAddress,
            orderNote,
            orderTotal,
            cart
        });
        if (
            customerId != ""
            && wardId != ""
            && orderName != ""
            && orderEmail != ""
            && orderPhone != ""
            && orderAddress != ""
            // ghichudathang
        ) {
            try {
                const orderRes = await axios.post("http://localhost:8080/order/add-order", { customerId, wardId, orderName, orderEmail, orderPhone, orderAddress, orderNote, orderTotal, cart });
                console.log("Kết quả đặt mua: ", orderRes.status);
                if (orderRes.status == 200) {
                    // Chuyển đến trang đặt mua thành công
                    navigate("/success");
                    dispatch(logoutCart()); //Khởi tạo lại người dùng
                }
            } catch (err) {
                console.log("Lỗi khi đặt mua: ", err);
                const dataShow = { message: "Lỗi khi đặt mua!", type: "danger" };
                showToastFromOut(dataShow);
            }
        } else {
            const dataShow = { message: "Bạn chưa điền đầy đủ thông tin", type: "danger" };
            showToastFromOut(dataShow);
        }
    }

    // ---- Không có user (Chưa đăng nhập)
    const [wardIdNoUser, setWardIdNoUser] = useState("");
    const [customerEmailNoUser, setCustomerEmailNoUser] = useState("");
    const [customerNameNoUser, setCustomerNameNoUser] = useState("");
    const [customerPhoneNoUser, setCustomerPhoneNoUser] = useState("");
    const [customerAddressNoUser, setCustomerAddressNoUser] = useState("");
    const [customerNoteNoUser, setCustomerNoteNoUser] = useState("");

    const [districtIdNoUser, setDistrictIdNoUser] = useState("");
    const [cityIdNoUser, setCityIdNoUser] = useState("");
    const [wardNameNoUser, setWardNameNoUser] = useState("");
    const [districtNameNoUser, setDistrictNameNoUser] = useState("");
    const [cityNameNoUser, setCityNameNoUser] = useState("");
    // Effect Tỉnh - Huyện - Xã (Không có User)
    const [cityArrayNoUser, setCityArrayNoUser] = useState([]);
    const [districtArrayNoUser, setDistrictArrayNoUser] = useState([]);
    const [wardArrayNoUser, setWardArrayNoUser] = useState([]);
    useEffect(() => {
        const getCityNoUser = async () => {
            const cityRes = await axios.get("http://localhost:8080/city");
            setCityArrayNoUser(cityRes.data);
            console.log("Tỉnh TP [res]: ", cityRes.data);
        }
        getCityNoUser();
    }, [])

    useEffect(() => {
        const getDistrictNoUser = async () => {
            const districtRes = await axios.get(`http://localhost:8080/district/${cityIdNoUser}`);
            setDistrictArrayNoUser(districtRes.data);
            console.log("Quận huyện  [res]: ", districtRes.data);
        }
        getDistrictNoUser();
    }, [cityIdNoUser])

    useEffect(() => {
        const getWardNoUser = async () => {
            const wardRes = await axios.get(`http://localhost:8080/ward/${districtIdNoUser}`);
            setWardArrayNoUser(wardRes.data);
            console.log("Xã phường  res: ", wardRes.data);
        }
        getWardNoUser();
    }, [districtIdNoUser])


    return (
        <Container>
            <Navbar />
            <Announcement />

                    <Wrapper>
                        <Box1>
                            <Title1>
                                <p style={{ fontSize: "1.2rem", fontWeight: "bold" }}>Những thú cưng mà bạn muốn mua</p>
                            </Title1>
                            <Carousel style={{ maxHeight: "300px", overflow: "hidden" }}>
                                {
                                    cart.products.map((pet, key) => {
                                        return (
                                            <Carousel.Item>
                                                <OrderImage item={pet.data.petId}></OrderImage>
                                            </Carousel.Item>
                                        );
                                    })
                                }
                            </Carousel>
                            <p style={{ fontWeight: "500", marginTop: "10px" }}>Chi tiết giỏ hàng</p>
                            {
                                cart.products.map((pet, key) => {
                                    return (
                                        <CartItem>
                                            <Circle />
                                            <Course>
                                                <Content>
                                                    <span style={{ width: "320px", fontWeight: "bold" }}> {pet.data.petTitle} </span>
                                                    <span style={{ fontWeight: "400", color: "var(--color-primary)", width: "145px", textAlign: "right" }}>{format_money((pet.data.petPriceDiscount).toString())} VNĐ</span>
                                                </Content>
                                                <span style={{ fontWeight: "400" }}><span style={{ color: "var(--color-primary)" }}>{pet.petQuantityBuy}</span> x {pet.data.petName}</span>
                                            </Course>
                                        </CartItem>
                                    );
                                })
                            }
                        </Box1>
                        {
                            user
                                ?
                                <Box2>
                                    <InfomationTitle>
                                        <p style={{ fontWeight: "bold", margin: "10px 0 0 0" }}>Chi tiết thanh toán</p>
                                        <p style={{ fontSize: "1rem" }}>Hoàn tất thanh toán bằng việc cung cấp những thông tin sau</p>
                                    </InfomationTitle>
                                    <InfomationForm>
                                        <ModalChiTietItem>
                                            <FormSpan>Địa chỉ email:</FormSpan>
                                            <FormInput type="text" value={user.customerEmail} disabled />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Họ tên:</FormSpan>
                                            <FormInput type="text" onChange={(e) => { setCustomerName(e.target.value) }} value={customerName} />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Số điện thoại:</FormSpan>
                                            <FormInput type="text" onChange={(e) => { setCustomerPhone(e.target.value) }} value={customerPhone} />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Địa chỉ:</FormSpan>
                                            <FormInput type="text" onChange={(e) => { setCustomerAddress(e.target.value) }} value={customerAddress} />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Thuộc tỉnh:</FormSpan>
                                            <FormSelect onChange={(e) => { setCityId(e.target.value) }}>
                                                <FormOption value="">-- Chọn thành phố --</FormOption>
                                                {cityArray.map((city, key) => {
                                                    if (city.cityName === cityName) {
                                                        return (
                                                            <FormOption value={city.cityId} selected> {city.cityName} </FormOption>
                                                        )
                                                    } else {
                                                        return (
                                                            <FormOption value={city.cityId}> {city.cityName} </FormOption>
                                                        )
                                                    }
                                                })}
                                            </FormSelect>
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Thuộc huyện:</FormSpan>
                                            <FormSelect onChange={(e) => { setDistrictId(e.target.value) }}>
                                                {
                                                    districtArray.length > 0
                                                        ?
                                                        districtArray.map((district, key) => {
                                                            if (district.districtName === districtName) {
                                                                return (
                                                                    <FormOption value={district.districtId} selected> {district.districtName} </FormOption>
                                                                )
                                                            } else {
                                                                return (
                                                                    <FormOption value={district.districtId}> {district.districtName} </FormOption>
                                                                )
                                                            }
                                                        })
                                                        :
                                                        <FormOption value="">-- Bạn chưa chọn Thành phố -- </FormOption>
                                                }
                                            </FormSelect>
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Thuộc xã:</FormSpan>
                                            <FormSelect onChange={(e) => { setWardId(e.target.value) }}>
                                                {
                                                    wardArray.length > 0
                                                        ?
                                                        wardArray.map((ward, key) => {
                                                            if (ward.wardName === wardName) {
                                                                return (
                                                                    <FormOption value={ward.wardId} selected> {ward.wardName} </FormOption>
                                                                )
                                                            } else {
                                                                return (
                                                                    <FormOption value={ward.wardId}> {ward.wardName} </FormOption>
                                                                )
                                                            }
                                                        })
                                                        :
                                                        <FormOption value="">-- Bạn chưa chọn Huyện </FormOption>
                                                }
                                            </FormSelect>
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Ghi chú:</FormSpan>
                                            <FormTextArea rows="3" onChange={(e) => { setCustomerNote(e.target.value) }} value={customerNote} placeholder="Ghi chú về đơn đặt hàng này" />
                                        </ModalChiTietItem>
                                    </InfomationForm>
                                    <Total>
                                        <TotalItem>
                                            <p>Tổng tiền thú cưng</p>
                                            <p>{format_money((cart.cartTotal).toString())} VNĐ</p>
                                        </TotalItem>
                                        <TotalItem>
                                            <p>Phí vận chuyển</p>
                                            <p>0.00 VNĐ</p>
                                        </TotalItem>
                                        <TotalItem>
                                            <p style={{ color: "var(--color-primary)", fontWeight: "bold" }}>Tổng cộng</p>
                                            <p style={{ color: "var(--color-primary)", fontWeight: "bold" }}>{format_money((cart.cartTotal).toString())} VNĐ</p>
                                        </TotalItem>
                                        <div style={{ display: "flex", flexDirection: "row", justifyContent: "space-between" }}>
                                            <ButtonContainer>
                                                <Button
                                                    onClick={() => {
                                                        Order({
                                                            customerId: customerId,
                                                            wardId: wardId,
                                                            orderName: customerName,
                                                            orderEmail: customerEmail,
                                                            orderPhone: customerPhone,
                                                            orderAddress: customerAddress,
                                                            orderNote: customerNote,
                                                            orderTotal: cart.cartTotal,
                                                            cart: cart.products
                                                        })
                                                    }}
                                                >Đặt mua</Button>
                                            </ButtonContainer>
                                            <Link to="/">
                                                <ButtonContainer>
                                                    <Button>Trở lại</Button>
                                                </ButtonContainer>
                                            </Link>
                                        </div>
                                    </Total>
                                </Box2>
                                :
                                <Box2>
                                    <InfomationTitle>
                                        <p style={{ fontWeight: "bold", margin: "10px 0 0 0" }}>Chi tiết thanh toán</p>
                                        <p style={{ fontSize: "1rem" }}>Hoàn tất thanh toán bằng việc cung cấp những thông tin sau</p>
                                    </InfomationTitle>
                                    <InfomationForm>
                                        <ModalChiTietItem>
                                            <FormSpan>Địa chỉ email:</FormSpan>
                                            <FormInput type="email" onChange={(e) => { setCustomerEmailNoUser(e.target.value) }} placeholder="Email của bạn là" />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Họ tên:</FormSpan>
                                            <FormInput type="text" onChange={(e) => { setCustomerNameNoUser(e.target.value) }} placeholder="Họ tên của bạn là" />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Số điện thoại:</FormSpan>
                                            <FormInput type="text" onChange={(e) => { setCustomerPhoneNoUser(e.target.value) }} placeholder="Số điện thoại của bạn là" />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Địa chỉ:</FormSpan>
                                            <FormInput type="text" onChange={(e) => { setCustomerAddressNoUser(e.target.value) }} placeholder="Địa chỉ của bạn là" />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem >
                                            <FormSpan>Thuộc tỉnh:</FormSpan>
                                            <FormSelect onChange={(e) => { setCityIdNoUser(e.target.value) }}>
                                                <FormOption value="">-- Chọn thành phố --</FormOption>
                                                {cityArrayNoUser.map((city, key) => {
                                                    return (
                                                        <FormOption value={city.cityId}> {city.cityName} </FormOption>
                                                    )
                                                })}
                                            </FormSelect>
                                        </ModalChiTietItem>
                                        <ModalChiTietItem >
                                            <FormSpan>Thuộc huyện:</FormSpan>
                                            <FormSelect onChange={(e) => { setDistrictIdNoUser(e.target.value) }}>
                                                {
                                                    districtArrayNoUser.length > 0
                                                        ?
                                                        districtArrayNoUser.map((district, key) => {
                                                            return (
                                                                <FormOption value={district.districtId}> {district.districtName} </FormOption>
                                                            )
                                                        })
                                                        :
                                                        <FormOption value="">-- Bạn chưa chọn Thành phố -- </FormOption>
                                                }
                                            </FormSelect>
                                        </ModalChiTietItem>
                                        <ModalChiTietItem >
                                            <FormSpan>Thuộc xã:</FormSpan>
                                            <FormSelect onChange={(e) => { setWardIdNoUser(e.target.value) }}>
                                                {
                                                    wardArrayNoUser.length > 0
                                                        ?
                                                        wardArrayNoUser.map((ward, key) => {
                                                            return (
                                                                <FormOption value={ward.wardId}> {ward.wardName} </FormOption>
                                                            )
                                                        })
                                                        :
                                                        <FormOption value="">-- Bạn chưa chọn Huyện </FormOption>
                                                }
                                            </FormSelect>
                                        </ModalChiTietItem>
                                        <ModalChiTietItem>
                                            <FormSpan>Ghi chú:</FormSpan>
                                            <FormTextArea rows="3" onChange={(e) => { setCustomerNoteNoUser(e.target.value) }} placeholder="Ghi chú về đơn đặt hàng này" />
                                        </ModalChiTietItem>
                                    </InfomationForm>
                                    <Total>
                                        <TotalItem>
                                            <p>Tổng tiền thú cưng</p>
                                            <p>{format_money((cart.cartTotal).toString())} VNĐ</p>
                                        </TotalItem>
                                        <TotalItem>
                                            <p>Phí vận chuyển</p>
                                            <p>0.00 VNĐ</p>
                                        </TotalItem>
                                        <TotalItem>
                                            <p style={{ color: "var(--color-primary)", fontWeight: "bold" }}>Tổng cộng</p>
                                            <p style={{ color: "var(--color-primary)", fontWeight: "bold" }}>{format_money((cart.cartTotal).toString())} VNĐ</p>
                                        </TotalItem>
                                        <div style={{ display: "flex", flexDirection: "row", justifyContent: "space-between" }}>
                                            <ButtonContainer>
                                                <Button
                                                    onClick={() => {
                                                        Order({
                                                            customerId: "0",
                                                            wardId: wardIdNoUser,
                                                            orderName: customerNameNoUser,
                                                            orderEmail: customerEmailNoUser,
                                                            orderPhone: customerPhoneNoUser,
                                                            orderAddress: customerAddressNoUser,
                                                            orderNote: customerNoteNoUser,
                                                            orderTotal: cart.cartTotal,
                                                            cart: cart.products
                                                        })
                                                    }}
                                                >Đặt mua</Button>
                                            </ButtonContainer>
                                            <Link to="/">
                                                <ButtonContainer>
                                                    <Button>Trở lại</Button>
                                                </ButtonContainer>
                                            </Link>
                                        </div>
                                    </Total>
                                </Box2>
                        }
                    </Wrapper>


            {/* === TOAST === */}
            <Toast
                ref={toastRef}
                dataToast={dataToast}   // Thông tin cần hiện lên: Đối tượng { message,type }
            />
            <Footer />
        </Container>
    );
};

export default Order;
