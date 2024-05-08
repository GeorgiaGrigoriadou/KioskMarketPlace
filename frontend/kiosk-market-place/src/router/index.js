import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "@/views/SignIn.vue";
import Tables from "@/views/Tables.vue";
import Billing from "@/views/Billing.vue";
import VirtualReality from "@/views/VirtualReality.vue";
import Profile from "@/views/Profile.vue";
import Rtl from "@/views/Rtl.vue";
import SignIn from "@/views/SignIn.vue";
import SignUp from "@/views/SignUp.vue";
import User from "@/views/User.vue";
import Quiz from "@/views/Quiz.vue";
import EditUser from "@/views/EditUser";

//
// Administrator - Product
//
import ListProducts from "@/views/administration/product/ListProducts.vue";
import EditProduct from "@/views/administration/product/EditProduct.vue";
import CreateProduct from "@/views/administration/product/CreateProduct.vue";

//
// Administrator - Offer
//
import Offers from "@/views/administration/offer/ListOffers.vue";
import EditOffer from "@/views/administration/offer/EditOffer.vue";
import CreateOffer from "@/views/administration/offer/CreateOffer.vue";

//
// Administrator - Order
//
import Orders from "@/views/administration/order/ListOrders.vue";
import EditCustomerOrder from "@/views/administration/order/EditCustomerOrder.vue"

//
// Administrator - Quiz
//
import Quizes from "@/views/administration/quiz/ListQuizes.vue";
import CreateQuiz from "@/views/administration/quiz/CreateQuiz.vue";

//
// User - Products
//
import UserListProducts from "@/views/user/product/ListProducts.vue";

const routes = [
  //  --------------------------------------------------------------------
  //                          Administration routes
  //  --------------------------------------------------------------------

  //product
  {
    path: "/admin/products",
    name: "ListProducts",
    component: ListProducts,
  },
  {
    path: "/admin/products/edit/:id",
    name: "EditProduct",
    component: EditProduct,
  },

  {
    path: "/admin/products/create",
    name: "CreateProduct",
    component: CreateProduct,
  },

  //offer
  {
    path: "/admin/offers",
    name: "Offers",
    component: Offers,
  },

  {
    path: "/admin/offers/edit/:id",
    name: "EditOffer",
    component: EditOffer,
  },

  {
    path: "/admin/offers/create",
    name: "CreateOffer",
    component: CreateOffer,
  },

  //order
  {
    path: "/admin/orders",
    name: "Orders",
    component: Orders,
  },

  {
    path: "/admin/orders/edit/:id",
    name: "EditCustomerOrder",
    component: EditCustomerOrder,
  },

  //quiz
  {
    path: "/admin/quizes",
    name: "Quizes",
    component: Quizes,
  },

  {
    path: "/admin/quizes/create",
    name: "CreateQuiz",
    component: CreateQuiz,
  },

  // {
  //   path: '/admin/quizes/edit/:id',
  //   name: 'EditQuiz',
  //   component: EditQuiz,
  // },

  //  --------------------------------------------------------------------
  //                              Guest routes
  //  --------------------------------------------------------------------

  {
    path: "/product/category/:category_id",
    name: "UserListProducts",
    component: UserListProducts,
  },

  //  --------------------------------------------------------------------
  //                           Authentication routes
  //  --------------------------------------------------------------------

  {
    path: "/user",
    name: "User",
    component: User,
  },

  {
    path: "/user/edit/:id",
    name: "EditUser",
    component: EditUser,
  },


  {
    path: "/profile",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/sign-in",
    name: "Sign In",
    component: SignIn,
  },
  {
    path: "/sign-up",
    name: "Sign Up",
    component: SignUp,
  },
  //  --------------------------------------------------------------------
  //                          Original template routes
  //  --------------------------------------------------------------------
  {
    path: "/",
    name: "/",
    redirect: "/dashboard",
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: Dashboard,
  },
  {
    path: "/tables",
    name: "Tables",
    component: Tables,
  },
  {
    path: "/billing",
    name: "Billing",
    component: Billing,
  },
  {
    path: "/virtual-reality",
    name: "Virtual Reality",
    component: VirtualReality,
  },

  {
    path: "/rtl-page",
    name: "Rtl",
    component: Rtl,
  },
  {
    path: "/quiz",
    name: "Quiz",
    component: Quiz,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
});

export default router;
