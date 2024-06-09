<script setup lang="ts">
import { useForm } from "vee-validate";
import * as yup from 'yup';
import { errorMessages } from "~/utils/form-errors";
import {useAccessTokenStore} from "~/store/access-token-store";

definePageMeta({
  layout: 'auth',
})

onMounted(() => {
  const {access} = useAccessTokenStore();
  if (access != "") {
    console.log("token");
    router.push("/");
  }
})

interface LoginForm {
    email: string,
    password: string
}

const router = useRouter();

const schema = yup.object({
    email: yup.string().required(errorMessages.mixed.required())
                .email(errorMessages.string.email()),
    password: yup.string().required(errorMessages.mixed.required())
})

const {setAccessToken} = useAccessTokenStore();
const { values, errors, setErrors, defineField, handleSubmit } = useForm<LoginForm>({
    validationSchema: schema
});

const isDisabled = computed(() => {
  return !!errors.value.email || !!errors.value.password;
});

const [email, emailAttr] = defineField('email');
const [password, passwordAttr] = defineField('password');

const login = handleSubmit(async values => {
  console.log("login call");
  const data = await $fetch('http://localhost:8090/auth/login', {
    method: 'POST',
    body: values,
    onResponseError: ({response, error}) => {
      if (response.status == 401) {
        console.log(response)
        setErrors({
          password: "Некорретный email или пароль"
        });
      }
    },
    onResponse: ({response}) => {
      const auth = response.headers.get('Authorization')?.replace('Bearer ', '');
      if (auth) {
        console.log(auth)
        setAccessToken(auth);
      }
  }
  });
  console.log('sdfs')
  await router.push('/');
})

</script>

<template>
    <NuxtLayout name="auth">
      <h1>Войти в приложение</h1>
      <form @submit="login" class="form-wrapper">
        <div class="sign-in__email field">
          <label for="email">Ваш e-mail</label>
          <input v-model="email" v-bind="emailAttr" type="text" name="email" id="">
          <p class="form__error" v-if="errors.email">{{ errors.email }}</p>
        </div>
        <div class="sign-in__password field">
          <label for="email">Ваш пароль</label>
          <input v-model="password" v-bind="passwordAttr" type="password" name="email" id="">
          <p class="form__error" v-if="errors.password">{{ errors.password }}</p>
        </div>
        <button type="submit" :disabled="isDisabled" class="submit">Войти</button>
        <div class="signup-link">
          <span>Нет аккаунта?  </span>
          <router-link to="/sign-up">Создать</router-link>
        </div>
      </form>
    </NuxtLayout>
</template>

<style>
.form-wrapper {
  padding: 1rem;
  min-height: 50%;
  width: 100%;

  flex-grow: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1.2rem;

  font-size: larger;

  border-radius: 10px;
  border: solid var(--secondary-color) 0.2rem;
}

.field {
  width: 100%;
}

.form__error {
  max-width: 100%;
  font-size: 0.75em;
}

.submit {
  width: 100%;
}

.signup-link {
  font-size: medium;
}
</style>