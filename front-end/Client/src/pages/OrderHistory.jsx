import { ClearOutlined, RemoveRedEyeOutlined } from "@material-ui/icons";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import styled from "styled-components"
import Announcement from "../components/Announcement";
import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import "../css/main.css";
import { useSelector } from 'react-redux';
import format_money from "../utils";
import ReactPaginate from "react-paginate";
import Modal from "../components/Modal";
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
flex-direction: column;
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

const ButtonInfo = styled.button`
    width: 40px;
    height: 30px;
    border: 2px solid var(--color-info);
    border-radius: var(--border-radius-2);
    color: var(--color-warnning);
    background: var(--color-white);
    padding:0px;
    outline:none;
    z-index: 2;
    cursor: pointer;
`
const RecentOrders = styled.div`
    // margin-top: 10px;
    padding: 2rem;
`
const H2 = styled.h2`
    margin-bottom: 0.8rem;
`

const ButtonDelete = styled.button`
width: 40px;
height: 30px;
border: 2px solid var(--color-danger);
border-radius: var(--border-radius-2);
color: var(--color-danger);
background: var(--color-white);
padding:0px;
outline:none;
z-index: 2;
cursor: pointer;
`
const OrderHistory = () => {
  // Lấy user từ Redux User
  const user = useSelector(state => state.user.currentUser);

  // Các state khởi tạo
  const [order, setOrder] = useState([]);
  const [reRenderData, setReRenderData] = useState(true);   // State để Compo KhachHangRight và KhachHangMain thay đổi Effect


  // Useeffect lấy data
  useEffect(() => {
    const getOrder = async () => {
      const orderRes = await axios.get(`http://localhost:8080/order/get-order-by-customer-id/${user.customerId}`);
      console.log("Lấy đơn hàng của user orderRes: ", orderRes);
      setOrder(orderRes.data);
    }
    getOrder();
  }, [reRenderData])

  // Modal
  const [showModal, setShowModal] = useState(false);
  const [typeModal, setTypeModal] = useState("")
  const [orderModal, setOrderModal] = useState(null);

  const openModal = (modal) => {
    setShowModal(prev => !prev);
    setTypeModal(modal.type);
    setOrderModal(modal.order);
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

  // PHÂN TRANG

  const [pageNumber, setPageNumber] = useState(0);

  const orderPerPage = 12;
  const pageVisited = pageNumber * orderPerPage;

  const orderChangePage = order
    .slice(pageVisited, pageVisited + orderPerPage)
    .map(orderItem => {
      return (
        <Tr>
          <Td>{orderItem.orderId}</Td>
          <Td>{orderItem.orderName}</Td>
          <Td>{orderItem.orderPhone}</Td>
          <Td>{orderItem.orderAddress}</Td>
          <Td>{orderItem.orderTotal ? format_money((orderItem.orderTotal).toString()) : null}</Td>
          {
            orderItem.orderStatusName === "Chờ xác nhận"
              ?
              <Td style={{ backgroundColor: "var(--color-warning)" }}>{orderItem.orderStatusName}</Td>
              :
              orderItem.orderStatusName === "Đang giao dịch"
                ?
                <Td style={{ backgroundColor: "var(--color-info)" }}>{orderItem.orderStatusName}</Td>
                :
                orderItem.orderStatusName === "Hoàn thành"
                  ?
                  <Td style={{ backgroundColor: "var(--color-success)" }}>{orderItem.orderStatusName}</Td>
                  :
                  orderItem.orderStatusName === "Đã hủy"
                    ?
                    <Td style={{ backgroundColor: "var(--color-danger)" }}>{orderItem.orderStatusName}</Td>
                    : null
          }
          <Td className="info">
            <ButtonInfo
              onClick={() => openModal({ type: "orderDetail", order: orderItem })}
            >
              <RemoveRedEyeOutlined />
            </ButtonInfo>
          </Td>
          <Td className="danger">
            <ButtonDelete
              onClick={() => openModal({ type: "cancelOrder", order: orderItem  })}
            >
              <ClearOutlined />
            </ButtonDelete>
          </Td>
        </Tr>
      );
    }
    );


  const pageCount = Math.ceil(order.length / orderPerPage);
  const changePage = ({ selected }) => {
    setPageNumber(selected);
  }

  return (
    <Container>
      <Navbar />
      <Announcement />
      <Wrapper>
        <RecentOrders>
          <H2>Lịch sử đặt mua</H2>
          <Table>
            <Thead>
              <Tr>
                <Th>Mã đơn hàng</Th>
                <Th>Họ tên</Th>
                <Th>Số điện thoại</Th>
                <Th>Địa chỉ</Th>
                <Th>Tổng tiền</Th>
                <Th>Trạng thái đơn hàng</Th>
                <Th>Chi tiết</Th>
                <Th>Hủy đơn</Th>
              </Tr>
            </Thead>
            <Tbody>
              {
                order !== null
                  ?
                  orderChangePage
                  :
                  (order.slice(0, 12).map(orderItem => (
                    <Tr>
                      <Td>{orderItem.orderId}</Td>
                      <Td>{orderItem.orderName}</Td>
                      <Td>{orderItem.orderPhone}</Td>
                      <Td>{orderItem.orderAddress}</Td>
                      <Td>{orderItem.orderTotal ? format_money((orderItem.orderTotal).toString()) : null}</Td>
                      {
                        orderItem.orderStatusName === "Chờ xác nhận"
                          ?
                          <Td style={{ backgroundColor: "var(--color-warning)" }}>{orderItem.orderStatusName}</Td>
                          :
                          orderItem.orderStatusName === "Đang giao dịch"
                            ?
                            <Td style={{ backgroundColor: "var(--color-info)" }}>{orderItem.orderStatusName}</Td>
                            :
                            orderItem.orderStatusName === "Hoàn thành"
                              ?
                              <Td style={{ backgroundColor: "var(--color-success)" }}>{orderItem.orderStatusName}</Td>
                              :
                              orderItem.orderStatusName === "Đã hủy"
                                ?
                                <Td style={{ backgroundColor: "var(--color-danger)" }}>{orderItem.orderStatusName}</Td>
                                : null
                      }
                      <Td className="info">
                        <ButtonInfo
                          onClick={() => openModal({ type: "orderDetail", order: orderItem })}
                        >
                          <RemoveRedEyeOutlined />
                        </ButtonInfo>
                      </Td>
                      <Td className="danger">
                        <ButtonDelete
                          onClick={() => openModal({ type: "cancelOrder", order: orderItem  })}
                        >
                          <ClearOutlined />
                        </ButtonDelete>
                      </Td>
                    </Tr>
                  )))
              }
            </Tbody>
          </Table>
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
            forcePage={pageNumber}
          />
        </RecentOrders>
        <Modal
          showModal={showModal}   //state Đóng mở modal
          setShowModal={setShowModal} //Hàm Đóng mở modal
          type={typeModal}    //Loại modal
          order={orderModal}  //Dữ liệu bên trong modal
          setReRenderData={setReRenderData}   //Hàm rerender khi dữ liệu thay đổi
          // handleClose={handleClose}   //Đóng tìm kiếm
          showToastFromOut={showToastFromOut} //Hàm hiện toast
        />

        {/* === TOAST === */}
        <Toast
          ref={toastRef}
          dataToast={dataToast}   // Thông tin cần hiện lên: Đối tượng { message,type }
        />
      </Wrapper>
      <Footer />
    </Container>
  );
};

export default OrderHistory