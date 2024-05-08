<template>
    <div>
      <QuizGameCard title="Choose a Color" :options="colorOptions" />
      <QuizGameCard title="Choose a Size" :options="sizeOptions" />
      <QuizGameCard title="Choose a Size" :options="sizeOptions" />
      <QuizGameCard title="Choose a Size" :options="sizeOptions" />
    </div>
  </template>
  
  <script>
  import QuizGameCard from "@/views/components/QuizGameCard.vue";
  
  export default {
    components: {
        QuizGameCard,
    },
    data() {
      return {
        colorOptions: ["Red", "Green", "Blue"],
        sizeOptions: ["Small", "Medium", "Large"],
      };
    },
  };
  </script>