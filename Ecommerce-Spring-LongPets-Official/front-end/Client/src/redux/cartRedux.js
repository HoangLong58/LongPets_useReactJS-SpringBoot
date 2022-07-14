import { createSlice } from "@reduxjs/toolkit";

const cartSlice = createSlice({
    name: "cart",
    initialState: {
        products: [],
        cartQuantity: 0,
        cartTotal: 0,
    },
    reducers: {
        addProduct: (state, action)=> {
            let i;
            let isFind = false;
            for(i = 0; i < state.products.length; i++) {
                if(state.products[i].data.petId === parseInt(action.payload.data.petId)) {
                    const petQuantityInCart = state.products[i].petQuantityBuy;
                    const petQuantityCanBuy = state.products[i].data.petQuantity - petQuantityInCart;
                    const petQuantityWantToBuy = action.payload.petQuantityBuy;

                    if(petQuantityCanBuy == 0) {
                        console.log("Số lượng mua đã đạt giới hạn");
                        return;
                    }
                    if(petQuantityWantToBuy <= petQuantityCanBuy) {
                        state.products[i].petQuantityBuy += petQuantityWantToBuy;
                        state.cartTotal += action.payload.data.petPriceDiscount * petQuantityWantToBuy;
                        console.log("So luong trong gio hang:"+petQuantityInCart+" So luong co the mua: "+petQuantityCanBuy+" So luong muon mua: "+petQuantityWantToBuy)
                        console.log("Tìm thấy & cập nhật lại số lượng giỏ hàng thành công");
                        isFind = true;
                        break;
                    }else {
                        console.log("Số lượng không hợp lệ");
                        isFind = true;
                    }
                }
            }
            // Chưa có mã thú cưng này trong giỏ hàng
            if(!isFind) {
                state.products.push(action.payload);
                state.cartQuantity += 1;
                state.cartTotal += action.payload.data.petPriceDiscount * action.payload.petQuantityBuy;
                console.log("Thêm vào giỏ hàng thành công");
                console.log("Them san pham: ", action.payload)
            }
        },
        updateProduct: (state, action)=> {
            console.log("Cap nhat san pham: ", action.payload)
            let i;
            for(i = 0; i < state.products.length; i++) {
                if(state.products[i].data.petId === parseInt(action.payload.data.petId)) {
                    if(action.payload.petQuantityUpdate === 0) {
                        state.products[i].petQuantityBuy = action.payload.petQuantityUpdate;
                        state.products.splice(i, 1);
                        state.cartQuantity -= 1 ;
                        state.cartTotal -= action.payload.data.petPriceDiscount * action.payload.petQuantityBuy;
                    }
                    if(action.payload.petQuantityUpdate === 1) {
                        state.products[i].petQuantityBuy += 1;
                        state.cartTotal += action.payload.data.petPriceDiscount * 1;
                    }
                    if(action.payload.petQuantityUpdate === -1) {
                        state.products[i].petQuantityBuy -= 1;
                        if(state.products[i].petQuantityBuy <= 0){
                            state.products.splice(i, 1);
                            state.cartQuantity -= 1 ;
                            state.cartTotal -= action.payload.data.petPriceDiscount * action.payload.petQuantityBuy;
                        }else {

                            state.cartTotal -= action.payload.data.petPriceDiscount * 1;
                        }
                    }
                }
            }
            
        // themSanPham: (state, action)=> {
        //     console.log("Them san pham: ", action.payload)
        //     state.products.push(action.payload);
        //     state.cartQuantity += 1;
        //     state.cartTotal += action.payload.data.petPriceDiscount * action.payload.petQuantityBuy;
        // },
        // capNhatSanPham: (state, action)=> {
        //     console.log("Cap nhat san pham: ", action.payload)

        //     state.products.petQuantityBuy += action.payload.petQuantityBuy; 
        //     // state.products.push(action.payload);
        //     // state.cartQuantity += 1;
        //     state.cartTotal += action.payload.data.petPriceDiscount * action.payload.petQuantityBuy;
        // },
        },
        logoutCart: (state) => {
            state.products = [];
            state.cartQuantity = 0;
            state.cartTotal = 0;
        }
    }
});

export const { addProduct, updateProduct, logoutCart } = cartSlice.actions
export default cartSlice.reducer;