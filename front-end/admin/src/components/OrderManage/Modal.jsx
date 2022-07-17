import format_money from "../../utils";
import styled from "styled-components";
import { CloseOutlined } from "@mui/icons-material";
import { useCallback, useEffect, useRef, useState } from "react";
import "../../css/main.css";
import axios from "axios";
import { useSelector } from 'react-redux';

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

const ThempetWrapper = styled.div`
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

const Table = styled.table`
    background: var(--color-white);
    width: 100%;
    border-radius: var(--card-border-radius);
    padding: var(--card-padding);
    text-align: center;
    box-shadow: var(--box-shadow);
    transition: all 300ms ease;
    &:hover {
        box-shadow: none;
    }
`

const Thead = styled.thead`
    background-color: var(--color-primary);
`

const Tr = styled.tr`
    &:last-child td {
        border: none;
    }
    &:hover {
        background: var(--color-light);
    }
`

const Th = styled.th`

`

const Tbody = styled.tbody`

`

const Td = styled.td`
    height: 2.8rem;
    border-bottom: 1px solid var(--color-light);
`

const Total = styled.div`
    display: flex;
    flex-direction: column;
    margin: 10px 30px 0 0;
    background: var(--color-white);
    width: 400px;
    border-radius: var(--card-border-radius);
    padding: var(--card-padding);
    text-align: center;
    box-shadow: var(--box-shadow);
    transition: all 300ms ease;
    font-size: 1rem;
    &:hover {
        box-shadow: none;
    }
