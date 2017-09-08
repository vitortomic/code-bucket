/**
 * 
 */
package controllers.base;

import javax.inject.Inject;

import controllers.BaseController;
import models.SurveyQuestionGroup;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
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
	protected static UserSurveyAnswerService answerService;
	
	public static Result getQuestionsByType(){
		try{
			SurveyQuestionGroupTO groupTO = Json.fromJson(request().body().asJson(), SurveyQuestionGroupTO.class);
			SurveyQuestionGroup group = questionService.findGroupByGroupId(groupTO.id);
			if(group == null) return notFound();
			SurveyQuestionGroupTO response = new SurveyQuestionGroupTO(group);
			return ok(Json.toJson(response));
		}
		catch (Exception e){
			Logger.error("getQuestionsByType error: " + e.getMessage());
			return status500(e);
		}
	}
}
