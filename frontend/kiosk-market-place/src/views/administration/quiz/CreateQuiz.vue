<template>
    <div class="container-fluid">
      <div class="py-4 container-fluid">
        <div class="mt-3 row">
          <div class="mt-4 col-12 col-md-6 col-xl-4 mt-md-0">
            <h3>Create Quiz</h3>
          </div>
        </div>
        <div class="mt-3 row">
          <div class="mt-4 col-12 col-md-6 col-xl-4 mt-md-0">
            <div class="card h-100">
              <div class="p-3 pb-0 card-header">
                <div class="row">
                  <div class="col-md-8 d-flex align-items-center">
                    <h6 class="mb-0">Quiz title</h6>
                  </div>
                  <div class="col-md-4 text-end">
                  </div>
                </div>
              </div>
              <div class="p-3 card-body">
                <p class="text-sm">Enter the title of the quiz</p>
                <hr class="horizontal gray-light" />
                <ul class="list-group">
                    <label for="title">Title:</label>
                  <input
                    v-model="quiz.title"
                    type="text"
                    id="title"
                    class="form-control mb-3"
                    placeholder="Fill the title of the quiz"
                  />
                </ul>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-xl-4">
            <div class="card h-100">
              <div class="p-3 pb-0 card-header">
                <div class="row">
                  <div class="col-md-8 d-flex align-items-center">
                    <h6 class="mb-0">Questions</h6>
                  </div>
                  <div class="col-md-4 text-end">
                  
                  </div>
                </div>
              </div>
              <div class="p-3 card-body">
                <p class="text-sm">create the questions of the quiz</p>
                <hr class="my-4 horizontal gray-light" />
                <ul class="list-group">
                    <label for="quiz_id">Quiz ID:</label>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="question.quiz_id"
                  >
                    <option value="0">Select Quiz ID</option>
                    <option
                      v-for="quiz in quizes"
                      v-bind:key="quiz.id"
                      :value="quiz.id"
                    >
                      {{ quiz.title }} ({{ quiz.id }})
                    </option>
                  </select>
                  <label for="text">Text:</label>
                  <input
                  
                    type="text"
                    id="retail cost"
                    class="form-control mb-3"
                    placeholder="Fill the text"
                  />
                  <label for="points">Points</label>
                  <input
                   
                    type="number"
                    id="points"
                    class="form-control mb-3"
                  />
                </ul>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-xl-4">
            <div class="card h-100">
              <div class="p-3 pb-0 card-header">
                <div class="row">
                  <div class="col-md-8 d-flex align-items-center">
                    <h6 class="mb-0">Answers</h6>
                  </div>
                  <div class="col-md-4 text-end">
                  
                  </div>
                </div>
              </div>
              <div class="p-3 card-body">
                <p class="text-sm">Enter Answers</p>
                <hr class="my-4 horizontal gray-light" />
                <ul class="list-group">
                    <label for="question_id">Question ID:</label>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="answer.question_id"
                  >
                    <option value="0">Select Quiz ID</option>
                    <option
                      v-for="question in questions"
                      v-bind:key="question.id"
                      :value="question.id"
                    >
                      {{ question.text }} ({{ question.id }})
                    </option>
                  </select>
                  <label for="correct">correct:</label>
                  <input
                  
                    type="boolean"
                    id="correct"
                    class="form-control mb-3"
                    placeholder="Fill the correct answer"
                  />
                  <label for="user_choice">User choice:</label>
                  <input
                   
                    type="text"
                    id="user_choice"
                    class="form-control mb-3"
                  />
                </ul>
              </div>
            </div>
          </div>
          <div class="mt-4 mx-3">
  
            <button @click="createQuiz" class="btn btn-primary">
              Create Quiz
            </button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import QuizService from "@/app/services/ProductService";
  import AnswerService from "@/app/services/ProductService";
  import QuestionService from "@/app/services/ProductService";
  
  export default {
    data() {
      return {
        quizes: [],
        answers: [],
        questions: [],
  
        quiz: {
            title:"",
        },
        question: {
            quiz_id :"",
            text:"",
            point :"",
        },
        answer:{
            question_id: "",
            correct: "",
            user_choice: "",
        }
      };
    },
    computed: {
    },
    async mounted() {
      this.quizes = await QuizService.get();
      this.answers = await AnswerService.get();
      this.questions = await QuestionService.get();
    },
    methods: {
      saveProduct() {
        console.log("Created Quiz:", this.CreateQuiz);
      },
    },
  };
  </script>
  