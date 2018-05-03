
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "title")
})
public class Advertisement extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String		title;
	private String		bannerURL;
	private String		targetPageURL;
	private CreditCard	creditCard;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBannerURL() {
		return this.bannerURL;
	}
	public void setBannerURL(final String bannerURL) {
		this.bannerURL = bannerURL;
	}

	@NotBlank
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTargetPageURL() {
		return this.targetPageURL;
	}
	public void setTargetPageURL(final String targetPageURL) {
		this.targetPageURL = targetPageURL;
	}

	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	// Relationships ----------------------------------------------------------

	private Agent		agent;
	private Newspaper	newspaper;


	@NotNull
	@Valid
	@ManyToOne
	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(final Agent agent) {
		this.agent = agent;
	}

	@NotNull
	@Valid
	@ManyToOne
	public Newspaper getNewspaper() {
		return this.newspaper;
	}

	public void setNewspaper(final Newspaper newspaper) {
		this.newspaper = newspaper;
	}

}
