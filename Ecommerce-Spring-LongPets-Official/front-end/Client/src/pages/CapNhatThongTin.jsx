import axios from 'axios';
import { useEffect, useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import Announcement from '../components/Announcement';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import { getStorage, ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import app from "../firebase";
import "../css/main.css";
import { Link } from 'react-router-dom';
import Toast from "../components/Toast";
import { updateInfo } from "../redux/userRedux";


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
flex: 1;
`

const Box2 = styled.div`
width: 100%;
padding: 10px 40px;
flex: 1;
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
margin-top: 15px;
display: flex;
flex-direction: row;
justify-content: space-between;
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

const Avatar = styled.img`
    width: 100%;
    max-height: 400px;
    overflow: hidden;
    object-fit: contain;
`

const CapNhatThongTin = () => {
  // User từ redux
  const user = useSelector(state => state.user.currentUser);
  const dispatch = useDispatch(); //Để gọi hàm từ redux updateInfo


  // ===== TOAST =====
  const [dataToast, setDataToast] = useState({ message: "alo alo", type: "success" });
  const toastRef = useRef(null);  // useRef có thể gọi các hàm bên trong của Toast
  // bằng các dom event, javascript, ...

  const showToastFromOut = (dataShow) => {
    console.log("showToastFromOut da chay", dataShow);
    setDataToast(dataShow);
    toastRef.current.show();
  }

  // Các state khởi tạo
  const [wardId, setWardId] = useState("");
  const [customerId, setCustomerId] = useState("");
  const [customerName, setCustomerName] = useState("");
  const [customerBirthday, setCustomerBirthday] = useState("");
  const [customerGender, setCustomerGender] = useState("");
  const [customerPhone, setCustomerPhone] = useState("");
  const [customerAddress, setCustomerAddress] = useState("");
  const [customerAvatar, setCustomerAvatar] = useState("");
  const [customerAvatarChange, setCustomerAvatarChange] = useState("");

  const [districtId, setDistrictId] = useState("");
  const [cityId, setCityId] = useState("");
  const [wardName, setWardName] = useState("");
  const [districtName, setDistrictName] = useState("");
  const [cityName, setCityName] = useState("");

  useEffect(() => {
    setCustomerId(user.customerId);
    setCustomerName(user.customerName);
    setCustomerBirthday(user.customerBirthday);
    setCustomerGender(user.customerGender);
    setCustomerPhone(user.customerPhone);
    setCustomerAddress(user.customerAddress);
    setCustomerAvatar(user.customerAvatar);
    setWardId(user.wardId);
    setDistrictId(user.districtId);
    setCityId(user.cityId);
    setWardName(user.wardName);
    setDistrictName(user.districtName);
    setCityName(user.cityName);
  }, [])

  // Effect Tỉnh - Huyện - Xã
  const [cityArray, setCityArray] = useState([]);
  const [districtArray, setDistrictArray] = useState([]);
  const [wardArray, setWardArray] = useState([]);
  useEffect(() => {
    const getCity = async () => {
      const citiRes = await axios.get("http://localhost:8080/city");
      setCityArray(citiRes.data);
      console.log("Tỉnh TP [res]: ", citiRes.data);
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

  // Thay đổi hình ảnh
  const handleChangeImg = (newImage) => {
    setCustomerAvatarChange("");
    const imageUnique = new Date().getTime() + newImage;
    const storage = getStorage(app);
    const storageRef = ref(storage, imageUnique);
    const uploadTask = uploadBytesResumable(storageRef, newImage);

    // Listen for state changes, errors, and completion of the upload.
    uploadTask.on('state_changed',
      (snapshot) => {
        // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
        const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        console.log('Upload is ' + progress + '% done');
        switch (snapshot.state) {
          case 'paused':
            console.log('Upload is paused');
            break;
          case 'running':
            console.log('Upload is running');
            break;
          default:
        }
      },
      (error) => {
        // A full list of error codes is available at
        // https://firebase.google.com/docs/storage/web/handle-errors
        switch (error.code) {
          case 'storage/unauthorized':
            // User doesn't have permission to access the object
            break;
          case 'storage/canceled':
            // User canceled the upload
            break;

          // ...

          case 'storage/unknown':
            // Unknown error occurred, inspect error.serverResponse
            break;
        }
      },
      () => {
        // Upload completed successfully, now we can get the download URL
        getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
          console.log('File available at', downloadURL);
          try {
            setCustomerAvatarChange(downloadURL);
          } catch (err) {
            console.log("Lỗi cập nhật hình ảnh:", err);
          }
        });
      }
    );
  }

  // XỬ LÝ CẬP NHẬT
  const CapNhatThongTin = async ({
    customerId,
    wardId,
    customerName,
    customerBirthday,
    customerGender,
    customerPhone,
    customerAddress,
    customerAvatar,
    customerAvatarchange,
  }) => {
    console.log("Đầu vào: ", {
      customerId,
      wardId,
      customerName,
      customerBirthday,
      customerGender,
      customerPhone,
      customerAddress,
      customerAvatar,
      customerAvatarchange,
    });

    if (customerId != ""
      && wardId != ""
      && customerName != ""
      && customerBirthday != ""
      && customerGender != ""
      && customerPhone != ""
      && customerAddress != ""
      // && customerAvatar != ""
      // && customerAvatarchange != ""
    ) {
      try {
        const checkPhoneRes = await axios.get(`http://localhost:8080/customer/check-customer-phone/${customerPhone}-${customerId}`);
        if (checkPhoneRes.data.message == "Unregistered phone number") {
          try {
            if (customerAvatarchange != "") {
              const updateCustomerRes = await axios.put("http://localhost:8080/customer/update-customer", { wardId: wardId, customerName: customerName, customerBirthday: customerBirthday, customerGender: customerGender, customerPhone: customerPhone, customerAddress: customerAddress, customerAvatar: customerAvatarchange, customerId: customerId });
              console.log("KQ trả về update: ", updateCustomerRes);
              if (updateCustomerRes.status == 200) {
                try {
                  const cutomerAfterUpdateRes = await axios.get(`http://localhost:8080/customer/find-customer/${customerId}`);
                  console.log("cutomerAfterUpdateRes: ", cutomerAfterUpdateRes);
                  dispatch(updateInfo(cutomerAfterUpdateRes.data));
                } catch (err) {
                  console.log("Lỗi khi lấy user sau khi update: ", err);
                }
                const dataShow = { message: "Cập nhật thông tin thành công!", type: "success" };
                showToastFromOut(dataShow);
                setCustomerAvatarChange("");
              } else {
                const dataShow = { message: "Cập nhật thông tin thất bại!", type: "danger" };
                showToastFromOut(dataShow);
                setCustomerAvatarChange("");
              }
            } else {
              const updateCustomerRes = await axios.put("http://localhost:8080/customer/update-customer", { wardId: wardId, customerName: customerName, customerBirthday: customerBirthday, customerGender: customerGender, customerPhone: customerPhone, customerAddress: customerAddress, customerAvatar: customerAvatar, customerId: customerId });
              console.log("KQ trả về update: ", updateCustomerRes);
              if (updateCustomerRes.status === 200) {
                try {
                  const cutomerAfterUpdateRes = await axios.get(`http://localhost:8080/customer/find-customer/${customerId}`);
                  console.log("cutomerAfterUpdateRes: ", cutomerAfterUpdateRes);
                  dispatch(updateInfo(cutomerAfterUpdateRes.data));
                } catch (err) {
                  console.log("Lỗi khi lấy user sau khi update: ", err);
                }
                const dataShow = { message: "Cập nhật thông tin thành công!", type: "success" };
                showToastFromOut(dataShow);
                setCustomerAvatarChange("");
              } else {
                const dataShow = { message: "Cập nhật thông tin thất bại!", type: "danger" };
                showToastFromOut(dataShow);
                setCustomerAvatarChange("");
              }
            }
          } catch (err) {
            const dataShow = { message: "Thất bại! Có lỗi khi cập nhật ", type: "danger" };
            showToastFromOut(dataShow);
          }
        } else {
          const dataShow = { message: "Số điện thoại này đã được đăng ký", type: "danger" };
          showToastFromOut(dataShow); //Hiện toast thông báo
        }
      } catch (err) {
        console.log("Lỗi khi bắt Số điện thoại trùng!");
      }
    } else {
      const dataShow = { message: "Thông tin của bạn còn thiếu! Hãy kiểm tra lại", type: "danger" };
      showToastFromOut(dataShow); //Hiện toast thông báo
    }
  }

  console.log("User: ", user);
  return (
    <Container>
      <Navbar />
      <Announcement />
      <Wrapper>
        <Box1>
          <Title1>
            <p style={{ fontSize: "1.2rem", fontWeight: "bold" }}>Cập nhật thông tin cá nhân</p>
          </Title1>
          {
            customerAvatarChange != ""   //Khi mảng hình có hình thì hiện các hình trong mảng
              ?
              <Avatar src={customerAvatarChange} />
              :   //Khi mảng hình trống thì hiện No Available Image
              customerAvatar != ""
                ?
                <Avatar src={customerAvatar} />
                :
                <Avatar src="https://firebasestorage.googleapis.com/v0/b/longpets-50c17.appspot.com/o/1651671554060%5Bobject%20File%5D?alt=media&token=a08b5233-bc37-491a-8211-b33cb58ae0c3" />
          }
          <p style={{ fontWeight: "500", marginTop: "10px" }}>Hình đại diện của bạn:</p>
          <FormInput type="file" style={{ width: "100%", marginTop: "0px" }} onChange={(e) => handleChangeImg(e.target.files[0])} />
        </Box1>
        <Box2>
          <InfomationTitle>
            <p style={{ fontWeight: "bold", margin: "10px 0 0 0" }}>Thông tin chi tiết</p>
            <p style={{ fontSize: "1rem" }}>Hoàn tất cập nhật bằng việc cung cấp những thông tin sau</p>
          </InfomationTitle>
          <InfomationForm>
            <ModalChiTietItem>
              <FormSpan>Địa chỉ email:</FormSpan>
              <FormInput type="text" value={user ? user.customerEmail : null} disabled />
            </ModalChiTietItem>
            <ModalChiTietItem>
              <FormSpan>Họ tên:</FormSpan>
              <FormInput type="text" onChange={(e) => { setCustomerName(e.target.value) }} value={customerName} placeholder="Họ tên của bạn là" />
            </ModalChiTietItem>
            <ModalChiTietItem>
              <FormSpan>Giới tính:</FormSpan>
              <FormSelect onChange={(e) => { setCustomerGender(e.target.value) }}>
                {
                  customerGender === "Nam"
                    ?
                    <>
                      <FormOption value="Nam" selected> Nam </FormOption>
                      <FormOption value="Nữ"> Nữ </FormOption>
                    </>
                    :
                    customerGender === "Nữ"
                      ?
                      <>
                        <FormOption value="Nam"> Nam </FormOption>
                        <FormOption value="Nữ" selected> Nữ </FormOption>
                      </>
                      :
                      <>
                        <FormOption value="Nam"> Nam </FormOption>
                        <FormOption value="Nữ"> Nữ </FormOption>
                      </>
                }
              </FormSelect>
            </ModalChiTietItem>
            <ModalChiTietItem>
              <FormSpan>Ngày sinh:</FormSpan>
              <FormInput type="date" onChange={(e) => setCustomerBirthday(e.target.value)} value={customerBirthday} />
            </ModalChiTietItem>
            <ModalChiTietItem>
              <FormSpan>Số điện thoại:</FormSpan>
              <FormInput type="text" onChange={(e) => setCustomerPhone(e.target.value)} value={customerPhone} placeholder="Số điện thoại của bạn là" />
            </ModalChiTietItem>
            <ModalChiTietItem>
              <FormSpan>Địa chỉ:</FormSpan>
              <FormInput type="text" onChange={(e) => setCustomerAddress(e.target.value)} value={customerAddress} placeholder="Địa chỉ của bạn là" />
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
          </InfomationForm>
          <Total>
            <ButtonContainer>
              <Button
                onClick={() => {
                  CapNhatThongTin({
                    customerId: customerId,
                    wardId: wardId,
                    customerName: customerName,
                    customerBirthday: customerBirthday,
                    customerGender: customerGender,
                    customerPhone: customerPhone,
                    customerAddress: customerAddress,
                    customerAvatar: customerAvatar,
                    customerAvatarchange: customerAvatarChange,
                  })
                }}
              >Cập nhật
              </Button>
            </ButtonContainer>
            <Link to="/">
              <ButtonContainer>
                <Button>Trở lại</Button>
              </ButtonContainer>
            </Link>
          </Total>
        </Box2>
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

export default CapNhatThongTin;
