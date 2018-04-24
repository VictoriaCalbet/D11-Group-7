
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "isPrivate"), @Index(columnList = "publicationDate"), @Index(columnList = "title"), @Index(columnList = "description")
})
public class Newspaper extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	title;
	private Date	publicationDate;
	private String	description;
	private String	picture;
	private boolean	isPrivate;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPublicationDate() {
		return this.publicationDate;
	}
	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(final boolean isPrivate) {
		this.isPrivate = isPrivate;
	}


	// Relationships ----------------------------------------------------------
	private User								publisher;
	private Collection<Article>					articles;
	private Collection<NewspaperSubscription>	newspaperSubscriptions;
	private Collection<Advertisement>			advertisements;
	private Collection<Volume>					volumes;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getPublisher() {
		return this.publisher;
	}
	public void setPublisher(final User publisher) {
		this.publisher = publisher;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "newspaper", cascade = CascadeType.REMOVE)
	public Collection<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(final Collection<Article> articles) {
		this.articles = articles;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "newspaper", cascade = CascadeType.REMOVE)
	public Collection<NewspaperSubscription> getNewspaperSubscriptions() {
		return this.newspaperSubscriptions;
	}

	public void setNewspaperSubscriptions(final Collection<NewspaperSubscription> newspaperSubscriptions) {
		this.newspaperSubscriptions = newspaperSubscriptions;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "newspaper", cascade = CascadeType.REMOVE)
	public Collection<Advertisement> getAdvertisements() {
		return this.advertisements;
	}
	public void setAdvertisements(final Collection<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	@NotNull
	@Valid
	@ManyToMany(mappedBy = "newspapers")
	public Collection<Volume> getVolumes() {
		return this.volumes;
	}
	public void setVolumes(final Collection<Volume> volumes) {
		this.volumes = volumes;
	}
}
