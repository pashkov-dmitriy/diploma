<script setup lang="ts">
import {useAccessTokenStore} from "~/store/access-token-store";
import {useForm} from "vee-validate";
import * as yup from 'yup';
import {errorMessages} from "~/utils/form-errors";

interface User {
  id: number,
  username: string,
  email: string,
  passwordHash: string,
  description?: string,
  profilePicUri?: string
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
      .matches(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/, errorMessages.password.matches()),
  repeatPassword: yup.string()
      .oneOf([yup.ref('password')], errorMessages.confirmPassword.oneOf())
})


const {values, setValues, errors, setErrors, defineField} = useForm({
  validationSchema: schema
});

let [username, usernameAttr] = defineField('username');
let [email, emailAttr] = defineField('email');
let [password, passwordAttr] = defineField('password');
let [description, descriptionAttr] = defineField('description');
let [repeatPassword, repeatPasswordAttr] = defineField('repeatPassword');

const {getUserId} = useAccessTokenStore();
const {data, pending, error} = await $api<User>('http://localhost:8020/users');
console.log(data);
setValues({
  username: data.value?.username,
  email: data.value?.email,
  description: data.value?.description,
})
const isEditable = computed(() => data.value?.id == getUserId());
const noImage = '/no-image.jpg';

const update = async () => {
  const {data, pending, error} = await $api('http://localhost:8020/users', {
    method: 'POST',
    body: {
      username: username.value,
      email: email.value,
      description: description.value,
      password: password.value,
    }
  });
}

</script>

<template>
  <div class="user-container">
    <div class="user-photo">
      <img :src="data?.profilePicUri ? data.profilePicUri : noImage" alt="" class="photo">
      <button class="change-photo">Сменить фото</button>
    </div>
    <div class="user-info">
      <form action="" method="post" class="user-info-edit">
        <h1>Изменить имя пользователя</h1>
        <input v-model="username" v-bind="usernameAttr" :disabled="!isEditable" type="text" class="change-username">
        <p v-if="errors.username">{{ errors.username }}</p>
        <h1>Изменить email</h1>
        <input v-model="email" v-bind="usernameAttr" type="text">
        <p v-if="errors.email">{{ errors.email }}</p>
        <h1>Изменить описание</h1>
        <input v-model="description" v-bind="descriptionAttr" type="text">
        <p v-if="errors.description">{{ errors.description }}</p>
        <h1>Изменить пароль</h1>
        <input v-model="password" v-bind="passwordAttr" type="password" class="change-password">
        <p v-if="errors.password">{{ errors.password }}</p>
        <input v-model="repeatPassword" v-bind="passwordAttr" type="password" name="password" id="">
        <p v-if="errors.repeatPassword">{{ errors.repeatPassword }}</p>
        <button @click="update">Сохранить изменения</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
.user-container {
  display: flex;
  flex-direction: row;
}

.user-photo {
  padding: 8rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 40%;
}

.user-photo > img {
  width: 100%;
  height: auto;
  border-radius: 10px;
  margin-bottom: 0.5rem;
}

button {
  width: 100%;
}

.user-info {
  width: 100%;
}

.user-info-edit {
  padding: 1rem 5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>