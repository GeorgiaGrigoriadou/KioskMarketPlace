<template>
    <div class="py-4 container-fluid">
      <div class="row">
        <div class="col-12">
          <admin-products-table />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import AdminProductsTable from "@/views/administration/components/AdminProductsTable";

  export default {
    name: "tables",
    components: {
        AdminProductsTable,
    },
  };
  </script>