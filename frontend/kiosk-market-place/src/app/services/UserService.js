import { myaxios } from "@/app/util/myaxios";

import {
    GET_USERS_ENDPOINT,
    GET_USERS_BY_ID_ENDPOINT,
    PATCH_USERS_ENDPOINT,
    CREATE_USERS_ENDPOINT,
    DELETE_USERS_ENDPOINT,
} from "@/app/api/endpoints.js";

class UserService {
  async get() {
    let response = await myaxios.get(GET_USERS_ENDPOINT);

    let users = response.data;

    console.log(users);

    let sortedUsers = users.sort((a, b) => {
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

    return sortedUsers;
  }

  async getByID(id) {
    let response = await myaxios.get(GET_USERS_BY_ID_ENDPOINT + id);

    let user = response.data[0];

    return user;
  }

  async create(data) {
    let response = await myaxios.post(CREATE_USERS_ENDPOINT, data);

    let user = response.data[0];

    return user;
  }

  async patch(id, data) {
    let response = await myaxios.patch(PATCH_USERS_ENDPOINT + id, data);

    let user = response.data[0];

    return user;
  }

  async delete(id) {
    let response = await myaxios.delete(DELETE_USERS_ENDPOINT + id);

    if (response.status == 200) {
      return true;
    } else {
      return false;
    }
  }
}

export default new UserService();
