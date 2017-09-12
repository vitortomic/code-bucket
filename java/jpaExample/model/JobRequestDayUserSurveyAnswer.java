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
public class JobRequestDayUserSurveyAnswer extends UserSurveyAnswer implements BaseModel {

	public JobRequestDayUserSurveyAnswer(User user, List<SurveyAnswer> selectedAnswers, SurveyQuestion question,
			String textAnswer, JobRequestDay jobRequestDay) {
		super(user, selectedAnswers, question, textAnswer);
		this.jobRequestDay = jobRequestDay;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4016090357648534456L;
	
	//job request day to which the question refers
	@ManyToOne
	@JoinColumn(name = "jobRequestDay_id")
	public JobRequestDay jobRequestDay;
	
}
