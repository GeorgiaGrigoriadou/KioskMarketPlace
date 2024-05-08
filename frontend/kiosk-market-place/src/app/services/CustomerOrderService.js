import { myaxios } from "@/app/util/myaxios";

import {
    GET_CUSTOMER_ORDERS_ENDPOINT,
    GET_CUSTOMER_ORDERS_BY_ID_ENDPOINT,
    CREATE_CUSTOMER_ORDERS_ENDPOINT,
    DELETE_CUSTOMER_ORDERS_ENDPOINT,
    PATCH_CUSTOMER_ORDERS_ENDPOINT,
 
} from "@/app/api/endpoints.js";

class CustomerOrderService {
  async get() {
    let response = await myaxios.get(GET_CUSTOMER_ORDERS_ENDPOINT);

    let customerOrder = response.data;
    return customerOrder;
    

  }

  async getByID(id) {
    let response = await myaxios.get(GET_CUSTOMER_ORDERS_BY_ID_ENDPOINT + id);

    let customerOrder = response.data[0];
    console.log(customerOrder);

    return customerOrder;
  }

  async create(data) {
    console.log(data);
    let response = await myaxios.post(CREATE_CUSTOMER_ORDERS_ENDPOINT, data);
    console.log(response);

    let customerOrder = response.data[0];
    return customerOrder;
  }

  async patch(id, data) {
    let response = await myaxios.patch(PATCH_CUSTOMER_ORDERS_ENDPOINT + id, data);

    let customerOrder = response.data[0];

    return customerOrder;
}


  async delete(id) {
    let response = await myaxios.delete(DELETE_CUSTOMER_ORDERS_ENDPOINT + id);

    if (response.status == 200) {
      return true;
    } else {
      return false;
    }
  }
}

export default new CustomerOrderService();
