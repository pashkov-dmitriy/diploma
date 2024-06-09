<script setup lang="ts">

import {type BaseFieldProps, type GenericObject, useField,} from "vee-validate";
import type {Ref} from "vue";

interface Props {
  model: string,
  bind: BaseFieldProps & GenericObject,
  name: string,
  type: string,
  errors: Record<string, string>
}

const props = defineProps<Props>()
const { value, errorMessage } = useField(props.name, props.bind);
const message = computed(() => props.errors[props.name]);

</script>

<template>
    <div class="form-field">
      <label for="name"/>
      <input v-model="model" v-bind="bind" :name="name" :type="type"/>
      <p>{{ message }}</p>
    </div>
</template>