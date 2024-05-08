import { myaxios } from "@/app/util/myaxios";

import {
    GET_ORDER_HAS_PRODUCT_ENDPOINT,
    GET_ORDER_HAS_PRODUCT_BY_ID_ENDPOINT,
    GET_CREATE_ORDER_HAS_PRODUCT_ENDPOINT,
    GET_DELETE_ORDER_HAS_PRODUCT_ENDPOINT,
 
} from "@/app/api/endpoints.js";

class OrderHasProductService {
  async get() {
    let response = await myaxios.get(GET_ORDER_HAS_PRODUCT_ENDPOINT);

    let ohp = response.data;
    return ohp;

  }

  async getByID(id) {
    let response = await myaxios.get(GET_ORDER_HAS_PRODUCT_BY_ID_ENDPOINT + id);

    let ohp = response.data[0];

    return ohp;
  }

  async create(data) {
    console.log(data);
    let response = await myaxios.post(GET_CREATE_ORDER_HAS_PRODUCT_ENDPOINT, data);
    console.log(response);

    let ohp = response.data[0];
    

    return ohp;
  }


  async delete(id) {
    let response = await myaxios.delete(GET_DELETE_ORDER_HAS_PRODUCT_ENDPOINT + id);

    if (response.status == 200) {
      return true;
    } else {
      return false;
    }
  }
}

export default new OrderHasProductService();
