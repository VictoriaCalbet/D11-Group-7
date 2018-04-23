
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Volume extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	title;
	private String	description;
	private Integer	year;


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


	// Relationships ----------------------------------------------------------

	private User							user;
	private Collection<Newspaper>			newspapers;
	private Collection<VolumeSubscription>	volumeSubscriptions;


	@NotNull
	@Valid
	@ManyToOne
	public User getUser() {
		return this.user;
	}
	public void setUser(final User user) {
		this.user = user;
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

	@NotNull
	@Valid
	@OneToMany(mappedBy = "volume")
	public Collection<VolumeSubscription> getVolumeSubscriptions() {
		return this.volumeSubscriptions;
	}
	public void setVolumeSubscriptions(final Collection<VolumeSubscription> volumeSubscriptions) {
		this.volumeSubscriptions = volumeSubscriptions;
	}

}
