<script setup lang="ts">
import { useForm } from "vee-validate";
import * as yup from 'yup';
import { errorMessages } from "~/utils/form-errors";

definePageMeta({
  layout: 'auth',
})

interface RegisterForm {
  username: string,
  email: string,
  password: string,
  repeatPassword: string
}

const schema = yup.object({
  username: yup.string()
      .min(3, errorMessages.string.min(3))
      .max(32, errorMessages.string.max(32))
      .required(errorMessages.mixed.required()),
  email: yup.string()
      .email(errorMessages.string.email())
      .required(errorMessages.mixed.required()),
  password: yup.string()
      .min(8, errorMessages.password.min(8))
      .max(32, errorMessages.password.max(32))
      .matches(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/, errorMessages.password.matches())
      .required(errorMessages.mixed.required()),
  repeatPassword: yup.string()
      .oneOf([yup.ref('password')], errorMessages.confirmPassword.oneOf())
      .required(errorMessages.mixed.required()),
})

const { errors, setErrors, defineField } = useForm<RegisterForm>({
  validationSchema: schema
});

const [username, usernameAttr] = defineField('username');
const [email, emailAttr] = defineField('email');
const [password, passwordAttr] = defineField('password');
const [repeatPassword, repeatPasswordAttr] = defineField('repeatPassword');

const isDisabled = computed(() => {
  return !!errors.value.email || !!errors.value.password;
});

</script>

<template>
  <NuxtLayout name="auth">
    <h1>Зарегистрироваться</h1>
    <div class="form-wrapper">
      <div class="sign-up__username field">
        <label for="username">Имя пользователя</label>
        <input v-model="username" v-bind="usernameAttr" type="text" name="username" id="">
        <p v-if="errors.username">{{ errors.username }}</p>
      </div>
      <div class="sign-up__email field">
        <label for="email">Ваш e-mail</label>
        <input v-model="email" v-bind="emailAttr" type="text" name="email" id="">
        <p v-if="errors.email">{{ errors.email }}</p>
      </div>
      <div class="sign-in__password field">
        <label for="password">Ваш пароль</label>
        <input v-model="password" v-bind="passwordAttr" type="password" name="password" id="">
        <p v-if="errors.password">{{ errors.password }}</p>
      </div>
      <div class="sign-in__password field">
        <label for="repeat">Повторите пароль</label>
        <input v-model="repeatPassword" v-bind="repeatPasswordAttr" type="password" name="repeat" id="">
        <p v-if="errors.repeatPassword">{{ errors.repeatPassword }}</p>
      </div>
      <button :disabled="isDisabled" class="submit">Создать аккаунт</button>
    </div>
  </NuxtLayout>
</template>

<style scoped>

</style>