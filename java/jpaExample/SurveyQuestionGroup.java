/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import enums.SurveyQuestionGroupType;

/**
 * @author vitort
 *
 */
@Entity
public class SurveyQuestionGroup extends BaseModelImpl implements BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3859682179184599242L;
	
	@OneToMany
	@JoinColumn(name = "group_id")
	public List<SurveyQuestion> questions;
	
	@Enumerated(EnumType.STRING)
	public SurveyQuestionGroupType type;

}
