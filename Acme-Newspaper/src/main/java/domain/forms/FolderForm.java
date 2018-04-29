
package domain.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class FolderForm {

	// Attributes -------------------------------------------------------------

	private int		id;
	private String	name;
	private Boolean	system;
	private Integer	parentId;


	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	public Boolean getSystem() {
		return this.system;
	}
	public void setSystem(final Boolean system) {
		this.system = system;
	}

	public Integer getParentId() {
		return this.parentId;
	}
	public void setParentId(final Integer parentId) {
		this.parentId = parentId;
	}

}
