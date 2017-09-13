/**
 * 
 */
package controllers.rest;

import controllers.base.SurveyController;
import exceptions.AuthException;
import models.User;
import play.Logger;
import play.mvc.Result;

/**
 * @author vitort
 *
 */
public class SurveyRESTController extends SurveyController {
	
	public static Result getQuestionsByTypeOrIdRest(String access_token, Long groupId, String type){
		try {
			@SuppressWarnings("unused")
			User user = getCurrentUser(access_token);
			return getQuestionsByTypeOrId(groupId, type);
		} catch (AuthException ae) {
			return unauthorized();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("SurveyRESTController getQuestionsByTypeOrIdRest Error: ", e);
			return status500(e);
		}
	}
	
	public static Result submitQuestionResponseRest(String access_token){
		try {
			User user = getCurrentUser(access_token);
			return submitQuestionResponse(user);
		} catch (AuthException ae) {
			return unauthorized();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("SurveyRESTController submitQuestionResponseRest Error: ", e);
			return status500(e);
		}
	}
	
	public static Result getAnswers(String access_token, Long userId, Long questionId){
		try {
			User user = getCurrentUser(access_token);
			return getAnswersForQuestionAndUser(user.getId(), questionId);
		} catch (AuthException ae) {
			return unauthorized();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("SurveyRESTController getAnswers Error: ", e);
			return status500(e);
		}
	}
}
