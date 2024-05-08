import { myaxios } from "@/app/util/myaxios";

import {
    GET_BASKET_HAS_PRODUCT_ENDPOINT,
    GET_BASKET_HAS_PRODUCT_BY_ID_ENDPOINT,
    GET_CREATE_BASKET_HAS_PRODUCT_ENDPOINT,
    GET_DELETE_BASKET_HAS_PRODUCT_ENDPOINT,
 
} from "@/app/api/endpoints.js";

class BasketHasProductService {
  async get() {
    let response = await myaxios.get(GET_BASKET_HAS_PRODUCT_ENDPOINT);

    let bhp = response.data;
    return bhp;
    

  }

  async getByID(id) {
    let response = await myaxios.get(GET_BASKET_HAS_PRODUCT_BY_ID_ENDPOINT + id);

    let bhp = response.data[0];

    return bhp;
  }

  async create(data) {
    console.log(data);
    let response = await myaxios.post(GET_CREATE_BASKET_HAS_PRODUCT_ENDPOINT, data);
    console.log(response);

    let bhp = response.data[0];
    

    return bhp;
  }


  async delete(id) {
    let response = await myaxios.delete(GET_DELETE_BASKET_HAS_PRODUCT_ENDPOINT + id);

    if (response.status == 200) {
      return true;
    } else {
      return false;
    }
  }
}

export default new BasketHasProductService();
