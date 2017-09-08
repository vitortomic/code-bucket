/**
 * 
 */
package util.to;

import models.SurveyAnswer;

/**
 * @author vitort
 *
 */
public class SurveyAnswerTO {
	
	public SurveyAnswerTO(){
		
	}
	
	public SurveyAnswerTO(SurveyAnswer answer) {
		this.id = answer.getId();
		this.answerText = answer.answerText;
	}
	
	public Long id;
	public String answerText;
	Boolean isChecked;
}
