/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author vitort
 *
 */
@Entity
public class SurveyQuestion extends BaseModelImpl implements BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2030451691044487765L;
	
	public Boolean active;
	
	public String questionText;
	
	public Boolean hasOtherTextResponse;
	
	@OneToMany
	@JoinColumn(name = "question_id")
	public List<SurveyAnswer> answers;
	
}
