/**
 * 
 */
package util.to;

import java.util.List;
import java.util.stream.Collectors;

import models.SurveyQuestion;

/**
 * @author vitort
 *
 */
public class SurveyQuestionTO {
	
	public SurveyQuestionTO(){
		
	}
	
	public SurveyQuestionTO(SurveyQuestion question) {
		this.id = question.getId();
		this.question = question.questionText;
		this.isMultiselect = question.isMultiselect;
		this.hasOtherAnswer = question.hasOtherTextResponse;
		this.answers = question.answers.stream().map(answer -> new SurveyAnswerTO(answer)).collect(Collectors.toList());
	}
	
	public Long id;
    public String question;
    public List<SurveyAnswerTO> answers;
    public Boolean isMultiselect;
    public Boolean hasOtherAnswer;
    public String otherAnswer;
    public Boolean isOtherAnswerChecked;
}
