/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	
	@ManyToOne
	@JoinColumn(name = "answer_id")
	public SurveyAnswer selectedAnswer;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	public SurveyQuestion question;
	
	public String textAnswer;

}
