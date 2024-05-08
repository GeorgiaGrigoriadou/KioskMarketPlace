<template>
  <div class="container-fluid">
    <div class="py-4 container-fluid">
      <div class="mt-3 row">
        <div class="mt-4 col-12 col-md-6 col-xl-4 mt-md-0">
          <h3>Create Offer</h3>
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
              <p class="text-sm">Enter the details of the offer</p>
              <hr class="horizontal gray-light" />
              <ul class="list-group">
                <label for="product_id">Product ID:</label>
                <select class="form-select" aria-label="Default select example" v-model="specialOffer.product_id">
                  <option value="0">Select product</option>
                  <option v-for="product in products" v-bind:key="product.id" :value="product.id">
                    {{ product.name }} ({{ product.id }})
                  </option>
                </select>
                <label for="discount">Discount:</label>
                <input v-model="specialOffer.discount" type="text" id="discount" class="form-control mb-3"
                  placeholder="Fill the discount of the offer" />
                <label for="points">Points:</label>
                <input v-model="specialOffer.points" type="text" id="points" class="form-control mb-3"
                  placeholder="Fill the points of the offer" />
                <label for="points">Date Start:</label>
                <input v-model="specialOffer.offerDateStart" type="datetime-local" id="dateStart" class="form-control mb-3"
                  placeholder="Fill the DateStart of the offer example : 1993-05-02T21:35:24 " />
                <label for="points">Date End:</label>
                <input v-model="specialOffer.offerDateEnd" type="datetime-local" id="dateEnd" class="form-control mb-3"
                  placeholder="Fill the DateEnd of the offer example : 1993-05-02T21:35:24" />
              </ul>
            </div>
          </div>
        </div>
        <div class="mt-4 mx-3">

          <button v-if="formValid" @click="createOffer" class="btn btn-primary">
            Create Offer
          </button>
          <button v-else disabled class="btn btn-secondary">
            Please fill the form above
          </button>
        </div>
        <div v-if="submitted" class="mt-4 px -6 alert alert-success" role="alert">
          <font-awesome-icon icon="circle-check" />

          Offer {{ offerCreated.discount }} created with ID:
          {{ offerCreated.id }} successfully!
        </div>

        <div class="modal" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <p>Modal body text goes here.</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
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
//import moment from "moment";
import OfferService from "@/app/services/OfferService";
import ProductService from "@/app/services/ProductService";

export default {
  data() {
    return {
      products: [],

      specialOffer: {
        product_id: "0",
        discount: "",
        points: "",
        offerDateStart: "",
        offerDateEnd: ""
      },

      submitted: false,
      offerCreated: {},
    };
  },
  computed: {
    formValid() {
      return (
        this.specialOffer.product_id != "0" &&
        this.specialOffer.discount != "" &&
        this.specialOffer.offerDateStart != "" &&
        this.specialOffer.offerDateEnd != ""
      );
    }
  },
  async mounted() {
    this.products = await ProductService.get();
  },
  methods: {
    async createOffer() {
      console.log("Created Offer:", this.CreateOffer);

      this.submitted = false;

      try {
        this.offerCreated = await OfferService.create(this.specialOffer);
        this.submitted = true;
      } catch (e) {
        alert(e);
        this.submitted = false;
      }
    },
  },
};
</script>
  