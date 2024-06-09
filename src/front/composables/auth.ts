import {useAccessTokenStore} from "~/store/access-token-store";
import {jwtDecode} from "jwt-decode";
import type {JwtPayload} from "jwt-decode";

type Errors = {
    username: boolean,
    password: boolean
} | null

interface LoginResult {
    loggedIn: boolean,
    errors: Errors
}

export function useAuth() {
    const {access, setAccessToken, clearAccessToken} = useAccessTokenStore();
    const router = useRouter();

    async function refresh() {
        if (access == '') {

        } else {
            const payload = jwtDecode<JwtPayload>(access);
            if (!payload.exp || payload.exp < Date.now()) {
                const response = await $fetch.raw('http://localhost:8080/auth/tokens/refresh',
                    {
                        method: 'GET'
                    })
                if (response.ok) {
                    const token = response.headers.get('Authorization')?.replace('Bearer ', '');
                    if (token) {
                        setAccessToken(token);
                    }
                }
            }
            router.push('/login');
        }
    }

    async function login(credentials: { email: string; password: string }): Promise<LoginResult | null> {
        try {
            const response = await $fetch.raw('http://localhost:8080/auth/sign-in', {
                method: 'POST',
                body: credentials,
            });

            // Assuming the response is in the expected format
            if (response.status === 401) {
                return {
                    loggedIn: false,
                    errors: response._data as Errors,
                };
            } else if (response.status === 500) {
                return {
                    loggedIn: false,
                    errors: null,
                };
            } else {
                const token = response.headers.get('Authorization')?.replace('Bearer ', '');
                if (token) {
                    setAccessToken(token);
                    return {
                        loggedIn: true,
                        errors: null,
                    };
                } else {
                    return {
                        loggedIn: false,
                        errors: null,
                    };
                }
            }
        } catch (error) {
            console.error(error);
            return null;
        }
    }

    function logout() {
        clearAccessToken();
        const refresh = useCookie('refresh-token');
        if (refresh) {
            refresh.value = null;
        }
    }

    return {refresh, login, logout}
}