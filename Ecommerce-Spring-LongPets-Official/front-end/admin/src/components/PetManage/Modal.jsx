import format_money from "../../utils";
import styled from "styled-components";
import { CloseOutlined } from "@mui/icons-material";
import { useCallback, useEffect, useRef, useState } from "react";
import "../../css/main.css";
import axios from "axios";
import { getStorage, ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import app from "../../firebase";

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

const Modal = ({ showModal, setShowModal, type, pet, setReRenderData, handleClose, showToastFromOut }) => {
    const modalRef = useRef();
    const closeModal = (e) => {
        if (modalRef.current === e.target) {
            setShowModal(false);
            // setPetModalPetImage([]); //Modal chi tiết thú cưng khi tắt sẽ xóa mảng hình
            setNewImage([]);  //Modal thêm thú cưng khi tắt sẽ xóa mảng hình
        }
    }

    const keyPress = useCallback(
        (e) => {
            if (e.key === 'Escape' && showModal) {
                setShowModal(false);
                // setPetModalPetImage([]); //Modal chi tiết thú cưng khi tắt sẽ xóa mảng hình
                setNewImage([]);  //Modal thêm thú cưng khi tắt sẽ xóa mảng hình
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

    // =============== Xử lý cập nhật thú cưng ===============
    const handleUpdatePet = async (
        {
            petId,
            newCategoryId,
            newPetName,
            newPetGender,
            newPetAge,
            newPetVaccinated,
            newPetHealthWarranty,
            newPetTitle,
            newPetDescription,
            newPetNote,
            newPetQuantity,
            newPetPrice,
            newPetPriceDiscount,
            petModalImageChange,
            petModalImage
        }
    ) => {
        console.log("Dau vao:", { petId, newCategoryId, newPetName, newPetGender, newPetAge, newPetVaccinated, newPetHealthWarranty, newPetTitle, newPetDescription, newPetNote, newPetQuantity, newPetPrice, newPetPriceDiscount, petModalImageChange, petModalImage });

        if (newCategoryId !== ""
            && newPetName !== ""
            && newPetGender !== ""
            && newPetAge !== ""
            // && newPetVaccinated !== ""
            // && newPetHealthWarranty !== ""
            && newPetTitle !== ""
            && newPetDescription !== ""
            && newPetNote !== ""
            && newPetQuantity !== ""
            && newPetPrice !== ""
            && newPetPriceDiscount !== ""
            // && newImage !== ""
        ) {
            try {
                // setPetModalImageChange([]);
                if(petModalImageChange.length > 0) {
                    const updatePetRes = await axios.put("http://localhost:8080/pet/update-pet", { petId, categoryId: newCategoryId, petName: newPetName, petGender: newPetGender, petAge: newPetAge, petVaccinated: newPetVaccinated, petHealthWarranty: newPetHealthWarranty, petTitle: newPetTitle, petDescription: newPetDescription, petNote: newPetNote, petQuantity: newPetQuantity, petPrice: newPetPrice, petPriceDiscount: newPetPriceDiscount, petImage: petModalImageChange });
                    console.log("KQ trả về update: ", updatePetRes);
                    setReRenderData(prev => !prev); //Render lại csdl ở Compo cha là - petMain & petRight.jsx
                    setShowModal(prev => !prev);
                    handleClose();
                    const dataShow = { message: "Thay đổi thú cưng có mã " + petId + " thành công!", type: "success" };
                    showToastFromOut(dataShow);
                    setPetModalImageChange([]);
                } else {
                    const updatePetRes = await axios.put("http://localhost:8080/pet/update-pet", { petId, categoryId: newCategoryId, petName: newPetName, petGender: newPetGender, petAge: newPetAge, petVaccinated: newPetVaccinated, petHealthWarranty: newPetHealthWarranty, petTitle: newPetTitle, petDescription: newPetDescription, petNote: newPetNote, petQuantity: newPetQuantity, petPrice: newPetPrice, petPriceDiscount: newPetPriceDiscount, petImage: petModalImage });
                    console.log("KQ trả về update: ", updatePetRes);
                    setReRenderData(prev => !prev); //Render lại csdl ở Compo cha là - petMain & petRight.jsx
                    setShowModal(prev => !prev);
                    handleClose();
                    const dataShow = { message: "Thay đổi thú cưng có mã " + petId + " thành công!", type: "success" };
                    showToastFromOut(dataShow);
                    // setPetModalPetImage([]);
                    setIsUpdate(prev => !prev);
                }
            } catch (err) {
                setReRenderData(prev => !prev); //Render lại csdl ở Compo cha là - petMain & petRight.jsx
                setShowModal(prev => !prev);
                handleClose();
                const dataShow = { message: "Thất bại! Không thể cập nhật thú cưng có mã " + petId, type: "danger" };
                showToastFromOut(dataShow);
            }
        }
    }
    //  test
    const [petModal, setPetModal] = useState();
    const [petModalCategoryId, setPetModalCategoryId] = useState();
    const [petModalPetName, setPetModalPetName] = useState();
    const [petModalPetGender, setPetModalPetGender] = useState();
    const [petModalPetAge, setPetModalPetAge] = useState();
    const [petModalPetVaccinated, setPetModalPetVaccinated] = useState();
    const [petModalPetHealthWarranty, setPetModalPetHealthWarranty] = useState();
    const [petModalPetTitle, setPetModalPetTitle] = useState();
    const [petModalPetDescription, setPetModalPetDescription] = useState();
    const [petModalPetNote, setPetModalPetNote] = useState();
    const [petModalPetQuantity, setPetModalPetQuantity] = useState();
    const [petModalPetPrice, setPetModalPetPrice] = useState();
    const [petModalPetPriceDiscount, setPetModalPetPriceDiscount] = useState();
    const [petModalImage, setPetModalPetImage] = useState([]);
    const [petModalImageChange, setPetModalImageChange] = useState([]);

    const [petModalCategoryIdOld, setPetModalCategoryIdOld] = useState();
    const [petModalPetNameOld, setPetModalPetNameOld] = useState();
    const [petModalPetGenderOld, setPetModalPetGenderOld] = useState();
    const [petModalPetAgeOld, setPetModalPetAgeOld] = useState();
    const [petModalPetVaccinatedOld, setPetModalPetVaccinatedOld] = useState();
    const [petModalPetHealthWarrantyOld, setPetModalPetHealthWarrantyOld] = useState();
    const [petModalPetTitleOld, setPetModalPetTitleOld] = useState();
    const [petModalPetDescriptionOld, setPetModalPetDescriptionOld] = useState();
    const [petModalPetNoteOld, setPetModalPetNoteOld] = useState();
    const [petModalPetQuantityOld, setPetModalPetQuantityOld] = useState();
    const [petModalPetPriceOld, setPetModalPetPriceOld] = useState();
    const [petModalPetPriceDiscountOld, setPetModalPetPriceDiscountOld] = useState();
    const [petModalImageOld, setPetModalPetImageOld] = useState([]);
    useEffect(() => {
        // setPetModalPetImage([]);
        // setPetModalImageChange([]);
        setNewImage([]);
        const getPet = async () => {
            try {
                const petRes = await axios.get(`http://localhost:8080/pet/get-pet-by-pet-id/${pet.petId}`);
                setPetModal(petRes.data);
                setPetModalCategoryId(petRes.data[0].categoryId);
                setPetModalPetName(petRes.data[0].petName);
                setPetModalPetGender(petRes.data[0].petGender);
                setPetModalPetAge(petRes.data[0].petAge);
                setPetModalPetVaccinated(petRes.data[0].petVaccinated);
                setPetModalPetHealthWarranty(petRes.data[0].petHealthWarranty);
                setPetModalPetTitle(petRes.data[0].petTitle);
                setPetModalPetDescription(petRes.data[0].petDescription);
                setPetModalPetNote(petRes.data[0].petNote);
                setPetModalPetQuantity(petRes.data[0].petQuantity);
                setPetModalPetPrice(petRes.data[0].petPrice);
                setPetModalPetPriceDiscount(petRes.data[0].petPriceDiscount);

                setPetModalCategoryIdOld(petRes.data[0].categoryId);
                setPetModalPetNameOld(petRes.data[0].petName);
                setPetModalPetGenderOld(petRes.data[0].petGender);
                setPetModalPetAgeOld(petRes.data[0].petAge);
                setPetModalPetVaccinatedOld(petRes.data[0].petVaccinated);
                setPetModalPetHealthWarrantyOld(petRes.data[0].petHealthWarranty);
                setPetModalPetTitleOld(petRes.data[0].petTitle);
                setPetModalPetDescriptionOld(petRes.data[0].petDescription);
                setPetModalPetNoteOld(petRes.data[0].petNote);
                setPetModalPetQuantityOld(petRes.data[0].petQuantity);
                setPetModalPetPriceOld(petRes.data[0].petPrice);
                setPetModalPetPriceDiscountOld(petRes.data[0].petPriceDiscount);
            } catch (err) {
                console.log("Lỗi lấy danh mục: ", err);
            }
        }
        const getImage = async () => {
            try {
                setPetModalPetImage([]);
                const imageRes = await axios.get(`http://localhost:8080/pet/get-all-pet-image/${pet.petId}`);
                imageRes.data.map((pet, index) => {
                    setPetModalPetImage(prev => {
                        const isHave = petModalImage.includes(pet.imageContent);
                        if (isHave) {
                            return [...prev];
                        } else {
                            return [...prev, pet.imageContent];
                        }
                    });
                    // setPetModalImageChange(prev => {
                    //     const isHave = petModalImageChange.includes(pet.hinhanh);
                    //     if (isHave) {
                    //         return [...prev];
                    //     } else {
                    //         return [...prev, pet.hinhanh];
                    //     }
                    // });
                    setPetModalPetImageOld(prev => {
                        const isHave = petModalImageOld.includes(pet.imageContent);
                        if (isHave) {
                            return [...prev];
                        } else {
                            return [...prev, pet.imageContent];
                        }
                    });
                })
            } catch (err) {
                console.log("Lỗi lấy hình ảnh thú cưng: ", err);
            }
        }
        getPet();
        getImage();
    }, [pet]);
    console.log("Thú cưng modal: ", petModal);

    const [isUpdate, setIsUpdate] = useState(true);
    useEffect(() => {
        const getImageUpdate = async () => {
            try {
                const imageRes = await axios.get(`http://localhost:8080/pet/get-all-pet-image/${pet.petId}`);
                setPetModalPetImage(imageRes.data);
            } catch (err) {
                console.log("Lỗi lấy hình ảnh thú cưng: ", err);
            }
        }
        getImageUpdate();
    },[isUpdate])
    // Thay đổi hình ảnh
    const handleChangeImage = (newImageArray) => {
        setPetModalImageChange([]);
        for (let i = 0; i < newImageArray.length; i++) {
            // console.log("hinh moi: ", newImageArray[i]);
            const uniqueImage = new Date().getTime() + newImageArray[i].name;
            const storage = getStorage(app);
            const storageRef = ref(storage, uniqueImage);
            const uploadTask = uploadBytesResumable(storageRef, newImageArray[i]);

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
                            setPetModalImageChange(prev => [...prev, downloadURL]);
                        } catch (err) {
                            console.log("Lỗi cập nhật hình ảnh:", err);
                        }
                    });
                }
            );
        }
    }

    const handleCloseUpdate = () => {
        // Set lại giá trị cũ sau khi đóng Modal
        // setPetModalPetImage(petModalImageOld);
        setPetModalCategoryId(petModalCategoryIdOld);
        setPetModalPetName(petModalPetNameOld);
        setPetModalPetGender(petModalPetGenderOld);
        setPetModalPetAge(petModalPetAgeOld);
        setPetModalPetVaccinated(petModalPetVaccinatedOld);
        setPetModalPetHealthWarranty(petModalPetHealthWarrantyOld);
        setPetModalPetTitle(petModalPetTitleOld);
        setPetModalPetDescription(petModalPetDescriptionOld);
        setPetModalPetNote(petModalCategoryIdOld);
        setPetModalPetQuantity(petModalPetQuantityOld);
        setPetModalPetPrice(petModalPetPriceOld);
        setPetModalPetPriceDiscount(petModalCategoryIdOld);

        setShowModal(prev => !prev);
        // setNewImage([]);  //Đóng modal sẽ xóa mảng hình cũ ở Modal Thêm thú cưng
        // setPetModalImageChange([]);
    }
    

    // Checkbox - Update thú cưng - Đã tiêm chủng - Được check
    const handleCheckboxPetVaccinatedUpdate = (e) => {
        if (e.target.checked) { //Khi checked
            setPetModalPetVaccinated(e.target.value);
        } else {
            setPetModalPetVaccinated("");
        }
    }

    // Checkbox - Update thú cưng - Bảo hành sức khỏe - Được check
    const handleCheckboxPetHealthWarrantyUpdate = (e) => {
        if (e.target.checked) { //Khi checked
            setPetModalPetHealthWarranty(e.target.value);
        } else {
            setPetModalPetHealthWarranty("");
        }
    }

    console.log("Đã tiêm chủng: ", petModalPetVaccinated);
    console.log("Được bảo hành sức khỏe: ", petModalPetHealthWarranty);
    console.log("petModalImage: ", petModalImage);
    console.log("petModalImagechange: ", petModalImageChange);

    // =============== Xử lý thêm thú cưng ===============
    const [newCategoryId, setNewCategoryId] = useState("1");  //Danh mục mặc định là Chó
    const [newPetName, setNewPetName] = useState("");
    const [newPetGender, setNewPetGender] = useState("Đực");    //Giới tính mặc định là "Đực"
    const [newPetAge, setNewPetAge] = useState("");
    const [newPetVaccinated, setNewPetVaccinated] = useState("");
    const [newPetHealthWarranty, setNewPetHealthWarranty] = useState("");
    const [newPetTitle, setNewPetTitle] = useState("");
    const [newPetDescription, setNewPetDescription] = useState("");
    const [newPetNote, setNewPetNote] = useState("");
    const [newPetQuantity, setNewPetQuantity] = useState("");
    const [newPetPrice, setNewPetPrice] = useState("");
    const [newPetPriceDiscount, setNewPetPriceDiscount] = useState("");
    const [newImage, setNewImage] = useState([]);   //Mảng chứa hình ảnh

    // Thay đổi hình ảnh
    const handleShowImg = (newImageArray) => {
        // Chạy vòng lặp thêm từng hình trong mảng lên firebase rồi lưu vô mảng [newImage] ở modal Thêm thú cưng
        setNewImage([]);
        for (let i = 0; i < newImageArray.length; i++) {
            // console.log("hinh moi: ", newImageArray[i]);
            const uniqueImage = new Date().getTime() + newImageArray[i].name;
            const storage = getStorage(app);
            const storageRef = ref(storage, uniqueImage);
            const uploadTask = uploadBytesResumable(storageRef, newImageArray[i]);

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
                            setNewImage(prev => [...prev, downloadURL]);
                            console.log("Up thành công 1 hình: ", downloadURL);
                        } catch (err) {
                            console.log("Lỗi show hình ảnh:", err);
                        }
                    });
                }
            );
        }
        console.log("mang hinh: ", newImageArray);
    }

    const handleThempet = async (
        { newCategoryId,
            newPetName,
            newPetGender,
            newPetAge,
            newPetVaccinated,
            newPetHealthWarranty,
            newPetTitle,
            newPetDescription,
            newPetNote,
            newPetQuantity,
            newPetPrice,
            newPetPriceDiscount,
            newImage  //Mảng hình nhe
        }) => {
        console.log("Thu Cung duoc them moi: ",{
            newCategoryId,
            newPetName,
            newPetGender,
            newPetAge,
            newPetVaccinated,
            newPetHealthWarranty,
            newPetTitle,
            newPetDescription,
            newPetNote,
            newPetQuantity,
            newPetPrice,
            newPetPriceDiscount,
            newImage  //Mảng hình nhe
        });
        if (newCategoryId !== ""
            && newPetName !== ""
            && newPetGender !== ""
            && newPetAge !== ""
            // && newPetVaccinated !== ""
            // && newPetHealthWarranty !== ""
            && newPetTitle !== ""
            && newPetDescription !== ""
            && newPetNote !== ""
            && newPetQuantity >= 1
            && newPetPrice >= 0
            && newPetPriceDiscount >= 0
            && newImage.length > 0
        ) {
            try {
                const insertPetRes = axios.post("http://localhost:8080/pet/add-new-pet",
                    {
                        categoryId: newCategoryId,
                        petName: newPetName,
                        petGender: newPetGender,
                        petAge: newPetAge,
                        petVaccinated: newPetVaccinated,
                        petHealthWarranty: newPetHealthWarranty,
                        petTitle: newPetTitle,
                        petDescription: newPetDescription,
                        petNote: newPetNote,
                        petQuantity: newPetQuantity,
                        petPrice: newPetPrice,
                        petPriceDiscount: newPetPriceDiscount,
                        petImage: newImage
                    }
                );
                console.log("KQ trả về update: ", insertPetRes);
                setReRenderData(prev => !prev); //Render lại csdl ở Compo cha là - categoryMain & categoryRight.jsx
                setShowModal(prev => !prev);
                const dataShow = { message: "Thêm thú cưng " + newPetName + " thành công!", type: "success" };
                showToastFromOut(dataShow);
                setNewImage([]);  //Làm rỗng mảng hình
            } catch (err) {
                console.log("Lỗi insert: ", err);
                setReRenderData(prev => !prev); //Render lại csdl ở Compo cha là - categoryMain & categoryRight.jsx
                setShowModal(prev => !prev);
                const dataShow = { message: "Đã có lỗi khi thêm thú cưng " + newPetName, type: "danger" };
                showToastFromOut(dataShow); //Hiện toast thông báo
            }
        } else {
            const dataShow = { message: "Bạn chưa nhập thông tin cho thú cưng", type: "danger" };
            showToastFromOut(dataShow); //Hiện toast thông báo
        }
    }

    // State chứa mảng danh mục - Lấy về danh mục để hiện select-option
    const [category, setCategory] = useState([]);
    useEffect(() => {
        const getCategory = async () => {
            try {
                const categoryRes = await axios.get("http://localhost:8080/category");
                setCategory(categoryRes.data);
                console.log("Mảng danh mục: ", category);
            } catch (err) {
                console.log("Lỗi lấy danh mục: ", err);
            }
        }
        getCategory();
    }, [pet])
    // Checkbox - Thêm thú cưng - Đã tiêm chủng
    const [vaccinated, setVaccinated] = useState(false);
    const handleCheckboxvaccinated = (value) => {
        setVaccinated(!vaccinated);
        if (!vaccinated) { //Khi checked
            setNewPetVaccinated(value);
        } else {
            setNewPetVaccinated("");
        }
    }
    // Checkbox - Thêm thú cưng - Bảo hành sức khỏe
    const [warranty, setWarranty] = useState(false);
    const handleCheckboxwarranty = (value) => {
        setWarranty(!warranty);
        if (!warranty) { //Khi checked
            setNewPetHealthWarranty(value);
        } else {
            setNewPetHealthWarranty("");
        }
    }

    // =============== Xử lý xóa thú cưng ===============
    const handleDeletePet = async ({ petId }) => {
        if (petId !== "") {
            try {
                const deletepetres = await axios.delete(`http://localhost:8080/pet/delete-pet/${petId}`);
                console.log("KQ trả về delete: ", deletepetres);
                setReRenderData(prev => !prev); //Render lại csdl ở Compo cha là - categoryMain & categoryRight.jsx
                setShowModal(prev => !prev);
                handleClose();  //Đóng thanh tìm kiếm
                const dataShow = { message: "Đã xóa thú cưng mã " + petId + " thành công!", type: "success" };
                showToastFromOut(dataShow);
            } catch (err) {
                console.log("Lỗi Delete thú cưng err: ", err);
            }
        }
    }

    // =============== Xử lý Xem chi tiết thú cưng ===============
    const handleCloseDetail = () => {
        setShowModal(prev => !prev);
        setNewImage([]);  //Đóng modal sẽ xóa mảng hình cũ ở Modal Thêm thú cưng
    }
    console.log("Giới tính: ", newCategoryId);
    // ================================================================
    //  =============== Xem chi tiết thú cưng ===============
    if (type === "detailPet") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ChiTietWrapper showModal={showModal} style={{ flexDirection: `column` }}>
                            <H1>Chi tiết thú cưng</H1>
                            <ModalForm>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Mã thú cưng:</FormSpan>
                                        <FormInput type="text" value={pet.petId} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tên thú cưng:</FormSpan>
                                        <FormInput type="text" value={pet.petName} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Danh mục:</FormSpan>
                                        <FormInput type="text" value={pet.categoryName} readOnly />
                                    </ModalChiTietItem>
                                </div>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giới tính:</FormSpan>
                                        <FormInput type="text" value={pet.petGender} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tuổi:</FormSpan>
                                        <FormInput type="text" value={pet.petAge} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tiêm chủng:</FormSpan>
                                        <FormInput type="text" value={pet.petVaccinated} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Bảo hành:</FormSpan>
                                        <FormInput type="text" value={pet.petHealthWarranty} readOnly />
                                    </ModalChiTietItem>
                                </div>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tiêu đề:</FormSpan>
                                        <FormInput type="text" value={pet.petTitle} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Ghi chú:</FormSpan>
                                        <FormInput type="text" value={pet.petNote} readOnly />
                                    </ModalChiTietItem>
                                </div>
                                <ModalChiTietItem>
                                    <FormSpan>Mô tả:</FormSpan>
                                    <FormInput type="text" value={pet.petDescription} readOnly />
                                </ModalChiTietItem>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Số lượng:</FormSpan>
                                        <FormInput type="text" value={pet.petQuantity} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giá bán:</FormSpan>
                                        <FormInput type="text" value={pet.petPrice ? format_money((pet.petPrice).toString()) : null} readOnly />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giảm giá:</FormSpan>
                                        <FormInput type="text" value={pet.petPriceDiscount ? format_money((pet.petPriceDiscount).toString()) : null} readOnly />
                                    </ModalChiTietItem>
                                </div>
                                <ModalChiTietItem>
                                    <FormSpan>Hình ảnh:</FormSpan>
                                    <ImageWrapper>
                                        {
                                            petModalImage.map((image, index) => {
                                                return (
                                                    <ChiTietHinhAnh src={image} />
                                                );
                                            })
                                        }
                                    </ImageWrapper>
                                </ModalChiTietItem>
                            </ModalForm>
                            <ButtonUpdate>
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
    //  =============== Thêm thú cưng ===============
    console.log("newPetVaccinated: ", newPetVaccinated);
    console.log("newPetHealthWarranty: ", newPetHealthWarranty);
    if (type === "addPet") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ThempetWrapper showModal={showModal} style={{ flexDirection: `column` }}>
                            <H1>Thêm thú cưng mới</H1>
                            <ModalForm>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tên thú cưng:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setNewPetName(e.target.value)} placeholder="Tên của thú cưng" />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Danh mục:</FormSpan>
                                        <FormSelect onChange={(e) => { setNewCategoryId(e.target.value) }}>
                                            {category.map((category, key) => {
                                                return (
                                                    <FormOption value={category.categoryId}> {category.categoryName} </FormOption>
                                                )
                                            })}
                                        </FormSelect>
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giới tính:</FormSpan>
                                        <FormSelect onChange={(e) => { setNewPetGender(e.target.value) }}>
                                            <FormOption value="Đực"> Đực </FormOption>
                                            <FormOption value="Cái"> Cái </FormOption>
                                        </FormSelect>
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tuổi thú cưng:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setNewPetAge(e.target.value)} placeholder="Tuổi của thú cưng" />
                                    </ModalChiTietItem>
                                </div>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormLabel>
                                            <FormCheckbox type="checkbox" onChange={(e) => handleCheckboxvaccinated(e.target.value)} value="Đã tiêm chủng" />
                                            <FormSpan>Đã tiêm chủng</FormSpan>
                                        </FormLabel>
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormLabel>
                                            <FormCheckbox type="checkbox" onChange={(e) => handleCheckboxwarranty(e.target.value)} value="Được bảo hành sức khỏe" />
                                            <FormSpan>Bảo hành sức khỏe</FormSpan>
                                        </FormLabel>
                                    </ModalChiTietItem>
                                </div>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tiêu đề:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setNewPetTitle(e.target.value)} placeholder="Nhập vào tiêu đề hiển thị thú cưng" />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Ghi chú:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setNewPetNote(e.target.value)} placeholder="Ghi chú cho thú cưng này" />
                                    </ModalChiTietItem>
                                </div>
                                <ModalChiTietItem>
                                    <FormSpan>Mô tả:</FormSpan>
                                    <FormTextArea rows="4" cols="50" onChange={(e) => setNewPetDescription(e.target.value)} placeholder="Mô tả về thú cưng này" />
                                </ModalChiTietItem>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Số lượng:</FormSpan>
                                        <FormInput type="number" min="1" onChange={(e) => setNewPetQuantity(e.target.value)} placeholder="Số lượng của thú cưng" />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giá bán:</FormSpan>
                                        <FormInput type="number" min="0" onChange={(e) => setNewPetPrice(e.target.value)} placeholder="Giá thú cưng" />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giảm giá:</FormSpan>
                                        <FormInput type="number" min="0" onChange={(e) => setNewPetPriceDiscount(e.target.value)} placeholder="Giá sau khi giảm" />
                                    </ModalChiTietItem>
                                </div>
                                <ModalChiTietItem>
                                    <FormSpan>Hình ảnh:</FormSpan>
                                    <FormInput type="file" multiple onChange={(e) => handleShowImg(e.target.files)} />
                                    <ImageWrapper>
                                        {
                                            newImage.length > 0   //Khi mảng hình có hình thì hiện các hình trong mảng
                                                ?
                                                newImage.map((image, index) => {
                                                    return (
                                                        <ChiTietHinhAnh src={image} />
                                                    );
                                                })
                                                :   //Khi mảng hình trống thì hiện No Available Image
                                                <ChiTietHinhAnh src={"https://firebasestorage.googleapis.com/v0/b/longpets-50c17.appspot.com/o/1650880603321No-Image-Placeholder.svg.png?alt=media&token=2a1b17ab-f114-41c0-a00d-dd81aea80d3e"} />
                                        }
                                    </ImageWrapper>
                                </ModalChiTietItem>
                            </ModalForm>
                            <ButtonUpdate>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => handleThempet({
                                            newCategoryId: newCategoryId,
                                            newPetName: newPetName,
                                            newPetGender: newPetGender,
                                            newPetAge: newPetAge,
                                            newPetVaccinated: newPetVaccinated,
                                            newPetHealthWarranty: newPetHealthWarranty,
                                            newPetTitle: newPetTitle,
                                            newPetDescription: newPetDescription,
                                            newPetNote: newPetNote,
                                            newPetQuantity: newPetQuantity,
                                            newPetPrice: newPetPrice,
                                            newPetPriceDiscount: newPetPriceDiscount,
                                            newImage: newImage
                                        })}
                                    >Thêm vào</ButtonClick>
                                </ButtonContainer>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => handleCloseUpdate()}
                                    >Hủy bỏ</ButtonClick>
                                </ButtonContainer>
                            </ButtonUpdate>
                            <CloseModalButton
                                aria-label="Close modal"
                                onClick={() => handleCloseUpdate()}
                            >
                                <CloseOutlined />
                            </CloseModalButton>
                        </ThempetWrapper>
                    </Background>
                ) : null}
            </>
        );
    }
    // =============== Chỉnh sửa thú cưng ===============
    if (type === "updatePet") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ThempetWrapper showModal={showModal} style={{ flexDirection: `column` }}>
                            <H1>Cập nhật thông tin thú cưng</H1>
                            <ModalForm>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tên thú cưng:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setPetModalPetName(e.target.value)} value={petModalPetName} />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Danh mục:</FormSpan>
                                        <FormSelect onChange={(e) => { setPetModalCategoryId(e.target.value) }}>
                                            {category.map((category, key) => {
                                                if (category.categoryId === petModalCategoryId) {
                                                    return (
                                                        <FormOption value={category.categoryId} selected> {category.categoryName} </FormOption>
                                                    )
                                                } else {
                                                    return (
                                                        <FormOption value={category.categoryId}> {category.categoryName} </FormOption>
                                                    )
                                                }
                                            })}
                                        </FormSelect>
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giới tính:</FormSpan>
                                        <FormSelect onChange={(e) => { setPetModalPetGender(e.target.value) }}>
                                            {
                                                petModalPetGender === "Đực"
                                                    ?
                                                    <FormOption value="Đực" selected> Đực </FormOption>
                                                    :
                                                    <FormOption value="Đực"> Đực </FormOption>
                                            }
                                            {
                                                petModalPetGender === "Cái"
                                                    ?
                                                    <FormOption value="Cái" selected> Cái </FormOption>
                                                    :
                                                    <FormOption value="Cái"> Cái </FormOption>
                                            }
                                        </FormSelect>
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tuổi thú cưng:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setPetModalPetAge(e.target.value)} value={petModalPetAge} />
                                    </ModalChiTietItem>
                                </div>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormLabel>
                                            {
                                                petModalPetVaccinated === "Đã tiêm chủng"
                                                    ?
                                                    <FormCheckbox type="checkbox" checked={true} onChange={(e) => handleCheckboxPetVaccinatedUpdate(e)} value="Đã tiêm chủng" />
                                                    :
                                                    <FormCheckbox type="checkbox" checked={false} onChange={(e) => handleCheckboxPetVaccinatedUpdate(e)} value="Đã tiêm chủng" />
                                            }
                                            <FormSpan>Đã tiêm chủng</FormSpan>
                                        </FormLabel>
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormLabel>
                                            {
                                                petModalPetHealthWarranty === "Được bảo hành sức khỏe"
                                                    ?
                                                    <FormCheckbox type="checkbox" checked={true} onChange={(e) => handleCheckboxPetHealthWarrantyUpdate(e)} value="Được bảo hành sức khỏe" />
                                                    :
                                                    <FormCheckbox type="checkbox" checked={false} onChange={(e) => handleCheckboxPetHealthWarrantyUpdate(e)} value="Được bảo hành sức khỏe" />
                                            }
                                            <FormSpan>Bảo hành sức khỏe</FormSpan>
                                        </FormLabel>
                                    </ModalChiTietItem>
                                </div>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Tiêu đề:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setPetModalPetTitle(e.target.value)} value={petModalPetTitle} />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Ghi chú:</FormSpan>
                                        <FormInput type="text" onChange={(e) => setPetModalPetNote(e.target.value)} value={petModalPetNote} />
                                    </ModalChiTietItem>
                                </div>
                                <ModalChiTietItem>
                                    <FormSpan>Mô tả:</FormSpan>
                                    <FormTextArea rows="4" cols="50" onChange={(e) => setPetModalPetDescription(e.target.value)} value={petModalPetDescription} />
                                </ModalChiTietItem>
                                <div style={{ display: "flex" }}>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Số lượng:</FormSpan>
                                        <FormInput type="number" min="1" onChange={(e) => setPetModalPetQuantity(e.target.value)} value={petModalPetQuantity} />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giá bán:</FormSpan>
                                        <FormInput type="number" min="0" onChange={(e) => setPetModalPetPrice(e.target.value)} value={petModalPetPrice} />
                                    </ModalChiTietItem>
                                    <ModalChiTietItem style={{ flex: "1" }}>
                                        <FormSpan>Giảm giá:</FormSpan>
                                        <FormInput type="number" min="0" onChange={(e) => setPetModalPetPriceDiscount(e.target.value)} value={petModalPetPriceDiscount} />
                                    </ModalChiTietItem>
                                </div>
                                <ModalChiTietItem>
                                    <FormSpan>Hình ảnh:</FormSpan>
                                    <FormInput type="file" multiple onChange={(e) => handleChangeImage(e.target.files)} placeholder="Nhập vào tên danh mục thú cưng" />
                                    <ImageWrapper>
                                        {
                                            petModalImageChange.length > 0   //Khi mảng hình có hình thì hiện các hình trong mảng
                                                ?
                                                petModalImageChange.map((updateImage, index) => {
                                                    return (
                                                        <ChiTietHinhAnh src={updateImage} />
                                                    );
                                                })
                                                :
                                                petModalImage.length > 0
                                                    ?
                                                    petModalImage.map((image, index) => {
                                                        return (
                                                            <ChiTietHinhAnh src={image} />
                                                        );
                                                    })
                                                    : null

                                        }
                                    </ImageWrapper>
                                </ModalChiTietItem>
                            </ModalForm>
                            <ButtonUpdate>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => handleUpdatePet({
                                            petId: pet.petId,
                                            newCategoryId: petModalCategoryId,
                                            newPetName: petModalPetName,
                                            newPetGender: petModalPetGender,
                                            newPetAge: petModalPetAge,
                                            newPetVaccinated: petModalPetVaccinated,
                                            newPetHealthWarranty: petModalPetHealthWarranty,
                                            newPetTitle: petModalPetTitle,
                                            newPetDescription: petModalPetDescription,
                                            newPetNote: petModalPetNote,
                                            newPetQuantity: petModalPetQuantity,
                                            newPetPrice: petModalPetPrice,
                                            newPetPriceDiscount: petModalPetPriceDiscount,
                                            petModalImageChange: petModalImageChange,
                                            petModalImage: petModalImage,
                                        })}
                                    >Cập nhật</ButtonClick>
                                </ButtonContainer>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => handleCloseUpdate()}
                                    >Hủy bỏ</ButtonClick>
                                </ButtonContainer>
                            </ButtonUpdate>
                            <CloseModalButton
                                aria-label="Close modal"
                                onClick={() => handleCloseUpdate()}
                            >
                                <CloseOutlined />
                            </CloseModalButton>
                        </ThempetWrapper>
                    </Background>
                ) : null}
            </>
        );
    }
    // // =============== Xóa thú cưng ===============
    if (type === "deletePet") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ModalWrapper showModal={showModal} style={{ backgroundImage: `url("https://img.freepik.com/free-vector/alert-safety-background_97886-3460.jpg?w=1060")`, backgroundPosition: `center center`, backgroundRepeat: `no-repeat`, backgroundSize: `cover`, width: `600px`, height: `400px` }} >
                            <ModalContent>
                                <h1>Bạn muốn xóa thú cưng có mã <span style={{ color: `var(--color-primary)` }}>{pet.petId}</span> này?</h1>
                                <p>Thông tin thú cưng không thể khôi phục. Bạn có chắc chắn?</p>
                                <Button>
                                    <ButtonContainer>
                                        <ButtonClick
                                            onClick={() => { handleDeletePet({ petId: pet.petId }) }}
                                        >Đồng ý</ButtonClick>
                                    </ButtonContainer>
                                    <ButtonContainer>
                                        <ButtonClick
                                            onClick={() => setShowModal(prev => !prev)}
                                        >Hủy bỏ</ButtonClick>
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