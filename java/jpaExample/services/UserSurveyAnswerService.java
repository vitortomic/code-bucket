/**
 * 
 */
package services.model;

import java.util.List;

import models.JobRequestDayUserSurveyAnswer;
import models.JobRequestUserSurveyAnswer;
import models.UserSurveyAnswer;

/**
 * @author vitort
 *
 */
public interface UserSurveyAnswerService extends BaseModelService<UserSurveyAnswer> {

	/**
	 * returns answers by anwsering user id
	 * @param userId
	 * @return
	 */
	public List<UserSurveyAnswer> findByUserId(Long userId);
	
	/**
	 * returns answers by answering user id and question id
	 * @param userId
	 * @param questionId
	 * @return
	 */
	public List<UserSurveyAnswer> findByUserIdAndQuestionId(Long userId, Long questionId);
	
	/**
	 * returns answers by corresponding job request id
	 * @param jobRequestId
	 * @return
	 */
	public List<JobRequestUserSurveyAnswer> findByJobRequestId(Long jobRequestId);
	
	/**
	 * returns answers by corresponding job request day id
	 * @param jobRequestDayId
	 * @return
	 */
	public List<JobRequestDayUserSurveyAnswer> findByJobRequestDayId(Long jobRequestDayId);
}
