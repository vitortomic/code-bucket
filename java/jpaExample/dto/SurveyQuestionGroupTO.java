/**
 * 
 */
package util.to;

import java.util.List;
import java.util.stream.Collectors;

import enums.SurveyQuestionGroupType;
import models.SurveyQuestionGroup;

/**
 * @author vitort
 *
 */
public class SurveyQuestionGroupTO {
	
	public Long id;
	public List<SurveyQuestionTO> questions;
	public SurveyQuestionGroupType type;
	
	public SurveyQuestionGroupTO(){
		
	}
	
	public SurveyQuestionGroupTO(SurveyQuestionGroup group){
		this.id = group.getId();
		this.questions = group.questions.stream().map(question -> new SurveyQuestionTO(question)).collect(Collectors.toList());
		this.type = group.type;
	}
}
