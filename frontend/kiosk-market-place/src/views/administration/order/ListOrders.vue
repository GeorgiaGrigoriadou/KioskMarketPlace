<template>
    <div class="py-4 container-fluid">
      <div class="row">
        <div class="col-12">
          <admin-orders-table />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import AdminOrdersTable from "@/views/administration/components/AdminOrdersTable";
    
  export default {
    name: "tables",
    components: {
      AdminOrdersTable,
    },
  };
  </script>