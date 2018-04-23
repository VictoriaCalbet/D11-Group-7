
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Newspaper;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {

	@Query("select news from Newspaper news where news.isPrivate is true and news.publicationDate is not null and news.id not in (select subs.newspaper.id from Subscription subs where subs.customer.id = ?1)")
	Collection<Newspaper> findAvailableNewspapersByCustomerId(int customerId);

	@Query("select n from Newspaper n where n.publicationDate != null and n.isPrivate = false")
	Collection<Newspaper> findPublicated();

	@Query("select n from Newspaper n where n.publicationDate != null")
	Collection<Newspaper> findPublicatedAll();

	@Query("select n from Newspaper n where (n.title like %?1% or n.description like %?1%) and n.publicationDate != null")
	Collection<Newspaper> findNewspaperByKeyWord(String keyWord);

	@Query("select count(a) from Newspaper n join n.articles a where n.id=?1 and a.isDraft=false")
	Integer numArticlesFinalOfNewspaper(int newspaperId);

	@Query("select n from Newspaper n join n.subscriptions s join s.customer c where c.id=?1")
	Collection<Newspaper> findNewspaperSubscribedOfCustomer(int customerId);

	@Query("select n from Newspaper n where (n.title like %?1% or n.description like %?1%) and n.publicationDate != null and n.isPrivate = false")
	Collection<Newspaper> findNewspaperByKeyWordNotPrivate(String keyWord);

	@Query("select n from Newspaper n where (n.title like %?1% or n.description like %?1%) and n.publisher.id = ?2")
	Collection<Newspaper> findNewspaperByKeyWordByUser(String keyWord, int userId);

	@Query("select n from Newspaper n where (n.title like %?1% or n.description like %?1%)")
	Collection<Newspaper> getTabooNewspapers(String tabooWord);

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.1
	@Query("select avg(usr.newspapers.size) from User usr")
	Double avgNewspaperCreatedPerUser();

	@Query("select sqrt(sum(usr.newspapers.size * usr.newspapers.size) / count(usr.newspapers.size) - (avg(usr.newspapers.size) * avg(usr.newspapers.size))) from User usr")
	Double stdNewspapercreatedPerUser();

	// Acme-Newspaper 1.0 - Requisito 7.3.4
	@Query("select n1 from Newspaper n1 where n1.articles.size >= 1.1*(select avg(n1.articles.size) from Newspaper n1)")
	Collection<Newspaper> newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg();

	// Acme-Newspaper 1.0 - Requisito 7.3.5
	@Query("select n1 from Newspaper n1 where n1.articles.size >= 0.9*(select avg(n1.articles.size) from Newspaper n1)")
	Collection<Newspaper> newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg();

	// Acme-Newspaper 1.0 - Requisito 24.1.1
	@Query("select count(n1)*1.0/(select count(n2)*1.0 from Newspaper n2 where n2.isPrivate is true) from Newspaper n1 where n1.isPrivate is false")
	Double ratioOfPublicVsPrivateNewspapers();

	// Acme-Newspaper 1.0 - Requisito 24.1.5
	@Query("select count(n1)*1.0/(select count(n2)*1.0 * (select count(u) from User u where u.newspapers is not empty) from Newspaper n2 where n2.isPrivate is true) from Newspaper n1 where n1.isPrivate is false")
	Double avgRatioOfPrivateVsPublicNewspaperPerPublisher();

	// Not publicated yet and public newspapers
	@Query("select n from Newspaper n where n.publicationDate = null")
	Collection<Newspaper> findAllNotPublished();
}
