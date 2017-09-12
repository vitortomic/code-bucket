/**
 * 
 */
package controllers.base;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import controllers.BaseController;
import models.SurveyAnswer;
import models.SurveyQuestion;
import models.SurveyQuestionGroup;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import services.model.SurveyAnswerService;
import services.model.SurveyQuestionService;
import services.model.UserSurveyAnswerService;
import util.to.SurveyQuestionGroupTO;

/**
 * @author vitort
 *
 */
public class SurveyController extends BaseController {
	
	@Inject
	protected static SurveyQuestionService questionService;
	
	@Inject
	protected static UserSurveyAnswerService userAnswerService;
	
	@Inject
	protected static SurveyAnswerService answerService;
	
	public static Result getQuestionsByTypeOrId(){
		try{
			SurveyQuestionGroupTO groupTO = Json.fromJson(request().body().asJson(), SurveyQuestionGroupTO.class);
			SurveyQuestionGroup group = null;
			if(groupTO.id != null){
				group = questionService.findGroupByGroupId(groupTO.id);
			}
			else if (groupTO.type != null){
				group = questionService.findGroupByGroupType(groupTO.type);
			}
			
			if(group == null) return notFound();
			SurveyQuestionGroupTO response = new SurveyQuestionGroupTO(group);
			return ok(Json.toJson(response));
		}
		catch (Exception e){
			Logger.error("getQuestionsByType error: " + e.getMessage());
			return status500(e);
		}
	}
	
	public static Result submitQuestionResponse(User user){
		SurveyQuestionGroupTO groupTO = Json.fromJson(request().body().asJson(), SurveyQuestionGroupTO.class);
		groupTO.questions.forEach(questionTO ->{
			SurveyQuestion question = questionService.findById(questionTO.id);
			List<SurveyAnswer> answers = null;
			String answerText = null;
			if(questionTO.hasOtherAnswer){
				answerText = questionTO.otherAnswer;
			}
			else{
				answers = questionTO.answers.stream().map(answerTO -> answerService.findById(answerTO.id)).collect(Collectors.toList());
			}
			userAnswerService.create(user, answers, question, answerText, null, null);
		});
		return ok();
	}
}
