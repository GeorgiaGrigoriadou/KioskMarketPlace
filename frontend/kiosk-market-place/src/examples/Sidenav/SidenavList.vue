<template>
  <div
    class="w-auto h-auto collapse navbar-collapse max-height-vh-100 h-100"
    id="sidenav-collapse-main"
  >
    <ul class="navbar-nav">
      <!-- <li class="nav-item">
        <sidenav-collapse navText="Dashboard" :to="{ name: 'Dashboard' }">
          <template #icon>
            <shop />
          </template>
        </sidenav-collapse>
      </li> -->
      <!-- <li class="nav-item">
        <sidenav-collapse navText="Tables" :to="{ name: 'Tables' }">
          <template #icon>
            <office />
          </template>
        </sidenav-collapse>
      </li> -->
      <!-- <li class="nav-item">
        <sidenav-collapse navText="Billing" :to="{ name: 'Billing' }">
          <template #icon>
            <credit-card />
          </template>
        </sidenav-collapse>
      </li> -->

      <!-- <li class="nav-item">
        <sidenav-collapse
          navText="Virtual Reality"
          :to="{ name: 'Virtual Reality' }"
        >
          <template #icon>
            <box3d />
          </template>
        </sidenav-collapse>
      </li> -->
      <li class="nav-item">
        <sidenav-collapse navText="Quiz" :to="{ name: 'Quiz' }">
          <template #icon>
            <office />
          </template>
        </sidenav-collapse>
      </li>
      <!-- <li class="nav-item">
        <sidenav-collapse navText="RTL" :to="{ name: 'Rtl' }">
          <template #icon>
            <settings />
          </template>
        </sidenav-collapse>
      </li> -->
      <li class="mt-3 nav-item">
        <h6
          class="text-xs ps-4 text-uppercase font-weight-bolder opacity-6"
          :class="this.$store.state.isRTL ? 'me-4' : 'ms-2'"
        >
          PAGES
        </h6>
      </li>
      <!-- <li class="nav-item">
        <sidenav-collapse navText="Profile" :to="{ name: 'Profile' }">
          <template #icon>
            <customer-support />
          </template>
        </sidenav-collapse>
      </li> -->
      <li class="nav-item">
        <sidenav-collapse navText="User" :to="{ name: 'User' }">
          <template #icon>
            <customer-support />
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse navText="Sign In" :to="{ name: 'Sign In' }">
          <template #icon>
            <document />
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse navText="Sign Up" :to="{ name: 'Sign Up' }">
          <template #icon>
            <spaceship />
          </template>
        </sidenav-collapse>
      </li>
      <div v-if="isAdministrator">
        <li class="mt-3 nav-item">
          <h6
            class="text-xs ps-4 text-uppercase font-weight-bolder opacity-6"
            :class="this.$store.state.isRTL ? 'me-4' : 'ms-2'"
          >
            Administration
          </h6>
        </li>
        <li class="nav-item">
          <sidenav-collapse navText="Products" :to="{ name: 'ListProducts' }">
            <template #icon>
              <font-awesome-icon icon="fa-solid fa-table-list" />
            </template>
          </sidenav-collapse>
        </li>
        <li class="nav-item">
          <sidenav-collapse navText="Offers" :to="{ name: 'Offers' }">
            <template #icon>
              <font-awesome-icon icon="fa-solid fa-tag" />
            </template>
          </sidenav-collapse>
        </li>
        <li class="nav-item">
          <sidenav-collapse navText="Orders" :to="{ name: 'Orders' }">
            <template #icon>
              <font-awesome-icon icon="fa-solid fa-list" />
            </template>
          </sidenav-collapse>
        </li>
        <li class="nav-item">
          <sidenav-collapse navText="Quizes" :to="{ name: 'Quizes' }">
            <template #icon>
              <font-awesome-icon icon="fa-solid fa-bars" />
            </template>
          </sidenav-collapse>
        </li>
      </div>

      <li class="mt-3 nav-item">
        <h6
          class="text-xs ps-4 text-uppercase font-weight-bolder opacity-6"
          :class="this.$store.state.isRTL ? 'me-4' : 'ms-2'"
        >
          Categories
        </h6>
      </li>
      <li
        v-for="productCategory in productCategories"
        v-bind:key="productCategory.id"
        class="nav-item"
      >
        <sidenav-collapse
          :navText="productCategory.name"
          :to="{
            name: 'UserListProducts',
            params: { category_id: productCategory.id },
          }"
        >
          <template #icon>
            <font-awesome-icon :icon="getIcon(productCategory.id)" />
          </template>
        </sidenav-collapse>
      </li>
    </ul>
  </div>
  <div class="pt-3 mx-3 mt-3 sidenav-footer">
    <sidenav-card
      :class="cardBg"
      textPrimary="Need Help?"
      textSecondary="Please check our docs"
      route="https://www.creative-tim.com/learning-lab/vue/overview/soft-ui-dashboard/"
      label="Documentation"
      icon="ni ni-diamond"
    />
    <a
      class="btn bg-gradient-success mt-4 w-100"
      href="https://www.creative-tim.com/product/vue-soft-ui-dashboard-pro?ref=vsud"
      type="button"
      >Upgrade to pro</a
    >
  </div>
</template>
<script>
import SidenavCollapse from "./SidenavCollapse.vue";
import SidenavCard from "./SidenavCard.vue";
//import Shop from "../../components/Icon/Shop.vue";
import Office from "../../components/Icon/Office.vue";
//import CreditCard from "../../components/Icon/CreditCard.vue";
//import Box3d from "../../components/Icon/Box3d.vue";
import CustomerSupport from "../../components/Icon/CustomerSupport.vue";
import Document from "../../components/Icon/Document.vue";
import Spaceship from "../../components/Icon/Spaceship.vue";
//import Settings from "../../components/Icon/Settings.vue";
import productCategoryService from "@/app/services/ProductCategoryService";
import authenticationService from "@/app/services/AuthenticationService";

export default {
  name: "SidenavList",
  props: {
    cardBg: String,
  },
  data() {
    return {
      title: "Soft UI Dashboard PRO",
      controls: "dashboardsExamples",
      isActive: "active",
      productCategories: [],
    };
  },
  components: {
    SidenavCollapse,
    SidenavCard,
    //Shop,
    Office,
    //CreditCard,
    //Box3d,
    CustomerSupport,
    Document,
    Spaceship,
    // Settings,
  },

  async mounted() {
    this.productCategories = await productCategoryService.get(true);
  },
  computed: {
    isAdministrator() {
      let x = authenticationService.isAdministrator();
      console.log(x);
      return x;
    },
  },
  methods: {
    getRoute() {
      const routeArr = this.$route.path.split("/");
      return routeArr[1];
    },
    getIcon(id) {
      if (id == 1) {
        return "fa-solid fa-smoking";
      }
      if (id == 2) {
        return "fa-solid fa-smoking";
      }
      if (id == 3) {
        return "fa-solid fa-smoking";
      }
      if (id == 4) {
        return "fa-solid fa-cookie-bite";
      }
      if (id == 5) {
        return "fa-solid fa-wine-bottle";
      }
      if (id == 6) {
        return "ice-cream";
      }
      if (id == 7) {
        return "fa-solid fa-bolt";
      }
    },
  },
};
</script>
