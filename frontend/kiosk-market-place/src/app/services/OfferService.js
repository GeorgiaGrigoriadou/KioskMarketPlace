import { myaxios } from "@/app/util/myaxios";

import {
    GET_SPECIAL_OFFER_ENDPOINT,
    GET_SPECIAL_OFFER_BY_ID_ENDPOINT,
    PATCH_SPECIAL_OFFER_ENDPOINT,
    CREATE_SPECIAL_OFFER_ENDPOINT,
    DELETE_SPECIAL_OFFER_ENDPOINT,
} from "@/app/api/endpoints.js";

class OfferService {
    async get() {
        let response = await myaxios.get(GET_SPECIAL_OFFER_ENDPOINT);

        let offers = response.data;
        return offers;

        
    }

    async getByID(id) {
        let response = await myaxios.get(GET_SPECIAL_OFFER_BY_ID_ENDPOINT + id);

        let offer = response.data[0];

        return offer;
    }

    async create(data) {
        let response = await myaxios.post(CREATE_SPECIAL_OFFER_ENDPOINT, data);

        let offer = response.data[0];

        return offer;
    }

    async patch(id, data) {
        let response = await myaxios.patch(PATCH_SPECIAL_OFFER_ENDPOINT + id, data);

        let offer = response.data[0];

        return offer;
    }

    async delete(id) {
        let response = await myaxios.delete(DELETE_SPECIAL_OFFER_ENDPOINT + id);

        if (response.status == 200) {
            return true;
        } else {
            return false;
        }
    }
}

export default new OfferService();
