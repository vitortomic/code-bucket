/**
 * 
 */
package services.model.impl;

import java.util.List;

import javax.persistence.Query;

import models.JobRequestDayUserSurveyAnswer;
import models.JobRequestUserSurveyAnswer;
import models.UserSurveyAnswer;
import play.db.jpa.JPA;
import services.model.UserSurveyAnswerService;
import util.ListUtils;

/**
 * @author vitort
 *
 */
public class UserSurveyAnswerServiceImpl extends BaseModelServiceImpl<UserSurveyAnswer> implements UserSurveyAnswerService {

	protected UserSurveyAnswerServiceImpl() {
		super(UserSurveyAnswer.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.UserSurveyAnswerService#findByUserId(java.lang.Long)
	 */
	@Override
	public List<UserSurveyAnswer> findByUserId(Long userId){
		String hql = "from UserSurveyAnswer answer where answer.user.id :=userId";
		Query query = JPA.em().createQuery(hql);
		return ListUtils.castList(UserSurveyAnswer.class, query.getResultList());
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.UserSurveyAnswerService#findByUserIdAndQuestionId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<UserSurveyAnswer> findByUserIdAndQuestionId(Long userId, Long questionId){
		String hql = "from UserSurveyAnswer answer where answer.user.id :=userId and answer.question.id =:questionId";
		Query query = JPA.em().createQuery(hql);
		return ListUtils.castList(UserSurveyAnswer.class, query.getResultList());
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.UserSurveyAnswerService#findByJobRequestId(java.lang.Long)
	 */
	@Override
	public List<JobRequestUserSurveyAnswer> findByJobRequestId(Long jobRequestId){
		String hql = "from JobRequestUserSurveyAnswer answer where answer.jobRequest.id :=jobRequestId";
		Query query = JPA.em().createQuery(hql);
		return ListUtils.castList(JobRequestUserSurveyAnswer.class, query.getResultList());
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.UserSurveyAnswerService#findByJobRequestDayId(java.lang.Long)
	 */
	@Override
	public List<JobRequestDayUserSurveyAnswer> findByJobRequestDayId(Long jobRequestDayId){
		String hql = "from JobRequestDayUserSurveyAnswer answer where answer.jobRequestDay.id :=jobRequestDayId";
		Query query = JPA.em().createQuery(hql);
		return ListUtils.castList(JobRequestDayUserSurveyAnswer.class, query.getResultList());
	}
}
