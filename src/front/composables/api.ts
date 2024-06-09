// import {useAuth} from "~/composables/auth";
// import {useAccessTokenStore} from "~/store/access-token-store";
// import type {UseFetchOptions} from "#app";
//
// export async function $api<T>(
//     request: Parameters<typeof $fetch<T>>[0],
//     opts?: Parameters<typeof $fetch<T>>[1]
// ) {
//     const {access} = useAccessTokenStore();
//     const {refresh} = useAuth();
//
//     const options: UseFetchOptions<T> = {
//         ...opts,
//         headers: access != '' ? {
//             Authorization: `Bearer ${access}`,
//         } : {},
//         onResponse: async ({response, options}) => {
//             try {
//                 if (response.status == 401) {
//                     await refresh();
//                     options.headers = { Authorization: `Bearer ${access}` };
//                     $fetch(request, options as UseFetchOptions<T>);
//                 }
//             }
//             catch (error) {
//                 console.error(error);
//             }
//         }
//
//     }
//
//     return useFetch(request, options);
// }

import {useAuth} from "~/composables/auth";
import {useAccessTokenStore} from "~/store/access-token-store";
import type {UseFetchOptions} from "#app";

export async function $api<T>(
    request: Parameters<typeof $fetch<T>>[0],
    opts?: Parameters<typeof $fetch<T>>[1]
) {
    const { access, setAccessToken } = useAccessTokenStore();
    const { refresh } = useAuth();
    let options: UseFetchOptions<T> = {
        ...opts,
        headers: access ? {
            Authorization: `Bearer ${access}`,
        } : {},
    };

    return await useFetch(request, {
        ...options,
        onResponseError: async ({response, error}) => {
            if (response.status === 401) {
                await refresh();
                useFetch(request, options);
            }
            else if (response.status === 404) {
                await useRouter().push('/404');
            }
            else {
                await useRouter().push('500');
            }
        }
    })
}