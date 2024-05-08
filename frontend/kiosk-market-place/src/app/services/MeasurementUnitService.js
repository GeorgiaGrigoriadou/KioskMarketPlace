import { myaxios } from "@/app/util/myaxios";

import { GET_MEASUREMENT_UNIT_ENDPOINT } from "@/app/api/endpoints.js";

class MeasurementUnitService {
  async get() {
    let response = await myaxios.get(GET_MEASUREMENT_UNIT_ENDPOINT);
    
    let units = response.data;
   
   
    let sortedUnits = units.sort((a, b) => {
      let fa = a.code,
        fb = b.code;
      if (fa < fb) {
        return -1;
      }
      if (fa > fb) {
        return 1;
      }
      return 0;
    });

    return sortedUnits;
  }
}

export default new MeasurementUnitService();