/**
 * 
 */
package services.model.impl;

import java.util.List;

import javax.persistence.Query;

import models.JobRequest;
import models.JobRequestDay;
import models.JobRequestDayUserSurveyAnswer;
import models.JobRequestUserSurveyAnswer;
import models.SurveyAnswer;
import models.SurveyQuestion;
import models.User;
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
		query.setParameter("userId", userId);
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
		query.setParameter("userId", userId);
		query.setParameter("questionId", questionId);
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
		query.setParameter("jobRequestId", jobRequestId);
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
		query.setParameter("jobRequestDayId", jobRequestDayId);
		return ListUtils.castList(JobRequestDayUserSurveyAnswer.class, query.getResultList());
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.UserSurveyAnswerService#create(models.User, java.util.List, models.SurveyQuestion, java.lang.String, models.JobRequest, models.JobRequestDay)
	 */
	@Override
	public UserSurveyAnswer create(User user, List<SurveyAnswer> selectedAnswers, 
			SurveyQuestion question, String textAnswer, JobRequest jobRequest, JobRequestDay jobRequestDay){
		
		if(jobRequestDay != null){
			JobRequestDayUserSurveyAnswer answer = new JobRequestDayUserSurveyAnswer(user, selectedAnswers, 
					 question, textAnswer, jobRequestDay);
			return save(answer);
		}
		
		if(jobRequest != null){
			JobRequestUserSurveyAnswer answer = new JobRequestUserSurveyAnswer(user, selectedAnswers, 
			 question, textAnswer, jobRequest);
			return save(answer);
		}
		
		UserSurveyAnswer answer = new UserSurveyAnswer(user, selectedAnswers, 
					 question, textAnswer);
		return save(answer);
	}
}
