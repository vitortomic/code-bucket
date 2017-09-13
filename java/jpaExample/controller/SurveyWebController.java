/**
 * 
 */
package controllers;

import controllers.base.SurveyController;
import models.User;
import play.mvc.Result;

/**
 * @author vitort
 *
 */
public class SurveyWebController extends SurveyController {
	
	public static Result getQuestionsByTypeOrIdWeb(){
		return getQuestionsByTypeOrId();
	}
	
	public static Result submitQuestionResponseWeb(){
		User user = getCurrentUser();
		return submitQuestionResponse(user);
	}
	
	public static Result getAnswers(Long userId, Long questionId){
		if(userId == null)userId = getCurrentUser().getId();
		return getAnswersForQuestionAndUser(userId, questionId);
	}
}
