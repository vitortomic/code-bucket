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
import models.UserSurveyAnswer;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import services.model.SurveyAnswerService;
import services.model.SurveyQuestionService;
import services.model.UserSurveyAnswerService;
import util.to.SurveyQuestionGroupTO;
import util.to.SurveyQuestionTO;

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
		try{
			SurveyQuestionGroupTO groupTO = Json.fromJson(request().body().asJson(), SurveyQuestionGroupTO.class);
			groupTO.questions.forEach(questionTO ->{
				SurveyQuestion question = questionService.findById(questionTO.id);
				List<SurveyAnswer> answers = null;
				String answerText = null;
				if(questionTO.isOtherAnswerChecked){
					answerText = questionTO.otherAnswer;
				}
				else{
					answers = questionTO.answers.stream().map(answerTO -> answerService.findById(answerTO.id)).collect(Collectors.toList());
				}
				userAnswerService.create(user, answers, question, answerText, null, null);
			});
			return ok();
		}
		catch (Exception e){
			Logger.error("submitQuestionResponse error: " + e.getMessage());
			return status500(e);
		}
	}
	
	/* unused
	public static Result getUserQuestionAnswer(User user){
		try{
			SurveyQuestionGroupTO groupTO = Json.fromJson(request().body().asJson(), SurveyQuestionGroupTO.class);
			List<SurveyQuestionTO> questionResponses = groupTO.questions.stream().map(questionTO -> {
				List<UserSurveyAnswer> answers = userAnswerService.findByUserIdAndQuestionId(user.getId(), questionTO.id);
				//returns latest answer of user to provided question
				return new SurveyQuestionTO(answers.get(answers.size()-1));
			}).collect(Collectors.toList());
			return ok(Json.toJson(questionResponses));
		}
		catch (Exception e){
			Logger.error("getUserQuestionAnswer error: " + e.getMessage());
			return status500(e);
		}
	}*/
	
	public static Result getAnswersForQuestionAndUser(Long userId, Long questionId){
		try{
			List<UserSurveyAnswer> answers = userAnswerService.findByUserIdAndQuestionId(userId, questionId);
			return ok(Json.toJson(new SurveyQuestionTO(answers.get(answers.size()-1))));
		}
		catch (Exception e){
			Logger.error("getAnswersForQuestionAndUser error: " + e.getMessage());
			return status500(e);
		}
	}
}
