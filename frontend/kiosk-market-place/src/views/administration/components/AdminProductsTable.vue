<template>
  <div class="card mb-4">
    <div class="card-header pb-0">
      <h6>Products table</h6>
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
                v-model="filters.name"
                placeholder="Search by Name..."
                class="form-control mb-3 mx-3"
              />
            </th>
            <th>
              <select
                class="form-select form-control mb-3 mx-3"
                aria-label="Default select example"
                v-model="filters.category_id"
              >
                <option value="0">Select category</option>
                <option
                  v-for="category in productCategories"
                  v-bind:key="category.id"
                  :value="category.id"
                >
                  {{ category.name }} ({{ category.totalProducts }})
                </option>
              </select>
            </th>
          </tr>
        </table>

        <div class="mb-3 mx-3">
          <button @click="createProduct" class="btn btn-primary">
            Create Product
          </button>
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
                  <a class="page-link" @click.prevent="selectFirstPage()"
                    >&lt;</a
                  >
                </li>
                <li
                  v-for="i in totalPages"
                  v-bind:key="i"
                  class="page-item"
                  :class="{ active: i - 1 == pagination.currentPage }"
                >
                  <a class="page-link" @click.prevent="selectPage(i - 1)">
                    {{ i }}</a
                  >
                </li>
                <li class="page-item">
                  <a class="page-link" @click.prevent="selectLastPage()"
                    >&gt;</a
                  >
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <table class="table align-items-center mb-0">
          <thead>
            <tr>
              <th>Image</th>
              <th>ID</th>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
              <th>Unit</th>
              <th>Cost</th>
              <th>Special Offers</th>
              <th>Category</th>
              <th>Day/Time</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="product in paginatedProducts" :key="product.id">
              <td>
                <div class="d-flex px-2 py-1">
                  <soft-avatar
                    :img="img1"
                    size="sm"
                    border-radius="lg"
                    class="me-3"
                    :alt="product.name"
                  />
                </div>
              </td>
              <td>
                <div class="d-flex flex-column justify-content-center">
                  <h6 class="mb-0 text-sm">{{ product.id }}</h6>
                </div>
              </td>
              <td>
                <p class="text-xs font-weight-bold mb-0">{{ product.name }}</p>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">
                  {{ product.description }}
                </p>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold"
                  >&euro;{{ product.price }}</span
                >
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">{{
                  product.measurementUnit.code
                }}</span>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold"
                  >&euro;{{ product.retail_cost }}</span
                >
              </td>
              <td class="align-middle text-center">
                <!-- <span
                  v-for="specialOffer in product.specialOffers"
                  :key="specialOffer.id"
                  class="badge rounded-pill bg-primary ms-1"
                  >{{ specialOffer.discount }}%</span
                > -->

                {{ product.specialOffers.length }}
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">{{
                  product.productCategory.name
                }}</span>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">
                  {{
                    moment(product.createdAt).format(
                      "YYYY/MM/DD [&nbsp;] HH:mm"
                    )
                  }}
                </span>
              </td>

              <td class="align-middle">
                <router-link
                  :to="{ name: 'EditProduct', params: { id: product.id } }"
                >
                  <font-awesome-icon
                    class="px-1"
                    title="Edit product"
                    icon="fa-pen-to-square"
                  />
                </router-link>

                <a href="#">
                  <font-awesome-icon
                    @click.prevent="deleteProduct(product.id)"
                    class="px-1"
                    title="Delete product"
                    icon="fa-trash"
                  />
                </a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import SoftAvatar from "@/components/SoftAvatar.vue";
import img1 from "../../../assets/img/team-2.jpg"; // Update the path to your image
import moment from "moment";
import productCategoryService from "@/app/services/ProductCategoryService";
import productService from "@/app/services/ProductService";

export default {
  name: "admin-products-table",
  components: {
    SoftAvatar,
  },
  data() {
    return {
      img1,
      products: [],
      productCategories: [],
      moment: moment,
      filters: {
        id: "",
        name: "",
        category_id: "0",
      },
      pagination: {
        currentPage: 0,
        pageSize: 10,
      },
    };
  },
  computed: {
    filteredProducts() {
      const filterId = this.filters.id.toLowerCase();
      const filterProductName = this.filters.name.toLowerCase();
      const filterCategoryId = this.filters.category_id;

      let productList = this.products.filter((product) => {
        let match = true;

        if (filterId != "" && product.id != filterId) {
          match = false;
        }

        if (
          filterProductName != "" &&
          !product.name.toLowerCase().includes(filterProductName)
        ) {
          match = false;
        }

        if (
          filterCategoryId != "0" &&
          product.productCategory.id != filterCategoryId
        ) {
          match = false;
        }

        return match;
      });

      return productList;
    },
    paginatedProducts() {
      const startIndex = this.pagination.currentPage * this.pagination.pageSize;
      const endIndex = startIndex + this.pagination.pageSize;
      
      return this.filteredProducts.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.filteredProducts.length / this.pagination.pageSize);
    },
  },
  watch: {
      filteredProducts: {
      deep: true,
      handler: function () {
        if (this.pagination.currentPage >= this.totalPages) {
          this.pagination.currentPage  = 0;
        }
      }
    }
  }
 , methods: {
    selectFirstPage() {
      this.pagination.currentPage = 0;
    },
    selectPage(n) {
      this.pagination.currentPage = n;
    },
    selectLastPage() {
      this.pagination.currentPage = this.totalPages - 1;
    },
    async getProducts() {
      this.products = await productService.get();
       
      this.productCategories = await productCategoryService.get(true);
      console.log(this.productCategories);
    },
    editProduct(product) {
      this.$router.push({ name: "EditProduct", params: { id: product.id } });
    },
    createProduct() {
      this.$router.push({ name: "CreateProduct" });
    },
    async deleteProduct(id) {
      try {
        await productService.delete(id);
        this.getProducts();
      } catch (e) {
        alert(
          "Product could not be deleted. Please check that is is not already used."
        );
      }
    },
  },
  mounted() {
    this.getProducts();
  },
};
</script>
