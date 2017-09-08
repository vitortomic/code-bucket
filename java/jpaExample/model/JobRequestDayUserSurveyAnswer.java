/**
 * 
 */
package models;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 4016090357648534456L;
	
	//job request day to which the question refers
	@ManyToOne
	@JoinColumn(name = "jobRequestDay_id")
	public JobRequestDay jobRequestDay;
	
}
