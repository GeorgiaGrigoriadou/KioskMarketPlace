import { myaxios } from "@/app/util/myaxios";

import {
    GET_ORDER_STATUS_ENDPOINT,
    GET_ORDER_STATUS_BY_ID_ENDPOINT,
    PATCH_ORDER_STATUS_ENDPOINT,
    CREATE_ORDER_STATUS_ENDPOINT,
    DELETE_ORDER_STATUS_ENDPOINT,
} from "@/app/api/endpoints.js";

class OrderStatusService {
    async get() {
        let response = await myaxios.get(GET_ORDER_STATUS_ENDPOINT);

        let status = response.data;
        let sortedDescription = status.sort((a, b) => {
            let fa = a.description.toLowerCase(),
              fb = b.description.toLowerCase();
            if (fa < fb) {
              return -1;
            }
            if (fa > fb) {
              return 1;
            }
            return 0;
          });
          console.log(sortedDescription);

          return sortedDescription;  
       
    }

    async getByID(id) {
        let response = await myaxios.get(GET_ORDER_STATUS_BY_ID_ENDPOINT + id);

        let status = response.data[0];

        return status;
    }

    async create(data) {
        let response = await myaxios.post(CREATE_ORDER_STATUS_ENDPOINT, data);

        let status = response.data[0];

        return status;
    }

    async patch(id, data) {
        let response = await myaxios.patch(PATCH_ORDER_STATUS_ENDPOINT + id, data);

        let status = response.data[0];

        return status;
    }

    async delete(id) {
        let response = await myaxios.delete(DELETE_ORDER_STATUS_ENDPOINT + id);

        if (response.status == 200) {
            return true;
        } else {
            return false;
        }
    }
}

export default new OrderStatusService();
