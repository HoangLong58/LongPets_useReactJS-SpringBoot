import styled from "styled-components";
import { CloseOutlined } from "@mui/icons-material";
import { useCallback, useEffect, useRef, useState } from "react";
import "../../css/main.css";
import axios from "axios";

const Background = styled.div`
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;


    top: 0;
    right: 0;
    bottom: 0;
    left: 0;

    animation: fadeIn linear 0.1s;
`

const ModalWrapper = styled.div`
    width: 500px;
    height: auto;
    box-shadow: 0 5px 16px rgba(0, 0, 0, 0.2);
    background: var(--color-white);
    color: var(--color-dark);
    display: flex;
    justify-content: center;
    align-items: center;

    position: relative;
    z-index: 10;
    border-radius: 10px;
    --growth-from: 0.7;
    --growth-to: 1;
    animation: growth linear 0.1s;
`

const ThemThuCungWrapper = styled.div`
    width: auto;
    height: auto;
    box-shadow: 0 5px 16px rgba(0, 0, 0, 0.2);
    background: var(--color-white);
    color: var(--color-dark);
    display: flex;
    justify-content: center;
    align-items: center;

    position: relative;
    z-index: 10;
    border-radius: 10px;
    --growth-from: 0.7;
    --growth-to: 1;
    animation: growth linear 0.1s;
`

const ChiTietWrapper = styled.div`
    width: 70%;
    height: auto;
    box-shadow: 0 5px 16px rgba(0, 0, 0, 0.2);
    background: var(--color-white);
    color: var(--color-dark);
    display: flex;
    justify-content: center;
    align-items: center;

    position: relative;
    z-index: 10;
    border-radius: 10px;
    --growth-from: 0.7;
    --growth-to: 1;
    animation: growth linear 0.1s;
`

const ModalImg = styled.img`
    width: 100%;
    height: 100%;
    border-radius: 10px 0 0 10px;
    background: var(--color-dark);
`

const ModalContent = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    line-height: 1.8;
    color: #141414;

    p {
        margin-bottom: 1rem;
    }
`

const CloseModalButton = styled.span`
    cursor: pointer;
    position: absolute;
    top: 20px;
    right: 20px;
    width: 32px;
    height: 32px;
    padding: 0;
    z-index: 10;
`

const Button = styled.div`
    margin-top: 30px;
    width: 100%;
    display: flex;
    justify-content: space-around;
    flex-direction: row;
`

const H1 = styled.h1`
margin-top: 30px;
`

const ModalForm = styled.form`
width: 100%;    
height: 100%;
    display: flex;
    flex-direction: column;
    border-radius: var(--card-border-radius);
    padding: var(--card-padding);
    box-shadow: var(--box-shadow);
    transition: all 300ms ease;
    &:hover {
        box-shadow: none;
    }
`

const ModalFormItem = styled.div`
margin: 10px 30px;
display: flex;
flex-direction: column;
`

const ModalChiTietItem = styled.div`
margin: 2px 30px;
display: flex;
flex-direction: column;
`

const FormSpan = styled.span`
font-size: 1.2rem;
height: 600;
color: var(--color-dark-light);
margin-bottom: 3px;
`
const FormInput = styled.input`
background-color: var(--color-white);
color: var(--color-dark);
width: auto;
padding: 12px 20px;
margin: 8px 0;
display: inline-block;
border: 1px solid #ccc;
border-radius: 4px;
box-sizing: border-box;
&:focus {
    border: 1px solid var(--color-success);
    box-shadow: var(--color-success) 0px 1px 4px, var(--color-success) 0px 0px 0px 3px;
}
`

const ButtonUpdate = styled.div`
    width: 100%;
    margin: 18px 0px;
    display: flex;
    justify-content: space-around;
    flex-direction: row;
