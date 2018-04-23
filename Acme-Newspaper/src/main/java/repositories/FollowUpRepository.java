
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FollowUp;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 17.6.1
	@Query("select avg(art.followUps.size) from Article art")
	Double avgFollowUpsPerArticle();

	// Acme-Newspaper 1.0 - Requisito 17.6.2
	@Query("select count(f)*1.0/(select count(a)*1.0 from Article a) from FollowUp f where f.publicationMoment <= f.article.newspaper.publicationDate + 604800000")
	Double avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished();

	// Acme-Newspaper 1.0 - Requisito 17.6.3
	@Query("select count(f)*1.0/(select count(a)*1.0 from Article a) from FollowUp f where f.publicationMoment <= f.article.newspaper.publicationDate + 2*604800000")
	Double avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished();

	@Query("select fol from FollowUp fol where fol.article.isDraft is false and fol.article.newspaper.isPrivate is false and fol.article.newspaper.publicationDate is not null")
	Collection<FollowUp> findPublicFollowUps();

	@Query("select case when (count(news) > 0) then true else false end from NewspaperSubscription subs where subs.newspaper.id = ?1 and subs.customer.id = ?2")
	boolean canISeeDisplayThisFollowUp(int newspaperId, int customerId);

}
