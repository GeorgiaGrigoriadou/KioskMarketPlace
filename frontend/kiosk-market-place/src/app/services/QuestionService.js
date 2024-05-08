import { myaxios } from "@/app/util/myaxios";

import { GET_QUIZ_QUESTIONS_ENDPOINT } from "@/app/api/endpoints.js";

class QuestionService {
  async get() {
    let response = await myaxios.get(GET_QUIZ_QUESTIONS_ENDPOINT);
    
    let questions = response.data;

    console.log(questions);

   
    let sortedQuestions = questions.sort((a, b) => {
      let fa = a.text.toLowerCase(),
        fb = b.text.toLowerCase();
      if (fa < fb) {
        return -1;
      }
      if (fa > fb) {
        return 1;
      }
      return 0;
    });

    return sortedQuestions;
  }
}

export default new QuestionService();