`

const ButtonContainer = styled.div`
    position: relative;
    float: right;
    margin: 0 22px 22px 0;
    &::after {
        content: "";
        border: 2px solid black;
        position: absolute;
        top: 5px;
        left: 5px;
        right: 20px;
        background-color: transperent;
        width: 95%;
        height: 95%;
        z-index: -1;
    }
`

const ButtonClick = styled.button`
    padding: 10px;
    border: 2px solid black;
    background-color: black;
    color: white;
    cursor: pointer;
    font-weight: 500;
    &:hover {
        background-color: #fe6430;
    }
    &:active {
        background-color: #333;
        transform: translate(5px, 5px);
        transition: transform 0.25s;
    }
`

const FormImg = styled.img`
    margin: auto;
    width: 50%;
    object-fit: cover;
    height: 200px;
`

const ChiTietHinhAnh = styled.img`
    width: 100px;
    height: 100px;
    object-fit: cover;
    margin: auto;
`

const ImageWrapper = styled.div`
    display: flex;
    flex-direction: row;
    &img {
        margin: 0px 20px;
    }
`

const FormSelect = styled.select`
    background-color: var(--color-white);
    color: var(--color-dark);
    width: auto;
    padding: 12px 20px;
    margin: 8px 0;
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

const FormLabel = styled.label`
    display: flex;
    flex-directory: row;
    // justify-content: center;
    align-items: center;
`

const FormCheckbox = styled.input`
    appearance: auto;
    margin-right: 10px;
`

const FormTextArea = styled.textarea`
    background-color: var(--color-white);
    color: var(--color-dark);
    width: auto;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    &:focus {
        border: 1px solid var(--color-success);
        box-shadow: var(--color-success) 0px 1px 4px, var(--color-success) 0px 0px 0px 3px;
    }
`

