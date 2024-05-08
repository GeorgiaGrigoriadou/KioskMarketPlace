<template>
    <div class="card mb-4">
      <div class="card-header pb-0">
        <h6>Quizes table</h6>
      </div>
      <div class="card-body px-0 pt-0 pb-2">
        <div class="table-responsive p-0">
          <table>
            <tr>
              <th>
                <input
                  v-model="filters.id"
                  placeholder="Search by ID..."
                  class="form-control mb-3 mx-3"
                />
              </th>
              <th>
                <input
                  v-model="filters.title"
                  placeholder="Search by title..."
                  class="form-control mb-3 mx-3"
                />
              </th>
              <th>
                <input
                  v-model="filters.totalPoints"
                  placeholder="Search by Total Points..."
                  class="form-control mb-3 mx-3"
                />
              </th>
            </tr>
          </table>
          
          <div class="mb-3 mx-3" >
           <button @click="createQuiz" class="btn btn-primary">Create Quiz</button>
          </div>
  
          <div class="d-flex bd-highlight">
            <div class="p-2 flex-grow-1 bd-highlight">&nbsp;</div>
            <div class="p-2 bd-highlight">
              {{ pagination.currentPage + 1 }} of {{ totalPages }}
            </div>
            <div class="p-2 bd-highlight">
              <nav aria-label="Page navigation example">
                <ul class="pagination">
                  <li class="page-item">
                    <a class="page-link" @click.prevent="selectFirstPage()">&lt;</a>
                  </li>
                  <li v-for="i in totalPages" v-bind:key="i" class="page-item" :class="{ active: i - 1 == pagination.currentPage }"><a class="page-link" @click.prevent="selectPage(i-1)"> {{ i }}</a></li>
                  <li class="page-item">
                    <a class="page-link" @click.prevent="selectLastPage()">&gt;</a>
                  </li>
                </ul>
              </nav>
            </div>
          </div>
          <table class="table align-items-center mb-0">
            <thead>
              <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Total Points</th>
                <th>Questions</th>
                <th>Points of Question</th>
                <th>Answers</th>
                <th>Correct Answer</th>
                <th>Edit</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="quiz in paginatedQuizes" :key="quiz.id">
                <td>
                  <div class="d-flex flex-column justify-content-center">
                    <h6 class="mb-0 text-sm">{{ quiz.id }}</h6>
                  </div>
                </td>
                <td>
                  <p class="text-xs font-weight-bold mb-0">{{ quiz.title }}</p>
                </td>
                <td>
                  <p class="text-xs text-secondary mb-0">
                    {{ quiz.totalPoints }}
                  </p>
                </td>
                <td class="align-middle text-center">
                  <span
                    v-for="question in quiz.questions"
                    :key="question.id"
                    class="text-xs font-weight-bold mb-0"
                    >{{ question.text }}</span
                  >
                </td>
                <td class="align-middle text-center">
                  <span
                    v-for="question in quiz.questions"
                    :key="question.id"
                    class="text-xs font-weight-bold mb-0"
                    >{{ question.points }},</span
                  >
                </td>
                <td class="align-middle text-center">
                  <span
                    v-for="answer in quiz.answers"
                    :key="answer.id"
                    class="text-xs font-weight-bold mb-0"
                    >{{ answer.user_choice }},</span
                  >
                </td>
                <td class="align-middle text-center">
                  <span
                    v-for="answer in quiz.answers"
                    :key="answer.id"
                    class="text-xs font-weight-bold mb-0"
                    >{{ answer.correct }},</span
                  >
                </td>
                <td class="align-middle">
                  <a
                    @click="editProduct(product)"
                    href="javascript:;"
                    class="text-secondary font-weight-bold text-xs"
                    data-toggle="tooltip"
                    data-original-title="Edit product"
                    >Edit</a
                  >
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </template>
  
  <script>

  import {
    GET_QUIZ_ENDPOINT,
  } from "@/app/api/endpoints.js";
  import moment from "moment";
  import QuizService from "@/app/services/QuizService"
  
  export default {
    name: "admin-quizes-table",
    components: {
    },
    data() {
      return {
        quizes: [],
        questions: [],
        moment: moment,
        filters: {
          id: "",
          title: "",
          totalPoints: "",
        },
        pagination: {
          currentPage: 0,
          pageSize: 3,
        },
      };
    },
    computed: {
      filteredQuizes() {
        const filterId = this.filters.id;
        const filterTitle = this.filters.title.toLowerCase();
        const filterTotalPoints = this.filters.totalPoints;
  
        let quizesList = this.quizes.filter((quiz) => {
          let match = true;
  
          if (filterId != "" && quiz.id != filterId) {
            match = false;
          }
  
          if (
            filterTitle != "" &&
            !quiz.title.toLowerCase().includes(filterTitle)
          ) {
            match = false;
          }
  
          if (
            filterTotalPoints != "" &&
            quiz.totalPoints != filterTotalPoints
          ) {
            match = false;
          }
  
          return match;
        });
  
        return quizesList;
      },
      paginatedQuizes() {
      const startIndex = this.pagination.currentPage * this.pagination.pageSize;
      const endIndex = startIndex + this.pagination.pageSize;
      
      return this.filteredQuizes.slice(startIndex, endIndex);
    },
      totalPages() {
        return Math.ceil(this.filteredQuizes.length / this.pagination.pageSize);
      },
    },
    watch: {
      filteredQuizes: {
      deep: true,
      handler: function () {
        if (this.pagination.currentPage >= this.totalPages) {
          this.pagination.currentPage  = 0;
        }
      }
    }
  },
    methods: {
      selectFirstPage() {
        this.pagination.currentPage = 0;
      },
      selectPage(n) {
        this.pagination.currentPage = n;
      },
      selectLastPage() {
        this.pagination.currentPage = this.totalPages - 1;
      },
      async getQuizes() {
        this.axios.get(GET_QUIZ_ENDPOINT).then((response) => {
          this.quizes = response.data;
          console.log(this.quizes);
        });
  
        this.questions = await QuizService.get();
        console.log(this.questions);
      },
      editProduct(quiz) {
        this.$router.push({ name: "EditQuiz", params: { id: quiz.id } });
      },
      createQuiz() {
        this.$router.push({ name: 'CreateQuiz' });
    },
    },
    mounted() {
      this.getQuizes();
    },
  };
  </script>