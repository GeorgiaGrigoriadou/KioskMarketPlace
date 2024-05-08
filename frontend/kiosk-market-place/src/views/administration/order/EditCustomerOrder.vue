<template>
    <div class="container-fluid">
      <div class="py-4 container-fluid">
        <div class="mt-3 row">
          <div class="mt-4 col-12 col-md-6 col-xl-4 mt-md-0">
            <h3>Edit Order</h3>
          </div>
        </div>
        <div class="mt-3 row">
          <div class="mt-4 col-12 col-md-6 col-xl-8 mt-md-0">
            <div class="card h-100">
              <div class="p-3 pb-0 card-header">
                <div class="row">
                  <div class="col-md-8 d-flex align-items-center">
                    <h6 class="mb-0">Order details</h6>
                  </div>
                  <div class="col-md-4 text-end">
                  </div>
                </div>
              </div>
              <div class="p-3 card-body">
                <p class="text-sm">Enter the status of the order</p>
                <hr class="horizontal gray-light" />
                <ul class="list-group">
                  <label for="status_id">Status Order :</label>
                  <select class="form-select" aria-label="Default select example" v-model="customerOrder.orderStatus.id">
                    <option value="0">Select order status</option>
                    <option v-for="orderStatus in orderStatuses" v-bind:key="orderStatus.id" :value="orderStatus.id">
                      {{ orderStatus.description }} 
                    </option>
                  </select>
                  <label for="order">Order ID:</label>
                  <input v-model="customerOrder.id" type="text" id="order" class="form-control mb-3" readonly/>
                  <label for="customer">Customer Name:</label>
                  <input v-model="customerOrder.customer.name" type="text" id="customer" class="form-control mb-3" readonly/>
                  <label for="price">Total Price:</label>
                  <input v-model="customerOrder.totalPrice" type="text" id="price" class="form-control mb-3" readonly/>
                  <label for="createdAt">Created At:</label>
                  <input v-model="customerOrder.createdAt" type="text" id="createdAt" class="form-control mb-3" readonly />
                </ul>
              </div>
            </div>
          </div>
          <div class="mt-4 mx-3">
  
            <button v-if="formValid" @click="saveOrder" class="btn btn-primary">
              Save changes
            </button>
            <button v-else disabled class="btn btn-secondary">
              Please fill the form above
            </button>
          </div>
          <div v-if="submitted" class="mt-4 px -6 alert alert-success" role="alert">
            <font-awesome-icon icon="circle-check" />
  
            Status of the order updated successfully
          </div>
        </div>
      </div>
    </div>
  </template>
    
  <script>

  import customerOrderService from  "@/app/services/CustomerOrderService";
  import orderStatusService from "@/app/services/OrderStatusService";

  export default {
    data() {
      return {
        orderStatuses:[],
       

        customerOrder: {
          id: "...",
          totalPrice: "...",
          orderStatus:{
            id:"0",
            description:"...",
            createdAt:"...",
          },
          createdAt: "...",
          customer: {
            id: 5,
            username: "...",
            name: "...",
            phone: "...",
            email: "...",
            total_points: "...",
            is_administrator: "...",
            createdAt: "..."
          },
         
          
        },
        submitted: false,
      };
    },
    computed: {
      formValid() {
        return this.customerOrder.orderStatus.id != "0";
      }
    },
    async mounted() {
      this.customerOrder.id = this.$route.params.id;
      this.orderStatuses = await orderStatusService.get();
      this.getOrders();  
  
    },
    methods: {
      async getOrders() {
        this.customerOrder = await customerOrderService.getByID(this.customerOrder.id);
      },
      async saveOrder() {
        let body = {
          "order_status_id": this.customerOrder.orderStatus.id,
          "total_price": this.customerOrder.totalPrice,
        };
  
        this.submitted = false;
  
        try {
          await customerOrderService.patch(this.customerOrder.id, body);
          this.submitted = true;
        } catch (e) {
          alert(e);
          this.submitted = false;
        }
      },
    },
  };
  </script>
    