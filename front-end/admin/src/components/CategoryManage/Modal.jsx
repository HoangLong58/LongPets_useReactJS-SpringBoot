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


const Modal = ({ showModal, setShowModal, type, category, setReRenderData, handleClose, showToastFromOut }) => {
    const modalRef = useRef();
    const closeModal = (e) => {
        if (modalRef.current === e.target) {
            setShowModal(false);
            setLinkImage(null);
        }
    }

    const keyPress = useCallback(
        (e) => {
            if (e.key === 'Escape' && showModal) {
                setShowModal(false);
                setLinkImage(null);
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

    // =============== X??? l?? c???p nh???t danh m???c ===============
    const handleUpdateCategory = async ({ newCategoryName, newCategoryTitle, newCategoryImage, categoryId }) => {
        console.log("Dau vao:", { newCategoryName, newCategoryTitle, newCategoryImage, categoryId });

        if (newCategoryName !== "" && newCategoryTitle !== "" && newCategoryImage !== "") {
            try {
                const updateCategoryRes = await axios.put(`http://localhost:8080/category/update-category/${categoryId}`, { categoryName: newCategoryName, categoryTitle: newCategoryTitle, categoryImage: newCategoryImage });
                console.log("KQ tr??? v??? update: ", updateCategoryRes);
                setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - categoryMain & categoryRight.jsx
                setShowModal(prev => !prev);
                handleClose();
                const dataShow = { message: "Thay ?????i danh m???c " + newCategoryName + " th??nh c??ng!", type: "success" };
                showToastFromOut(dataShow);
            } catch (err) {
                setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - categoryMain & categoryRight.jsx
                setShowModal(prev => !prev);
                handleClose();
                const dataShow = { message: "Th???t b???i! Kh??ng th??? c???p nh???t danh m???c " + newCategoryName, type: "danger" };
                showToastFromOut(dataShow);
            }
        }
    }
    //  test
    const [categoryModal, setCategoryModal] = useState();
    const [categoryNameModal, setCategoryNameModal] = useState();
    const [categoryIdModal, setCategoryIdModal] = useState();
    const [categoryTitleModal, setCategoryTitleModal] = useState();
    const [categoryImageModal, setCategoryImageModal] = useState();

    const [categoryIdModalOld, setCategoryIdModalOld] = useState();
    const [categoryNameModalOld, setCategoryNameModalOld] = useState();
    const [categoryTitleModalOld, setCategoryTitleModalOld] = useState();
    const [categoryImageModalOld, setCategoryImageModalOld] = useState();
    useEffect(() => {
        const getCategory = async () => {
            try {
                const categoryRes = await axios.get(`http://localhost:8080/category/get-category/${category.categoryId}`);
                setCategoryModal(categoryRes.data);
                setCategoryIdModal(categoryRes.data.categoryId);
                setCategoryNameModal(categoryRes.data.categoryName);
                setCategoryTitleModal(categoryRes.data.categoryTitle);
                setCategoryImageModal(categoryRes.data.categoryImage);

                setCategoryIdModalOld(categoryRes.data.categoryId);
                setCategoryNameModalOld(categoryRes.data.categoryName);
                setCategoryTitleModalOld(categoryRes.data.categoryTitle);
                setCategoryImageModalOld(categoryRes.data.categoryImage);
            } catch (err) {
                console.log("L???i l???y danh m???c: ", err);
            }
        }
        getCategory();
    }, [category]);
    console.log("Danh m???c modal: ", categoryModal);
    // Thay ?????i h??nh ???nh
    const handleChangeImg = (newImage) => {
        const uniqueImage = new Date().getTime() + newImage.name;
        const storage = getStorage(app);
        const storageRef = ref(storage, uniqueImage);
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
                        setCategoryImageModal(downloadURL);
                    } catch (err) {
                        console.log("L???i c???p nh???t h??nh ???nh:", err);
                    }
                });
            }
        );
    }

    const handleCloseUpdate = () => {
        // Set l???i gi?? tr??? c?? sau khi ????ng Modal
        setCategoryImageModal(categoryImageModalOld);
        setCategoryNameModal(categoryNameModalOld);
        setCategoryTitleModal(categoryTitleModalOld);

        setShowModal(prev => !prev);
    }



    // =============== X??? l?? th??m danh m???c ===============
    const [newCategoryName, setNewCategoryName] = useState("");
    const [newCategoryTitle, setNewCategoryTitle] = useState("");
    const [linkImage, setLinkImage] = useState(null);
    // const handleAddCategory = async ({ newCategoryName, newCategoryTitle, linkImage }) => {
    //     const uniqueImage = new Date().getTime() + linkImage.name;
    //     const storage = getStorage(app);
    //     const storageRef = ref(storage, uniqueImage);
    //     const uploadTask = uploadBytesResumable(storageRef, linkImage);

    //     // Listen for state changes, errors, and completion of the upload.
    //     uploadTask.on('state_changed',
    //         (snapshot) => {
    //             // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
    //             const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
    //             console.log('Upload is ' + progress + '% done');
    //             switch (snapshot.state) {
    //                 case 'paused':
    //                     console.log('Upload is paused');
    //                     break;
    //                 case 'running':
    //                     console.log('Upload is running');
    //                     break;
    //                 default:
    //             }
    //         },
    //         (error) => {
    //             // A full list of error codes is available at
    //             // https://firebase.google.com/docs/storage/web/handle-errors
    //             switch (error.code) {
    //                 case 'storage/unauthorized':
    //                     // User doesn't have permission to access the object
    //                     break;
    //                 case 'storage/canceled':
    //                     // User canceled the upload
    //                     break;

    //                 // ...

    //                 case 'storage/unknown':
    //                     // Unknown error occurred, inspect error.serverResponse
    //                     break;
    //             }
    //         },
    //         () => {
    //             // Upload completed successfully, now we can get the download URL
    //             getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
    //                 console.log('File available at', downloadURL);
    //                 setLinkImage(downloadURL);
    //                 if (newCategoryName !== "" && newCategoryTitle !== "" && linkImage !== null) {
    //                     try {
    //                         const insertcategoryRes = axios.post("http://localhost:3001/api/products/insertcategory", { newCategoryName: newCategoryName, newCategoryTitle: newCategoryTitle, linkImage: downloadURL });
    //                         console.log("KQ tr??? v??? update: ", insertcategoryRes);
    //                         setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - categoryMain & categoryRight.jsx
    //                         setShowModal(prev => !prev);
    //                         const dataShow = { message: "Th??m danh m???c " + newCategoryName + " th??nh c??ng!", type: "success" };
    //                         showToastFromOut(dataShow);
    //                     } catch (err) {
    //                         console.log("L???i insert: ", err);
    //                         setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - categoryMain & categoryRight.jsx
    //                         setShowModal(prev => !prev);
    //                         const dataShow = { message: "???? c?? l???i khi th??m danh m???c " + newCategoryName, type: "danger" };
    //                         showToastFromOut(dataShow);
    //                     }
    //                 }
    //             });
    //         }
    //     );


    // }

    // Thay ?????i h??nh ???nh
    const handleShowImg = (newImage) => {
        const uniqueImage = new Date().getTime() + newImage.name;
        const storage = getStorage(app);
        const storageRef = ref(storage, uniqueImage);
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
                        setLinkImage(downloadURL);
                    } catch (err) {
                        console.log("L???i c???p nh???t h??nh ???nh:", err);
                    }
                });
            }
        );
    }

    const handleAddCategory = async ({ newCategoryName, newCategoryTitle, linkImage }) => {
        if (newCategoryName !== "" && newCategoryTitle !== "" && linkImage !== null) {
            try {
                const insertcategoryRes = axios.post("http://localhost:8080/category/add-category", { categoryName: newCategoryName, categoryTitle: newCategoryTitle, categoryImage: linkImage }).then(() => {
                    console.log("KQ tr??? v??? update: ", insertcategoryRes);
                    setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - categoryMain & categoryRight.jsx
                    setShowModal(prev => !prev);
                    const dataShow = { message: "Th??m danh m???c " + newCategoryName + " th??nh c??ng!", type: "success" };
                    showToastFromOut(dataShow);
                    setLinkImage(null);
                });
            } catch (err) {
                console.log("L???i insert: ", err);
                setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - categoryMain & categoryRight.jsx
                setShowModal(prev => !prev);
                const dataShow = { message: "???? c?? l???i khi th??m danh m???c " + newCategoryName, type: "danger" };
                showToastFromOut(dataShow);
            }
        }
    }

    // =============== X??? l?? x??a danh m???c ===============
    const handleDeleteCategory = async ({ categoryId, categoryName }) => {
        if (categoryId !== "") {
            try {
                const deletecategoryRes = await axios.delete(`http://localhost:8080/category/delete-category/${categoryId}`);
                console.log("KQ tr??? v??? delete: ", deletecategoryRes);
                setReRenderData(prev => !prev); //Render l???i csdl ??? Compo cha l?? - categoryMain & categoryRight.jsx
                setShowModal(prev => !prev);
                handleClose();  //????ng thanh t??m ki???m
                const dataShow = { message: "???? x??a danh m???c " + categoryName + " th??nh c??ng!", type: "success" };
                showToastFromOut(dataShow);
            } catch (err) {
                console.log("L???i delete err: ", err);
            }
        }
    }

    // ================================================================
    //  =============== Th??m danh m???c ===============
    if (type === "addCategory") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ModalWrapper showModal={showModal} style={{ flexDirection: `column` }}>
                            {/* <ModalImg src="https://scontent.fsgn5-8.fna.fbcdn.net/v/t39.30808-6/278270298_565881528230362_2335941423796450487_n.jpg?_nc_cat=109&ccb=1-5&_nc_sid=730e14&_nc_ohc=1csEjacjr-cAX9nRW6x&_nc_ht=scontent.fsgn5-8.fna&oh=00_AT-At5LL1aaL1GnAjuZnxBtccvQ-qUOvbj-LYussaVFg8w&oe=625E2D98" alt="img n??"></ModalImg> */}
                            <H1>Th??m danh m???c m???i</H1>
                            <ModalForm>
                                <ModalFormItem>
                                    <FormSpan>T??n danh m???c:</FormSpan>
                                    <FormInput type="text" onChange={(e) => setNewCategoryName(e.target.value)} placeholder="Nh???p v??o t??n danh m???c th?? c??ng" />
                                </ModalFormItem>
                                <ModalFormItem>
                                    <FormSpan>Ti??u ?????:</FormSpan>
                                    <FormInput type="text" onChange={(e) => setNewCategoryTitle(e.target.value)} placeholder="Nh???p v??o ti??u ????? hi???n th??? th?? c??ng" />
                                </ModalFormItem>
                                <ModalFormItem>
                                    <FormSpan>H??nh ???nh:</FormSpan>
                                    <FormInput type="file" onChange={(e) => handleShowImg(e.target.files[0])} placeholder="Nh???p v??o t??n danh m???c th?? c??ng" />
                                    <FormImg src={linkImage !== null ? linkImage : "https://firebasestorage.googleapis.com/v0/b/longpets-50c17.appspot.com/o/1650880603321No-Image-Placeholder.svg.png?alt=media&token=2a1b17ab-f114-41c0-a00d-dd81aea80d3e"} key={linkImage}></FormImg>
                                </ModalFormItem>

                            </ModalForm>
                            <ButtonUpdate>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => handleAddCategory({ newCategoryName: newCategoryName, newCategoryTitle: newCategoryTitle, linkImage: linkImage })}
                                    >Th??m v??o</ButtonClick>
                                </ButtonContainer>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => setShowModal(prev => !prev)}
                                    >H???y b???</ButtonClick>
                                </ButtonContainer>
                            </ButtonUpdate>
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
    // =============== Ch???nh s???a danh m???c ===============
    if (type === "updateCategory") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ModalWrapper showModal={showModal} style={{ flexDirection: `column` }}>
                            <H1>Ch???nh s???a danh m???c</H1>
                            <ModalForm>
                                <ModalFormItem>
                                    <FormSpan>T??n danh m???c:</FormSpan>
                                    <FormInput type="text" onChange={(e) => setCategoryNameModal(e.target.value)} value={categoryNameModal} />
                                </ModalFormItem>
                                <ModalFormItem>
                                    <FormSpan>Ti??u ?????:</FormSpan>
                                    <FormInput type="text" onChange={(e) => setCategoryTitleModal(e.target.value)} value={categoryTitleModal} />
                                </ModalFormItem>
                                <ModalFormItem>
                                    <FormSpan>H??nh ???nh:</FormSpan>
                                    <FormInput type="file" onChange={(e) => handleChangeImg(e.target.files[0])} />
                                    <FormImg src={categoryImageModal !== categoryImageModalOld ? categoryImageModal : categoryImageModalOld} key={categoryImageModal}></FormImg>
                                </ModalFormItem>
                            </ModalForm>
                            <ButtonUpdate>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => handleUpdateCategory({ newCategoryName: categoryNameModal, newCategoryTitle: categoryTitleModal, newCategoryImage: categoryImageModal, categoryId: categoryIdModal })}
                                    >C???p nh???t</ButtonClick>
                                </ButtonContainer>
                                <ButtonContainer>
                                    <ButtonClick
                                        onClick={() => handleCloseUpdate()}
                                    >H???y b???</ButtonClick>
                                </ButtonContainer>
                            </ButtonUpdate>
                            <CloseModalButton
                                aria-label="Close modal"
                                onClick={() => handleCloseUpdate()}
                            >
                                <CloseOutlined />
                            </CloseModalButton>
                        </ModalWrapper>
                    </Background>
                ) : null}
            </>
        );
    }
    // =============== X??a danh m???c ===============
    if (type === "deleteCategory") {
        return (
            <>
                {showModal ? (
                    <Background ref={modalRef} onClick={closeModal}>
                        <ModalWrapper showModal={showModal} style={{ backgroundImage: `url("https://img.freepik.com/free-vector/alert-safety-background_97886-3460.jpg?w=1060")`, backgroundPosition: `center center`, backgroundRepeat: `no-repeat`, backgroundSize: `cover`, width: `600px`, height: `400px` }} >
                            {/* <ModalImg src="https://img.freepik.com/free-vector/alert-safety-background_97886-3460.jpg?w=1060" alt="img n??"></ModalImg> */}
                            <ModalContent>
                                <h1>B???n mu???n x??a danh m???c <span style={{ color: `var(--color-primary)` }}>{categoryNameModal}</span> n??y?</h1>
                                <p>To??n b??? th?? c??ng c???a danh m???c n??y c??ng s??? b??? x??a</p>
                                <Button>
                                    <ButtonContainer>
                                        <ButtonClick
                                            onClick={() => { handleDeleteCategory({ categoryId: categoryIdModal, categoryName: categoryNameModal }) }}
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