import { configureStore} from "@reduxjs/toolkit";
import adminReducer from "./adminRedux";
import authToken from "../authToken.js"
import {
    persistStore,
    persistReducer,
    FLUSH,
    REHYDRATE,
    PAUSE,
    PERSIST,
    PURGE,
    REGISTER,
} from 'redux-persist'
import storage from 'redux-persist/lib/storage'

const persistConfig = {
    key: 'root',
    version: 1,
    storage,
}

if(localStorage.jwtTokenAdmin) {
  authToken(localStorage.jwtTokenAdmin);
}
  
const persistedReducer = persistReducer(persistConfig, adminReducer)
export const store = configureStore({
    reducer:{
      admin: persistedReducer,
    },
    middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
})

export let persistor = persistStore(store);