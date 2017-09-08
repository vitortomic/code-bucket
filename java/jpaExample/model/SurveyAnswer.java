/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author vitort
 *
 */
@Entity
public class SurveyAnswer extends BaseModelImpl implements BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8010335552526510868L;
	
	public String answerText;
	
	public Boolean isActive;
	
	@ManyToOne
	public SurveyQuestion question;
}