`

const TotalItem = styled.div`
display: flex;
align-items: center;
justify-content: space-between;
`

const Modal = ({ showModal, setShowModal, type, order, setReRenderData, handleClose, showToastFromOut }) => {
    // Lấy admin từ redux
    const admin = useSelector((state) => state.admin.currentAdmin);

    // Hàm đóng mở modal
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

    // ========== Xử lý Xem chi tiết thú cưng ===============
    const handleCloseDetail = () => {
        setShowModal(prev => !prev);
    }

    // Các state khởi tạo
    const [orderModal, setOrderModal] = useState();
    const [orderStatusNameModal, setOrderStatusNameModal] = useState();
    const [orderIdModal, setOrderIdModal] = useState();
    const [orderDateModal, setOrderDateModal] = useState();
    const [employeeNameModal, setEmployeeNameModal] = useState();
    const [orderEmailModal, setOrderEmailModal] = useState();
    const [orderPhoneModal, setOrderPhoneModal] = useState();
    const [orderNameModal, setOrderNameModal] = useState();
    const [orderAddressModal, setOrderAddressModal] = useState();
    const [orderNoteModal, setOrderNoteModal] = useState();
    const [orderDetailModal, setOrderDetailModal] = useState([]);
    const [orderTotalModal, setOrderTotalModal] = useState();
    useEffect(() => {
        const getOrderDetail = async () => {
            try {
                const orderDetailRes = await axios.get(`http://localhost:8080/order/get-all-order-detail/${order.orderId}`);
                console.log("Kết quả chi tiết đơn hàng: ", orderDetailRes);
                setOrderModal(orderDetailRes.data);
                setOrderStatusNameModal(orderDetailRes.data[0].orderStatusName);
                setOrderIdModal(orderDetailRes.data[0].orderId);
                setOrderDateModal(orderDetailRes.data[0].orderDate.substring(0, 10));
                setEmployeeNameModal(orderDetailRes.data[0].employeeName);
                setOrderEmailModal(orderDetailRes.data[0].orderEmail);
                setOrderPhoneModal(orderDetailRes.data[0].orderPhone);
                setOrderNameModal(orderDetailRes.data[0].orderName);
                setOrderAddressModal(orderDetailRes.data[0].orderAddress + ", " + orderDetailRes.data[0].tenxa + ", " + orderDetailRes.data[0].tenquanhuyen + ", " + orderDetailRes.data[0].tenthanhpho);
                setOrderNoteModal(orderDetailRes.data[0].orderNote);
                setOrderTotalModal(format_money((orderDetailRes.data[0].orderTotal).toString()));
            } catch (err) {
                console.log("Lỗi lấy đơn đặt mua: ", err);
            }
        }
        const getPetOrderDetail = async () => {
            try {
                const petOrderDetailRes = await axios.get(`http://localhost:8080/order/get-all-pet/${order.orderId}`);
                console.log("Kết quả petOrderDetailRes: ", petOrderDetailRes);
                setOrderDetailModal(petOrderDetailRes.data);
            } catch (err) {
                console.log("Lỗi lấy chi tiết thú cưng đặt hàng: ", err);
            }
        }
        getPetOrderDetail();
        getOrderDetail();
    }, [order])
    console.log("orderDetailModal: ", orderDetailModal);

    // DUYỆT ĐƠN
    const AcceptOrder = async ({ orderStatusName, orderId, employeeId, employeeName, employeeAvatar }) => {
        console.log("Mã đặt hàng, mã nhân viên: ", orderStatusName, orderId, employeeId);
        if (orderStatusName === "Chờ xác nhận") {
            const AcceptOrderres = await axios.put("http://localhost:8080/order/accept-order", { orderId, employeeId, employeeName, employeeAvatar });
            if (AcceptOrderres.status === 200) {
                const dataShow = { message: "Duyệt đơn hàng có mã " + orderId + " thành công!", type: "success" };
                showToastFromOut(dataShow);
                setShowModal(prev => !prev);
                setReRenderData(prev => !prev);
                handleClose();  //Đóng thanh tìm kiếm
            } else {
                const dataShow = { message: "Có lỗi khi duyệt đơn đặt mua có mã " + orderId, type: "danger" };
                showToastFromOut(dataShow);
                setShowModal(prev => !prev);
                setReRenderData(prev => !prev);
                handleClose();  //Đóng thanh tìm kiếm
            }
        } else {
            const dataShow = { message: "Đơn đặt mua " + orderId + " đã được duyệt rồi ", type: "danger" };
            showToastFromOut(dataShow);
            setShowModal(prev => !prev);
            setReRenderData(prev => !prev);
            handleClose();  //Đóng thanh tìm kiếm
        }
    }

    // TỪ CHỐI ĐƠN
    const DenyOrder = async ({ orderStatusName, orderId, employeeId, employeeName, employeeAvatar }) => {
        const denyOrderRes = await axios.put("http://localhost:8080/order/deny-order", { orderId, employeeId, employeeName, employeeAvatar });
        if (denyOrderRes.status === 200) {
            const dataShow = { message: "Bạn đã từ chối đơn đặt mua có mã " + orderId, type: "success" };
            showToastFromOut(dataShow);
            setShowModal(prev => !prev);
            setReRenderData(prev => !prev);
            handleClose();  //Đóng thanh tìm kiếm
        } else {
            const dataShow = { message: "Có lỗi khi từ chối đơn đặt mua có mã " + orderId, type: "danger" };
            showToastFromOut(dataShow);
            setShowModal(prev => !prev);
            setReRenderData(prev => !prev);
            handleClose();  //Đóng thanh tìm kiếm
        }
    }
    // ================================================================
    //  =============== Xem chi tiết thú cưng ===============
    if (type === "detailOrder") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ChiTietWrapper showModal={showModal} style={{ flexDirection: `column` }}>
                            <H1>Chi tiết Đơn đặt mua</H1>
                            <ModalForm>
                                <div style={{ display: "flex", marginTop: "15px", flexDirection: "column" }}>
                                    <div style={{ display: "flex" }}>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Mã đặt hàng:</FormSpan>
                                            <FormInput type="text" value={orderIdModal} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Ngày đặt hàng:</FormSpan>
                                            <FormInput type="text" value={orderDateModal} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Trạng thái đơn đặt hàng:</FormSpan>
                                            <FormInput type="text" value={orderStatusNameModal} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Nhân viên duyệt đơn:</FormSpan>
                                            <FormInput type="text" value={employeeNameModal} readOnly />
                                        </ModalChiTietItem>
                                    </div>
                                    <div style={{ display: "flex" }}>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Email đặt mua:</FormSpan>
                                            <FormInput type="text" value={orderEmailModal} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Số điện thoại đặt mua:</FormSpan>
                                            <FormInput type="text" value={orderPhoneModal} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Họ tên:</FormSpan>
                                            <FormInput type="text" value={orderNameModal} readOnly />
                                        </ModalChiTietItem>
                                    </div>
                                    <div style={{ display: "flex" }}>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Địa chỉ:</FormSpan>
                                            <FormInput type="text" value={orderAddressModal} readOnly />
                                        </ModalChiTietItem>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Ghi chú:</FormSpan>
                                            <FormTextArea rows={1} value={orderNoteModal} readOnly />
                                        </ModalChiTietItem>
                                    </div>
                                    <div style={{ display: "flex" }}>
                                        <ModalChiTietItem style={{ flex: "1" }}>
                                            <FormSpan>Thú cưng đã được đặt mua:</FormSpan>
                                            <Table>
                                                <Thead>
                                                    <Tr>
                                                        <Th>Mã thú cưng</Th>
                                                        <Th>Tiêu đề</Th>
                                                        <Th>Tên thú cưng</Th>
                                                        <Th>Số lượng</Th>
                                                        <Th>Đơn giá</Th>
                                                        <Th>Tổng tiền</Th>
                                                    </Tr>
                                                </Thead>
                                                <Tbody>
                                                    {
                                                        orderDetailModal.length > 0
                                                            ?
                                                            orderDetailModal.map((pet, key) => {
                                                                return (
                                                                    <Tr>
                                                                        <Td>{pet.petId}</Td>
                                                                        <Td>{pet.petTitle}</Td>
                                                                        <Td>{pet.petName}</Td>
                                                                        <Td>{pet.orderDetailQuantity}</Td>
                                                                        <Td>{pet.petPriceDiscount ? format_money((pet.petPriceDiscount).toString()) : null}</Td>
                                                                        <Td>{pet.orderDetailTotal ? format_money((pet.orderDetailTotal).toString()) : null}</Td>
                                                                    </Tr>
                                                                );
                                                            })
                                                            : null
                                                    }
                                                </Tbody>
                                            </Table>
                                        </ModalChiTietItem>
                                    </div>
                                    <div style={{ display: "flex" }}>
                                        <div style={{ flex: "1" }}>

                                        </div>
                                        <Total style={{ flex: "1" }}>
                                            <TotalItem style={{ paddingBottom: "9px" }}>
                                                <p>Tổng tiền thú cưng</p>
                                                <p>{orderTotalModal} VNĐ</p>
                                            </TotalItem>
                                            <TotalItem style={{ paddingBottom: "9px" }}>
                                                <p>Phí vận chuyển</p>
                                                <p>0.00 VNĐ</p>
                                            </TotalItem>
                                            <TotalItem>
                                                <p style={{ color: "var(--color-primary)", fontWeight: "bold" }}>Tổng cộng</p>
                                                <p style={{ color: "var(--color-primary)", fontWeight: "bold" }}>{orderTotalModal} VNĐ</p>
                                            </TotalItem>
                                        </Total>
                                    </div>
                                </div>
                            </ModalForm>
                            <ButtonUpdate>

                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => {
                                            AcceptOrder({
                                                orderStatusName: orderStatusNameModal,
                                                orderId: orderIdModal,
                                                employeeId: admin ? admin.employeeId : null,
                                                employeeName: admin ? admin.employeeName : null,
                                                employeeAvatar: admin ? admin.employeeAvatar : null,
                                            });
                                        }}
                                    >Duyệt đơn này</ButtonClick>
                                </ButtonContainer>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => {
                                            DenyOrder({
                                                orderStatusName: orderStatusNameModal,
                                                orderId: orderIdModal,
                                                employeeId: admin ? admin.employeeId : null,
                                                employeeName: admin ? admin.employeeName : null,
                                                employeeAvatar: admin ? admin.employeeAvatar : null,
                                            });
                                        }}
                                    >Từ chối đơn này</ButtonClick>
                                </ButtonContainer>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={handleCloseDetail}
                                    >Đóng</ButtonClick>
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
};

export default Modal;