<template>
  <div class="container-fluid">
    <div class="py-4 container-fluid">
      <div class="mt-3 row">
        <div class="mt-4 col-12 col-md-6 col-xl-4 mt-md-0">
          <h3>Create Product</h3>
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
                  <h6 class="mb-0">Product details</h6>
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
              <p class="text-sm">Enter the details of the product</p>
              <hr class="horizontal gray-light" />
              <ul class="list-group">
                <label for="name">Name: <span v-if="product.name.trim() == ''"><font-awesome-icon class="text-danger" title="This field is required" icon="circle-exclamation" /></span></label>
                <input
                  v-model="product.name"
                  type="text"
                  id="name"
                  class="form-control mb-3"
                  placeholder="Fill the name of the product"
                />
                <label for="description">Description:</label>
                <input
                  v-model="product.description"
                  type="text"
                  id="description"
                  class="form-control mb-3"
                  placeholder="Fill the description of the product"
                />
                <label for="image">Image:</label>
                <input
                  v-model="product.img"
                  type="text"
                  id="image"
                  class="form-control mb-3"
                  placeholder="Fill the URL of the images of the product"
                />
                <label for="product category">Product Category:</label>
                <select
                  class="form-select"
                  aria-label="Default select example"
                  v-model="product.product_category_id"
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
              </ul>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-xl-4">
          <div class="card h-100">
            <div class="p-3 pb-0 card-header">
              <div class="row">
                <div class="col-md-8 d-flex align-items-center">
                  <h6 class="mb-0">Pricing</h6>
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
              <p class="text-sm">Enter the pricing data of the product</p>
              <hr class="my-4 horizontal gray-light" />
              <ul class="list-group">
                <label for="price">Price:</label>
                <input
                  v-model="product.price"
                  type="number"
                  id="price"
                  class="form-control mb-3"
                  placeholder="Fill the price of the product"
                />
                <label for="retail cost">Retail Cost:</label>
                <input
                  v-model="product.retail_cost"
                  type="number"
                  id="retail cost"
                  class="form-control mb-3"
                  placeholder="Fill the retail cost of the product"
                />
                <label for="measurement unit id">Measurement Unit Id:</label>
                <select class="form-select" aria-label="Default select example" v-model="product.measurement_unit_id">
                  <option value="0">Select Measurement Unit</option>
                  <option v-for="meeasurementUnit in measurementUnits" v-bind:key="meeasurementUnit.id" :value="meeasurementUnit.id">
                    {{ meeasurementUnit.code }} ({{ meeasurementUnit.id }})
                  </option>
                </select>
              </ul>
            </div>
          </div>
        </div>
        <div class="mt-4 mx-3">
          <button
            v-if="formValid"
            @click="createProduct"
            class="btn btn-primary"
          >
            Create Product
          </button>
          <button v-else disabled class="btn btn-secondary">
            Please fill the form above
          </button>
        </div>
        <div
          v-if="submitted"
          class="mt-4 px -6 alert alert-success"
          role="alert"
        >
          <font-awesome-icon icon="circle-check" />

          Product {{ productCreated.name }} created with ID:
          {{ productCreated.id }} successfully!
        </div>

        <div class="modal" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <p>Modal body text goes here.</p>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
                <button type="button" class="btn btn-primary">
                  Save changes
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import productCategoryService from "@/app/services/ProductCategoryService";
import productService from "@/app/services/ProductService";
import MeasurementUnitService from "@/app/services/MeasurementUnitService";

export default {
  data() {
    return {
      productCategories: [],
      measurementUnits:[],

      product: {
        measurement_unit_id: "1",
        product_category_id: "0",
        name: "",
        description: "",
        price: "",
        retail_cost: "",
      },

      submitted: false,
      productCreated: {},
    };
  },
  computed: {
    formValid() {
      return (
        this.product.measurement_unit_id !="0"&&
        this.product.product_category_id != "0" &&
        this.product.price != "" &&
        this.product.retail_cost != ""
      );
    },
  },
  async mounted() {
    this.productCategories = await productCategoryService.get(false);
    this.measurementUnits=await MeasurementUnitService.get();
  },
  methods: {
    async createProduct() {
      console.log("Created Product:", this.CreateProduct);

      this.submitted = false;
      
      try {
        this.productCreated = await productService.create(this.product);
        this.submitted = true;
      } catch (e) {
        alert(e);
        this.submitted = false;
      }
    },
  },
};
</script>
