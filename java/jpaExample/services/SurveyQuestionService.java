/**
 * 
 */
package services.model;

import java.util.List;

import enums.SurveyQuestionGroupType;
import models.SurveyQuestion;
import models.SurveyQuestionGroup;

/**
 * @author vitort
 *
 */
public interface SurveyQuestionService extends BaseModelService<SurveyQuestion> {
	
	/**
	 * finds question group by survey type
	 * @param type
	 * @return
	 */
	public List<SurveyQuestion> findByGroupType(SurveyQuestionGroupType type);
	
	/**
	 * finds question group by group id
	 * @param groupId
	 * @return
	 */
	public List<SurveyQuestion> findByGroupId(Long groupId);
	
	/**
	 * returns question group object by id
	 * @param groupId
	 * @return
	 */
	public SurveyQuestionGroup findGroupByGroupId(Long groupId);

}
