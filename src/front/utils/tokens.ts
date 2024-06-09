export const getTokens = async () => {
    const refreshToken = useCookie('refresh-token');

    if (!refreshToken) {
        return null;
    }

    try {
        const response = await $fetch.raw('http://localhost:8080/auth/tokens');

        if (!response.ok) {
            return null;
        }

        const access = response.headers.get('Authorization');

        return access ? access.replace('Bearer ', '') : null;
    }
    catch (error) {
        return null;
    }
}
