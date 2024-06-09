import axios from 'axios';
import {useAccessTokenStore} from "~/store/access-token-store";

export default defineNuxtPlugin((app) => {
    const router = useRouter();
    const instance = axios.create({});

    instance.interceptors.request.use((config) => {
        const {access, setAccessToken, clearAccessToken} = useAccessTokenStore();

        if (access != "") {
            config.headers.Authorization = `Bearer ${access}`
        }
        else {

        }
        return config;
    });

    instance.interceptors.response.use(response => response,
        error => {
            if (error.response) {
                switch (error.response.status) {
                    case 401:
                        break;
                    case 404:
                        router.push('/404');
                        break;
                    case 500:
                        router.push('/500');
                        break;
                }
            }
            return Promise.reject(error);
        });

    app.provide('axios', instance);
})