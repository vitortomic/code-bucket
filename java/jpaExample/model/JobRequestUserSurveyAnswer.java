/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author vitort
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class JobRequestUserSurveyAnswer extends UserSurveyAnswer implements BaseModel {

	public JobRequestUserSurveyAnswer(User user, List<SurveyAnswer> selectedAnswers, SurveyQuestion question,
			String textAnswer, JobRequest jobRequest) {
		super(user, selectedAnswers, question, textAnswer);
		this.jobRequest = jobRequest;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7399733967437123745L;
	
	//job request day to which the question refers
	@ManyToOne
	@JoinColumn(name = "jobRequest_id")
	public JobRequest jobRequest;

}
