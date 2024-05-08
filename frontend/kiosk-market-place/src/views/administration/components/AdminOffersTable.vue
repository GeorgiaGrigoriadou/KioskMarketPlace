<template>
  <div class="card mb-4">
    <div class="card-header pb-0">
      <h6>Offers table</h6>
    </div>
    <div class="card-body px-0 pt-0 pb-2">
      <div class="table-responsive p-0">
        <table>
          <tr>
            <th>
              <input v-model="filters.id" placeholder="Search by ID..." class="form-control mb-3 mx-3" />
            </th>
            <th>
              <input v-model="filters.discount" placeholder="Search by Discount..." class="form-control mb-3 mx-3" />
            </th>
            <th>
              <input v-model="filters.points" placeholder="Search by Points..." class="form-control mb-3 mx-3" />
            </th>
          </tr>
        </table>

        <div class="mb-3 mx-3">
          <button @click="createOffer" class="btn btn-primary">Create Offer</button>
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
                <li v-for="i in totalPages" v-bind:key="i" class="page-item"
                  :class="{ active: i - 1 == pagination.currentPage }"><a class="page-link"
                    @click.prevent="selectPage(i - 1)"> {{ i }}</a></li>
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
              <th>Discount</th>
              <th>Points</th>
              <th>Date Start</th>
              <th>Day End</th>
              <th>Created At</th>
              <th>Edit</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="specialOffer in paginatedOffers" :key="specialOffer.id">
              <td>
                <div class="d-flex flex-column justify-content-center">
                  <h6 class="mb-0 text-sm">{{ specialOffer.id }}</h6>
                </div>
              </td>
              <td>
                <p class="text-xs font-weight-bold mb-0">{{ specialOffer.discount }}</p>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">
                  {{ specialOffer.points }}
                </p>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">{{ moment(specialOffer.offerDateStart).format(
                  "YYYY/MM/DD [&nbsp;] HH:mm"
                ) }}</span>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">{{ moment(specialOffer.offerDateEnd).format(
                  "YYYY/MM/DD [&nbsp;] HH:mm"
                ) }}</span>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">
                  {{
                    moment(specialOffer.createdAt).format(
                      "YYYY/MM/DD [&nbsp;] HH:mm"
                    )
                  }}
                </span>
              </td>
              <td class="align-middle">
                <router-link :to="{ name: 'EditOffer', params: { id: specialOffer.id } }">
                  <font-awesome-icon class="px-1" title="Edit offer" icon="fa-pen-to-square" />
                </router-link>

                <a href="#">
                  <font-awesome-icon @click.prevent="deleteOffer(specialOffer.id)" class="px-1" title="Delete product"
                    icon="fa-trash" />
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
import moment from "moment";
import OfferService from "@/app/services/OfferService";

export default {
  name: "admin-offers-table",
  components: {
  },
  data() {
    return {
      specialOffers: [],
      moment: moment,
      filters: {
        id: "",
        discount: "",
        points: "",
      },
      pagination: {
        currentPage: 0,
        pageSize: 3,
      },
    };
  },
  computed: {
    filteredOffers() {
      const filterId = this.filters.id;
      const filterDiscount = this.filters.discount;
      const filterPoints = this.filters.points;

      let offerList = this.specialOffers.filter((specialOffer) => {
        let match = true;

        if (filterId != "" && specialOffer.id != filterId) {
          match = false;
        }

        if (filterDiscount != "" && specialOffer.discount != filterDiscount) {
          match = false;
        }

        if (filterPoints != "" && specialOffer.points != filterPoints) {
          match = false;
        }

        return match;
      });

      return offerList;
    },
    paginatedOffers() {
      const startIndex = this.pagination.currentPage * this.pagination.pageSize;
      const endIndex = startIndex + this.pagination.pageSize;
      
      return this.filteredOffers.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.filteredOffers.length / this.pagination.pageSize);
    },
  },
  watch: {
      filteredOffers: {
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
    async getOffers() {
     this.specialOffers = await OfferService.get();
       

    },
    editOffer(specialOffer) {
      this.$router.push({ name: "EditOffer", params: { id: specialOffer.id } });
    },
    createOffer() {
      this.$router.push({ name: 'CreateOffer' });
    },
    async deleteOffer(id) {
      try {
        await OfferService.delete(id);
        this.getOffers();
      } catch (e) {
        alert(
          "Offer could not be deleted. Please check that is is not already used."
        );
      }
    },
  },
  async mounted() {
    this.getOffers();
  },
};
</script>
