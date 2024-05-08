import { myaxios } from "@/app/util/myaxios";

import { GET_QUIZ_ENDPOINT } from "@/app/api/endpoints.js";

class QuizService {
  async get() {
    let response = await myaxios.get(GET_QUIZ_ENDPOINT);
    
    let quizes = response.data;

    console.log(quizes);

   
    let sortedTotalQuestions = quizes.sort((a, b) => {
      let fa = a.totalPoints,
        fb = b.totalPoints;
      if (fa < fb) {
        return -1;
      }
      if (fa > fb) {
        return 1;
      }
      return 0;
    });

    return sortedTotalQuestions;
  }
}

export default new QuizService();