import { defineNuxtRouteMiddleware, navigateTo } from '#app';
import {jwtDecode} from "jwt-decode";
import type {JwtPayload} from "jwt-decode";
import {useAccessTokenStore} from "~/store/access-token-store";

export default defineNuxtRouteMiddleware(async (to, from) => {
    const {access} = useAccessTokenStore();
    console.log(access);
    if (access.length == 0) {
        return navigateTo('/sign-in');
    }

    try {
        if (access) {
            const decodedToken = jwtDecode<JwtPayload>(access);
            const now = Date.now() / 1000;
            if (decodedToken.exp && decodedToken.exp < now) {
                return navigateTo('/sign-in');
            }
        }
    }
    catch {
        return navigateTo('/sign-in');
    }
})