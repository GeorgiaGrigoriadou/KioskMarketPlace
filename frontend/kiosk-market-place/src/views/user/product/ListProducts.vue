<template>
    <div class="py-4 container-fluid">
      <div class="row">
        <div class="col-12">
          <user-products-table></user-products-table>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import UserProductsTable from "@/views/user/components/UserProductsTable";

  export default {
    name: "UserListProducts",
    components: {
      UserProductsTable,
    },
  };
  </script>