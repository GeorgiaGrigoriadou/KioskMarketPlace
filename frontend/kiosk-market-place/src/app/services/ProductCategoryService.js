import { myaxios } from "@/app/util/myaxios";

import { GET_PRODUCT_CATEGORIES_ENDPOINT } from "@/app/api/endpoints.js";

class ProductCategoryService {
  async get(removeUnused = false) {
    let response = await myaxios.get(GET_PRODUCT_CATEGORIES_ENDPOINT);
    
    let categories = response.data;

    console.log(categories);

    if (removeUnused) {
      categories = categories.filter((product) => product.totalProducts > 0);
    }

    let sortedCategories = categories.sort((a, b) => {
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

    return sortedCategories;
  }
}

export default new ProductCategoryService();
