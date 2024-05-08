/**
=========================================================
* Vue Soft UI Dashboard - v3.0.0
=========================================================

* Product Page: https://creative-tim.com/product/vue-soft-ui-dashboard
* Copyright 2022 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/

import axios from 'axios'
import VueAxios from 'vue-axios'
import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import SoftUIDashboard from "./soft-ui-dashboard";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faUserSecret, faSmoking, faIceCream, faTag, faList, faBars, faBolt, faCookieBite, faWineBottle, faTableList, faCircleCheck, faCircleExclamation, faPenToSquare, faTrash, faCartShopping, faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

library.add(faUserSecret, faSmoking, faTag, faList, faBars, faBolt, faCookieBite, faWineBottle, faIceCream, faTableList, faCircleCheck, faCircleExclamation, faPenToSquare, faTrash,faCartShopping,faMagnifyingGlass);

const appInstance = createApp(App).component(
  "font-awesome-icon",
  FontAwesomeIcon
);
appInstance.use(store);
appInstance.use(router);
appInstance.use(SoftUIDashboard);
appInstance.use(VueAxios, axios)
appInstance.mount("#app");
