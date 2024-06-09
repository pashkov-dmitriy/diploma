import { defineStore } from 'pinia';
import type {JwtPayload} from "jwt-decode";
import {jwtDecode} from 'jwt-decode';

export const useAccessTokenStore =  defineStore('access-token',  () => {
    const access = ref('');

    function setAccessToken(accessToken: string) {
        access.value = accessToken;
    }

    function clearAccessToken() {
        access.value = '';
    }

    function getUserId() {
        console.log(access)
       const jwt = jwtDecode<JwtPayload>(access.value);
        console.log(jwt)
       return jwt.sub;
    }

    return {access, setAccessToken, clearAccessToken, getUserId};
})