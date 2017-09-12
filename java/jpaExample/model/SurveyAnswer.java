/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

/**
 * @author vitort
 *
 */
@Entity
@Where(clause="isActive <> '0'")
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
