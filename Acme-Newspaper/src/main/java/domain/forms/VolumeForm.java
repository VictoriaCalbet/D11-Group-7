
package domain.forms;

import java.util.Collection;

import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Newspaper;

public class VolumeForm {

	// Attributes -------------------------------------------------------------

	private int						id;
	private String					title;
	private String					description;
	private Integer					year;
	private Collection<Newspaper>	newspapers;


	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getYear() {
		return this.year;
	}
	public void setYear(final Integer year) {
		this.year = year;
	}
	@NotNull
	@Valid
	@ManyToMany
	public Collection<Newspaper> getNewspapers() {
		return this.newspapers;
	}
	public void setNewspapers(final Collection<Newspaper> newspapers) {
		this.newspapers = newspapers;
	}

}
