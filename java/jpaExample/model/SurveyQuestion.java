/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

/**
 * @author vitort
 *
 */
@Entity
@Where(clause="active <> 0")
public class SurveyQuestion extends BaseModelImpl implements BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2030451691044487765L;
	
	public Boolean active;
	
	public String questionText;
	
	public Boolean hasOtherTextResponse;
	
	public Boolean isMultiselect;
	
	@OneToMany
	@JoinColumn(name = "question_id")
	public List<SurveyAnswer> answers;
	
}
