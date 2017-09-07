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
public class JobRequestUserSurveyAnswer extends UserSurveyAnswer implements BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7399733967437123745L;
	
	//job request day to which the question refers
	@ManyToOne
	@JoinColumn(name = "jobRequest_id")
	public JobRequest jobRequest;

}
