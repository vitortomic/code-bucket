/**
 * 
 */
package services.model.impl;

import java.util.List;

import javax.persistence.Query;

import enums.SurveyQuestionGroupType;
import models.SurveyQuestion;
import models.SurveyQuestionGroup;
import play.db.jpa.JPA;
import services.model.SurveyQuestionService;

/**
 * @author vitort
 *
 */
public class SurveyQuestionServiceImpl extends BaseModelServiceImpl<SurveyQuestion> implements SurveyQuestionService{

	protected SurveyQuestionServiceImpl() {
		super(SurveyQuestion.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.SurveyQuestionService#findByGroupType(enums.SurveyQuestionGroupType)
	 */
	@Override
	public List<SurveyQuestion> findByGroupType(SurveyQuestionGroupType type){
		String hql = "from SurveyQuestionGroup group where group.type =:type";
		Query query = JPA.em().createQuery(hql);
		query.setParameter("type", type);
		SurveyQuestionGroup group = (SurveyQuestionGroup)query.getSingleResult();
		if(group != null){
			return group.questions;
		}
		else return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.SurveyQuestionService#findByGroupId(java.lang.Long)
	 */
	@Override
	public List<SurveyQuestion> findByGroupId(Long groupId){
		String hql = "from SurveyQuestionGroup group where group.id =:groupId";
		Query query = JPA.em().createQuery(hql);
		query.setParameter("groupId", groupId);
		SurveyQuestionGroup group = (SurveyQuestionGroup)query.getSingleResult();
		if(group != null){
			return group.questions;
		}
		else return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.SurveyQuestionService#findGroupByGroupId(java.lang.Long)
	 */
	@Override
	public SurveyQuestionGroup findGroupByGroupId(Long groupId){
		String hql = "from SurveyQuestionGroup group where group.id =:groupId";
		Query query = JPA.em().createQuery(hql);
		query.setParameter("groupId", groupId);
		return (SurveyQuestionGroup)query.getSingleResult();
	}
	
	/*
	 * (non-Javadoc)
	 * @see services.model.SurveyQuestionService#findGroupByGroupType(java.lang.String)
	 */
	@Override
	public SurveyQuestionGroup findGroupByGroupType(SurveyQuestionGroupType type){
		String hql = "from SurveyQuestionGroup group where group.type =:type";
		Query query = JPA.em().createQuery(hql);
		query.setParameter("type", type);
		return (SurveyQuestionGroup)query.getSingleResult();
	}
}
