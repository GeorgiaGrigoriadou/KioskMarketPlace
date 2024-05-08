import { myaxios } from "@/app/util/myaxios";

import { GET_QUIZ_ANSWER_ENDPOINT } from "@/app/api/endpoints.js";

class AnswerService {
  async get() {
    let response = await myaxios.get(GET_QUIZ_ANSWER_ENDPOINT);
    
    let answers = response.data;


   
    let sortedAnswers = answers.sort((a, b) => {
      let fa = a.question_id,
        fb = b.question_id;
      if (fa < fb) {
        return -1;
      }
      if (fa > fb) {
        return 1;
      }
      return 0;
    });

    return sortedAnswers;
  }
}

export default new AnswerService();