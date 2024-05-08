import axios from "axios";

axios.defaults.headers.common['Accept'] = "application/json";

export const myaxios = axios.create({
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json"
  },
});


// import store from "../../store";
// import router from "@/router";

// myaxios.interceptors.response.use(
//   (response) => {
//     return response;
//   },
//   (error) => {
//     if (error.response.status === 401) {
//       console.log("session expired!");
//       store.dispatch("LogOut");
//       router.push("/login");
//     }
//     return error;
//   }
// );

// myaxios.interceptors.request.use(
//   (config) => {
//     if (store.getters.IsAuthenticated) {
//       console.log("token injected");
//       const token = store.getters.StateToken;
//      // const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZ2FsIiwiaWF0IjoxNjk0ODUzMDAyLCJleHAiOjE2OTQ5Mzk0MDJ9.BtY7LXPTds1ZjYzcLn56wfuNIfBgv-qEsOB8RtZfYgw";

//       console.log(token);

//       if (token != null) {
//         config.headers['Authorization'] = `Bearer ${token}`;
//       }
//       return config;
//     } else {
//       console.log("token ignored");
//       return config;
//     }
//   },
//   (error) => {
//     Promise.reject(error);
//   }
// );

// myaxios.interceptors.response.use(response => {
//   return response;
// }, error => {
//  if (error.response.status === 401) {
//   alert('login failed')
//  }
//  return error;
// });