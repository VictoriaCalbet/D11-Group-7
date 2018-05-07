
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FollowUpRepository;
import domain.FollowUp;
import domain.User;

@Service
@Transactional
public class FollowUpService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private FollowUpRepository	followUpRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService			userService;


	// Constructors -----------------------------------------------------------

	public FollowUpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public FollowUp create() {
		FollowUp result = null;
		User user = null;

		user = this.userService.findByPrincipal();

		result = new FollowUp();
		result.setPublicationMoment(new Date());
		result.setUser(user);
		result.setPictures(new ArrayList<String>());

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public FollowUp save(final FollowUp followUp) {
		Assert.notNull(followUp);
		FollowUp result;
		result = this.followUpRepository.save(followUp);
		return result;
	}

	public FollowUp saveFromCreate(final FollowUp followUp) {
		FollowUp result = null;
		User user = null;

		user = this.userService.findByPrincipal();

		Assert.notNull(followUp, "message.error.followUp.null");
		Assert.notNull(user, "message.error.followUp.principal.null");
		Assert.isTrue(followUp.getUser().equals(user), "message.error.followUp.user.owner");
		Assert.isTrue(followUp.getArticle().getWriter().equals(user), "message.error.followUp.heDoesntWriteTheArticle");
		Assert.isTrue(!followUp.getArticle().getIsDraft(), "message.error.followUp.articleIsDraft");
		Assert.notNull(followUp.getArticle().getNewspaper().getPublicationDate(), "message.error.followUp.theNewspaperHasntBeenPublishedYet");

		followUp.setPublicationMoment(new Date(System.currentTimeMillis() - 1));

		// Paso 1: realizo la entidad del servicio FollowUp

		result = this.save(followUp);

		// Paso 2: persisto el resto de relaciones a las que el objeto FollowUp está relacionada.

		//		result.getUser().getFollowUps().add(result);
		//		result.getArticle().getFollowUps().add(result);

		return result;
	}
	public FollowUp saveFromEdit(final FollowUp followUp) {
		FollowUp result = null;
		User user = null;

		user = this.userService.findByPrincipal();

		Assert.notNull(followUp, "message.error.followUp.null");
		Assert.notNull(user, "message.error.followUp.principal.null");
		Assert.isTrue(followUp.getUser().equals(user), "message.error.followUp.user.owner");
		Assert.isTrue(followUp.getArticle().getWriter().equals(user), "message.error.followUp.heDoesntWriteTheArticle");
		Assert.isTrue(!followUp.getArticle().getIsDraft(), "message.error.followUp.articleIsDraft");
		Assert.notNull(followUp.getArticle().getNewspaper().getPublicationDate(), "message.error.followUp.theNewspaperHasntBeenPublishedYet");
		Assert.isTrue(followUp.getArticle().getNewspaper().getPublicationDate().before(new Date()), "message.error.followUp.publicationDateIsPast");

		// Paso 1: realizo la entidad del servicio FollowUp

		result = this.save(followUp);

		return result;
	}

	public Collection<FollowUp> findAll() {
		Collection<FollowUp> result = null;
		result = this.followUpRepository.findAll();
		return result;
	}

	public FollowUp findOne(final int followUpId) {
		FollowUp result = null;
		result = this.followUpRepository.findOne(followUpId);
		return result;
	}

	public void flush() {
		this.followUpRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Collection<FollowUp> findPublicFollowUps() {
		Collection<FollowUp> result = null;
		result = this.followUpRepository.findPublicFollowUps();
		return result;
	}
	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 17.6.1

	public Double avgFollowUpsPerArticle() {
		Double result = null;
		result = this.followUpRepository.avgFollowUpsPerArticle();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 17.6.2

	public Double avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished() {
		Double result = null;
		result = this.followUpRepository.avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 17.6.3

	public Double avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished() {
		Double result = null;
		result = this.followUpRepository.avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished();
		return result;
	}

	public boolean canISeeDisplayThisFollowUp(final int newspaperId, final int customerId) {
		boolean result = false;
		result = this.followUpRepository.canISeeDisplayThisFollowUp(newspaperId, customerId);
		return result;
	}
}
