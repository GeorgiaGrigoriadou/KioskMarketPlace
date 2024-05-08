<template>
    <div class="py-4 container-fluid">
      <div class="row">
        <div class="col-12">
          <user-products-table />
        </div>
      </div>
      <!-- <div class="row">
        <div class="col-12">
          <projects-table />
        </div>
      </div> -->
    </div>
  </template>
  
  <script>
  import UserProductsTable from "./user/UserProductsTable";
  
  
  export default {
    name: "tables",
    components: {
      UserProductsTable,
    },
  };
  </script>