const Modal = ({ showModal, setShowModal, type, customer, setReRenderData, handleClose, showToastFromOut }) => {
    const modalRef = useRef();
    const closeModal = (e) => {
        if (modalRef.current === e.target) {
            setShowModal(false);
        }
    }

    const keyPress = useCallback(
        (e) => {
            if (e.key === 'Escape' && showModal) {
                setShowModal(false);
            }
        },
        [setShowModal, showModal]
    );

    useEffect(
        () => {
            document.addEventListener('keydown', keyPress);
            return () => document.removeEventListener('keydown', keyPress);
        },
        [keyPress]
    );
    // =============== X??? l?? x??a th?? c??ng ===============
    const handleDeleteCustomer = async ({ customerId, customerName }) => {
        if (customerId !== "") {
            try {
                const deleteCustomerRes = await axios.put(`http://localhost:8080/customer/delete-customer/${customerId}`);
                console.log("KQ tr??? v??? delete: ", deleteCustomerRes);
                setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - DanhMucMain & DanhMucRight.jsx
                setShowModal(prev => !prev);
                handleClose();  //????ng thanh t??m ki???m
                const dataShow = { message: "???? x??a kh??ch h??ng " + customerName + " th??nh c??ng!", type: "success" };
                showToastFromOut(dataShow);
            } catch (err) {
                console.log("L???i Delete kh??ch h??ng err: ", err);
            }
        }
    }

    // =============== X??? l?? Xem chi ti???t th?? c??ng ===============
    const handleCloseDetail = () => {
        setShowModal(prev => !prev);
    }

    const [wardNameDistrictNameCityName, setWardNameDistrictNameCityName] = useState();
    useEffect(() => {
        const getWardDistrictCity = async () => {
            try {
                const wardDistrictCityRes = await axios.get(`http://localhost:8080/customer/get-ward-district-city/${customer.wardCustomer.wardId}`);
                setWardNameDistrictNameCityName(", " + wardDistrictCityRes.data.wardName + ", " + wardDistrictCityRes.data.districtName + ", " + wardDistrictCityRes.data.cityName);
            } catch(err) {
                console.log("L???y x?? ph?????ng th??? tr???n, tp th???t b???i");
            }
        }
        getWardDistrictCity();
    }, [customer])
    // ================================================================
    //  =============== Xem chi ti???t kh??ch h??ng ===============
    if (type === "detailCustomer") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ChiTietWrapper showModal={showModal} style={{ flexDirection: `column` }}>
                            <H1>Chi ti???t kh??ch h??ng</H1>
                            <ModalForm>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <ImageWrapper>
                                            <ChiTietHinhAnh src={customer.customerAvatar} />
                                        </ImageWrapper>
                                    </ModalChiTietItem>
                                    <div style={{ display: "flex", flex: "1" }}>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>H??? t??n kh??ch h??ng:</FormSpan>
                                            <FormInput type="text" value={customer.customerName} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Ng??y sinh:</FormSpan>
                                            <FormInput type="text" value={customer.customerBirthday != null ? customer.customerBirthday.substring(0,10) : "Ch??a c???p nh???t"} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Gi???i t??nh:</FormSpan>
                                            <FormInput type="text" value={customer.customerGender != null ? customer.customerGender : "Ch??a c???p nh???t"} readOnly />
                                        </ModalChiTietItem>
                                    </div>
                                </div>
                                <div style={{ display: "flex", flex: "1" }}>
                                    <ModalChiTietItem style={{ flex: "1", marginLeft: "26%" }}>
                                        <FormSpan>?????a ch??? kh??ch h??ng:</FormSpan>
                                        <FormInput type="text" value={customer.customerAddress ? customer.customerAddress + wardNameDistrictNameCityName : "Ch??a c???p nh???t"} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Email:</FormSpan>
                                        <FormInput type="text" value={customer.customerEmail} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>S??? ??i???n tho???i:</FormSpan>
                                        <FormInput type="text" value={customer.customerPhone ? customer.customerPhone : "Ch??a c???p nh???t"} readOnly />
                                    </ModalChiTietItem>
                                </div>

                            </ModalForm>
                            <ButtonUpdate>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={handleCloseDetail}
                                    >????ng</ButtonClick>
                                </ButtonContainer>
                            </ButtonUpdate>
                            <CloseModalButton
                                aria-label="Close modal"
                                onClick={handleCloseDetail}
                            >
                                <CloseOutlined />
                            </CloseModalButton>
                        </ChiTietWrapper>
                    </Background>
                ) : null}
            </>
        );
    }
    // // =============== X??a kh??ch h??ng ===============
    if (type === "deleteCustomer") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ModalWrapper showModal={showModal} style={{ backgroundImage: `url("https://img.freepik.com/free-vector/alert-safety-background_97886-3460.jpg?w=1060")`, backgroundPosition: `center center`, backgroundRepeat: `no-repeat`, backgroundSize: `cover`, width: `600px`, height: `400px` }} >
                            <ModalContent>
                                <h1>B???n mu???n x??a kh??ch h??ng <span style={{ color: `var(--color-primary)` }}>{customer.customerName}</span></h1>
                                <p>Th??ng tin kh??ch h??ng kh??ng th??? kh??i ph???c. B???n c?? ch???c ch???n?</p>
                                <Button>
                                    <ButtonContainer>
                                        <ButtonClick
                                            onClick={() => { handleDeleteCustomer({ customerId: customer.customerId, customerName: customer.customerName }) }}
                                        >?????ng ??</ButtonClick>
                                    </ButtonContainer>
                                    <ButtonContainer>
                                        <ButtonClick
                                            onClick={() => setShowModal(prev => !prev)}
                                        >H???y b???</ButtonClick>
                                    </ButtonContainer>
                                </Button>
                            </ModalContent>
                            <CloseModalButton
                                aria-label="Close modal"
                                onClick={() => setShowModal(prev => !prev)}
                            >
                                <CloseOutlined />
                            </CloseModalButton>
                        </ModalWrapper>
                    </Background>
                ) : null}
            </>
        );
    }
};

export default Modal;