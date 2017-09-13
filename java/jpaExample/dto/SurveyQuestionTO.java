/**
 * 
 */
package util.to;

import java.util.List;
import java.util.stream.Collectors;

import models.SurveyQuestion;
import models.UserSurveyAnswer;

/**
 * @author vitort
 *
 */
public class SurveyQuestionTO {
	
	public SurveyQuestionTO(){
		
	}
	
	/**
	 * maps question to question TO
	 * @param question
	 */
	public SurveyQuestionTO(SurveyQuestion question) {
		this.id = question.getId();
		this.question = question.questionText;
		this.isMultiselect = question.isMultiselect;
		this.hasOtherAnswer = question.hasOtherTextResponse;
		this.answers = question.answers.stream().map(answer -> new SurveyAnswerTO(answer)).collect(Collectors.toList());
	}
	
	/**
	 * maps answers to question TO (to display user response to question)
	 * @param answer
	 */
	public SurveyQuestionTO(UserSurveyAnswer answer){
		this.id = answer.question.getId();
		this.question = answer.question.questionText;
		this.isMultiselect = answer.question.isMultiselect;
		this.hasOtherAnswer = answer.question.hasOtherTextResponse;
		this.otherAnswer = answer.textAnswer;
		this.isOtherAnswerChecked = answer.textAnswer != null && answer.textAnswer.length() > 0 ? true : false;
		this.answers = answer.selectedAnswers.stream().map(selectedAnswer -> new SurveyAnswerTO(selectedAnswer)).collect(Collectors.toList());
	}
	
	public Long id;
    public String question;
    public List<SurveyAnswerTO> answers;
    public Boolean isMultiselect;
    public Boolean hasOtherAnswer;
    public String otherAnswer;
    public Boolean isOtherAnswerChecked;
}
