<template>
    <div class="container-fluid">
      <div class="py-4 container-fluid">
        <div class="mt-3 row">
          <div class="mt-4 col-12 col-md-6 col-xl-4 mt-md-0">
            <h3>Edit User</h3>
          </div>
        </div>
        <div class="mt-3 row">
          <!-- <div class="col-12 col-md-6 col-xl-4">
          </div> -->
          <div class="mt-4 col-12 col-md-6 col-xl-8 mt-md-0">
            <div class="card h-100">
              <div class="p-3 pb-0 card-header">
                <div class="row">
                  <div class="col-md-8 d-flex align-items-center">
                    <h6 class="mb-0">User details</h6>
                  </div>
                  <div class="col-md-4 text-end">
                    <!-- <a :href="action.route">
              <i
                class="text-sm fas fa-user-edit text-secondary"
                data-bs-toggle="tooltip"
                data-bs-placement="top"
                :title="action.tooltip"
              ></i>
            </a> -->
                  </div>
                </div>
              </div>
              <div class="p-3 card-body">
                <p class="text-sm">Enter the information of your profile</p>
                <hr class="horizontal gray-light" />
                <ul class="list-group">
                 
                  <label for="name">Name:</label>
                  <input v-model="user.name" type="text" id="name" class="form-control mb-3"
                    placeholder="Fill your name" />
                  <label for="username">Username:</label>
                  <input v-model="user.username" type="text" id="username" class="form-control mb-3"
                    placeholder="Fill your username" />
                  <label for="mobile">Mobile:</label>
                  <input v-model="user.phone" type="text" id="mobile" class="form-control mb-3"
                    placeholder="Fill your phone" />
                  <label for="email">Email:</label>
                  <input v-model="user.email" type="text" id="email" class="form-control mb-3"
                  placeholder="Fill your email" />
                </ul>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-xl-4">
            <div class="card h-100">
              <div class="p-3 pb-0 card-header">
                <div class="row">
                  <div class="col-md-8 d-flex align-items-center">
                    <h6 class="mb-0">Details</h6>
                  </div>
                  <div class="col-md-4 text-end">
                    <!-- <a :href="action.route">
              <i
                class="text-sm fas fa-user-edit text-secondary"
                data-bs-toggle="tooltip"
                data-bs-placement="top"
                :title="action.tooltip"
              ></i>
            </a> -->
                  </div>
                </div>
              </div>
              <div class="p-3 card-body">
                <hr class="my-4 horizontal gray-light" />
                <ul class="list-group">
                    <label for="id">ID:</label>
                  <input v-model="user.id" type="text" id="id" class="form-control mb-3" readonly />
                  <label for="total_points">Total Points:</label>
                  <input v-model="user.total_points" type="number" id="total_points" class="form-control mb-3" readonly
                    placeholder="Fill the " />
                </ul>
              </div>
            </div>
          </div>
          <div class="mt-4 mx-3">
            <button v-if="formValid" @click="saveUser" class="btn btn-primary">
              Save changes
            </button>
            <button v-else disabled class="btn btn-secondary">
              Please fill the form above
            </button>
          </div>
          <div v-if="submitted" class="mt-4 px -6 alert alert-success" role="alert">
            <font-awesome-icon icon="circle-check" />
  
            User updated successfully
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import UserService from "../app/services/UserService";
  
  export default {
    data() {
      return {
        users: [],
  
        user: {
          id: "...",
          username: "...",
          name: "...",
          phone: "...",
          email: "",
          total_points: "",
          is_administrator: "...",
          },
        submitted: false,
      };
    },
    computed: {
      formValid() {
        return (
          this.user.username !=""&&
          this.user.name != "" &&
          this.user.phone != "" &&
          this.user.email != ""
        );
      },
    },
    async mounted() {
      this.user.id = this.$route.params.id;
      this.getUser();
    },
    methods: {
      async getUser() {
        this.user = await UserService.getByID(this.user.id);
      },
      async saveUser() {
        let body = {
          "username": this.user.username,
          "name": this.user.name,
          "phone": this.user.phone,
          "email": this.user.email,
        };
  
        this.submitted = false;
  
        try {
          await UserService.patch(this.user.id, body);
          this.submitted = true;
        } catch (e) {
          alert(e);
          this.submitted = false;
        }
      },
    },
  };
  </script>
  