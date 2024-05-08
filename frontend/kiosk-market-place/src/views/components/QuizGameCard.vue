<template>
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">{{ title }}</h4>
        <div class="form-check" v-for="(option, index) in options" :key="index">
          <input
            class="form-check-input"
            type="radio"
            :id="`option${index}`"
            :value="option"
            v-model="selectedOption"
          />
          <label class="form-check-label" :for="`option${index}`">{{ option }}</label>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      title: String,
      options: Array,
    },
    data() {
      return {
        selectedOption: null,
      };
    },
  };
  </script>
  
  <style scoped>
  /* You can add custom styling here if needed */
  </style>