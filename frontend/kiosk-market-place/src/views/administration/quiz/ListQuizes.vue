<template>
    <div class="py-4 container-fluid">
      <div class="row">
        <div class="col-12">
          <admin-quizes-table />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import AdminQuizesTable from "@/views/administration/components/AdminQuizesTable";
    
  export default {
    name: "tables",
    components: {
        AdminQuizesTable,
    },
  };
  </script>