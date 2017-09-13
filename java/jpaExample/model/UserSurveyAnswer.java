/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author vitort
 *
 */
@Entity
public class UserSurveyAnswer extends BaseModelImpl implements BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3200019543697709249L;
	
	//user answering the question
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User user;
	
	@ManyToMany
	public List<SurveyAnswer> selectedAnswers;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	public SurveyQuestion question;
	
	public String textAnswer;

	public UserSurveyAnswer(User user, List<SurveyAnswer> selectedAnswers, SurveyQuestion question, String textAnswer) {
		super();
		this.user = user;
		this.selectedAnswers = selectedAnswers;
		this.question = question;
		this.textAnswer = textAnswer;
	}
	
	public UserSurveyAnswer(){
		
	}
	
}
