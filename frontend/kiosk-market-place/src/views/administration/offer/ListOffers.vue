<template>
    <div class="py-4 container-fluid">
      <div class="row">
        <div class="col-12">
          <admin-offers-table />
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
  import AdminOffersTable from "@/views/administration/components/AdminOffersTable";
  
  
  export default {
    name: "tables",
    components: {
      AdminOffersTable,
    },
  };
  </script>