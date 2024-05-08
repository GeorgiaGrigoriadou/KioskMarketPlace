import { myaxios } from "@/app/util/myaxios";

import {
  GET_PRODUCTS_ENDPOINT,
  CREATE_PRODUCT_ENDPOINT,
  DELETE_PRODUCT_ENDPOINT,
  GET_PRODUCT_BY_ID_ENDPOINT,
  PATCH_PRODUCT_ENDPOINT,
} from "@/app/api/endpoints.js";

class ProductService {
  async get() {
    let response = await myaxios.get(GET_PRODUCTS_ENDPOINT);

    let products = response.data;

    console.log(products);

    let sortedProducts = products.sort((a, b) => {
      let fa = a.name.toLowerCase(),
        fb = b.name.toLowerCase();
      if (fa < fb) {
        return -1;
      }
      if (fa > fb) {
        return 1;
      }
      return 0;
    });

    return sortedProducts;
  }

  async getByID(id) {
    let response = await myaxios.get(GET_PRODUCT_BY_ID_ENDPOINT + id);

    let product = response.data[0];

    return product;
  }

  async create(data) {
    let response = await myaxios.post(CREATE_PRODUCT_ENDPOINT, data);

    let product = response.data[0];

    return product;
  }

  async patch(id, data) {
    let response = await myaxios.patch(PATCH_PRODUCT_ENDPOINT + id, data);

    let product = response.data[0];

    return product;
  }

  async delete(id) {
    let response = await myaxios.delete(DELETE_PRODUCT_ENDPOINT + id);

    if (response.status == 200) {
      return true;
    } else {
      return false;
    }
  }
}

export default new ProductService();
