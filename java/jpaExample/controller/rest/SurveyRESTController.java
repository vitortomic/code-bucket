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
	
	public static Result getQuestionsByTypeOrIdRest(String access_token){
		try {
			@SuppressWarnings("unused")
			User user = getCurrentUser(access_token);
			return getQuestionsByTypeOrId();
		} catch (AuthException ae) {
			return unauthorized();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("SurveyRESTController getQuestionsByTypeOrIdRest Error: ", e);
			return status500(e);
		}
	}
	
	public Result submitQuestionResponseRest(String access_token){
		try {
			User user = getCurrentUser(access_token);
			return super.submitQuestionResponse(user);
		} catch (AuthException ae) {
			return unauthorized();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("SurveyRESTController submitQuestionResponseRest Error: ", e);
			return status500(e);
		}
	}
}
