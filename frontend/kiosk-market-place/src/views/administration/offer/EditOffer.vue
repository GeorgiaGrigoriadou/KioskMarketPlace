<template>
  <div class="container-fluid">
    <div class="py-4 container-fluid">
      <div class="mt-3 row">
        <div class="mt-4 col-12 col-md-6 col-xl-4 mt-md-0">
          <h3>Edit Offer</h3>
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
                <input v-model="specialOffer.points" type="text" id="discount" class="form-control mb-3"
                  placeholder="Fill the points of the offer" />
                <label for="points">Date Start:</label>
                <input v-model="specialOffer.offerDateStart" type="text" id="dateStart" class="form-control mb-3"
                  placeholder="Fill the DateStart of the offer" />
                <label for="points">Date End:</label>
                <input v-model="specialOffer.offerDateEnd" type="text" id="dateEnd" class="form-control mb-3"
                  placeholder="Fill the DateEnd of the offer" />
              </ul>
            </div>
          </div>
        </div>
        <div class="mt-4 mx-3">

          <button v-if="formValid" @click="saveOffer" class="btn btn-primary">
            Save changes
          </button>
          <button v-else disabled class="btn btn-secondary">
            Please fill the form above
          </button>
        </div>
        <div v-if="submitted" class="mt-4 px -6 alert alert-success" role="alert">
          <font-awesome-icon icon="circle-check" />

          Product updated successfully
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import ProductService from "@/app/services/ProductService";
import OfferService from "@/app/services/OfferService";

export default {
  data() {
    return {
      products: [],

      specialOffer: {
        id: "...",
        product_id: "...",
        discount: "0",
        points: "0",
        createdAt: "...",
        offerDateStart: "...",
        offerDateEnd: "..."
      },
      submitted: false,
    };
  },
  computed: {
    formValid() {
      return this.specialOffer.product_id != "0";
    }
  },
  async mounted() {
    this.specialOffer.id = this.$route.params.id;
    this.products = await ProductService.get();
    this.getOffer();  

  },
  methods: {
    async getOffer() {
      this.specialOffer = await OfferService.getByID(this.specialOffer.id);
    },
    async saveOffer() {
      let body = {
        "product_id": this.specialOffer.product_id,
        "discount": this.specialOffer.discount,
        "points": this.specialOffer.points,
        "offerDateStart": this.specialOffer.offerDateStart,
        "offerDateEnd": this.specialOffer.offerDateEnd,
      };

      this.submitted = false;

      try {
        await OfferService.patch(this.specialOffer.id, body);
        this.submitted = true;
      } catch (e) {
        alert(e);
        this.submitted = false;
      }
    },
  },
};
</script>
  