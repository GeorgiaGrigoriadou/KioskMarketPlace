<template>
  <div class="card mb-4">
    <div class="card-header pb-0">
      <h6>Customer Orders table</h6>
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
                v-model="filters.totalPrice"
                placeholder="Search by price..."
                class="form-control mb-3 mx-3"
              />
            </th>
            <th>
              <select
                class="form-select form-control mb-3 mx-3"
                aria-label="Default select example"
                v-model="filters.orderStatus"
              >
                <option value="0">Select order status</option>
                <option
                  v-for="orderStatus in orderStatuses"
                  v-bind:key="orderStatus.id"
                  :value="orderStatus.id"
                >
                  {{ orderStatus.description }}
                </option>
              </select>
            </th>
          </tr>
        </table>

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
              <th>Order Status</th>
              <th>Products ID</th>
              <th>Total price </th>
              <th>Customer ID </th>
              <th>Day/Time</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="customerOrder in paginatedOrders" :key="customerOrder.id">
              <td>
                <div class="d-flex flex-column justify-content-center">
                  <h6 class="mb-0 text-sm">{{ customerOrder.id }}</h6>
                </div>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">
                  {{ customerOrder.orderStatus.description }}
                </p>
              </td>
              <td class="align-middle text-center">
                <span
                  v-for="product in customerOrder.details"
                  :key="product.id"                
                  >{{ product.name }}, </span
                >
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold"
                  >&euro;{{ customerOrder.totalPrice }}</span
                >
              </td>
              <td>
                <div class="d-flex flex-column justify-content-center">
                  <h6 class="mb-0 text-sm">{{ customerOrder.customer.name }}</h6>
                </div>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">
                  {{
                    moment(customerOrder.createdAt).format(
                      "YYYY/MM/DD [&nbsp;] HH:mm"
                    )
                  }}
                </span>
              </td>
              <td class="align-middle">
                <router-link :to="{ name: 'EditCustomerOrder', params: { id: customerOrder.id } }">
                  <font-awesome-icon class="px-1" title="Edit customer order" icon="fa-solid fa-magnifying-glass" /> 
                </router-link>

                <!-- <a href="#">
                  <font-awesome-icon @click.prevent="deleteOffer(specialOffer.id)" class="px-1" title="Delete product"
                    icon="fa-trash" />
                </a> -->
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
  GET_ORDER_STATUS_ENDPOINT,
} from "@/app/api/endpoints.js";
import moment from "moment";
import CustomerOrderService from "../../../app/services/CustomerOrderService";

export default {
  name: "admin-orders-table",
  components: {
    
  },
  data() {
    return {
      customerOrders: [],
      orderStatuses: [],
      moment: moment,
      filters: {
        id: "",
        totalPrice: "",
        orderStatus: "0",
      },
      pagination: {
        currentPage: 0,
        pageSize: 5,
      },
    };
  },
  computed: {
    filteredCustomerOrders() {
      const filterId = this.filters.id.toLowerCase();
      const filterTotalPrice = this.filters.totalPrice;
      const filterOrderStatus = this.filters.orderStatus;

      let customerOrdersList = this.customerOrders.filter((customerOrder) => {
        let match = true;

        if (filterId != "" && customerOrder.id != filterId) {
          match = false;
        }

        if (
          filterTotalPrice != "" &&
          customerOrder.totalPrice.includes(filterTotalPrice)
        ) {
          match = false;
        }

        if (
          filterOrderStatus != "0" &&
          customerOrder.orderStatus.id != filterOrderStatus
        ) {
          match = false;
        }

        return match;
      });

      return customerOrdersList;
    },
    paginatedOrders() {
      const startIndex = this.pagination.currentPage * this.pagination.pageSize;
      const endIndex = startIndex + this.pagination.pageSize;
      
      return this.filteredCustomerOrders.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.filteredCustomerOrders.length / this.pagination.pageSize);
    },
  },
  watch: {
    filteredCustomerOrders: {
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
    async getCustomerOrders() {
      this.customerOrders = await CustomerOrderService.get();

      this.axios.get(GET_ORDER_STATUS_ENDPOINT).then((response) => {
        let descriptions = response.data;

        let sortedDescription = descriptions.sort((a, b) => {
          let fa = a.description.toLowerCase(),
            fb = b.description.toLowerCase();
          if (fa < fb) {
            return -1;
          }
          if (fa > fb) {
            return 1;
          }
          return 0;
        });

        this.orderStatuses = sortedDescription;
      });
    },
    editCustomerOrder(customerOrder) {
      this.$router.push({ name: "EditOrder", params: { id: customerOrder.id } });
    },
  },
  mounted() {
    this.getCustomerOrders();
  },
};
</script>
