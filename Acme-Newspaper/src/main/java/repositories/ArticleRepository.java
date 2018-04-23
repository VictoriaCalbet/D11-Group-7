
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Query("select a from Article a where a.isDraft IS FALSE AND a.publicationMoment < CURRENT_TIMESTAMP AND a.newspaper.isPrivate IS FALSE AND a.newspaper.publicationDate < CURRENT_TIMESTAMP AND a.writer.id = ?1")
	Collection<Article> findAllPublishedByUserId(int userId);

	@Query("select art from Article art where art.newspaper.publicationDate is not null and art.isDraft is false and art.writer.id = ?1")
	Collection<Article> findAvailableArticlesToCreateFollowUps(int userId);

	@Query("select a from Article a where a.newspaper.id = ?1")
	Collection<Article> findAllByNewspaperId(int newspaperId);

	@Query("select a from Article a where (a.title like %?1% or a.summary like %?1% or a.body like %?1%)")
	Collection<Article> getTabooArticles(String tabooWord);

	@Query("select a from Article a where (a.title like %?1% or a.summary like %?1% or a.body like %?1%) and a.newspaper.id = ?2")
	Collection<Article> findArticleByKeywordAndNewspaperId(String keyword, int newspaperId);

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.2
	@Query("select avg(usr.articles.size) from User usr")
	Double avgArticlesWrittenByWriter();

	@Query("select sqrt(sum(usr.articles.size * usr.articles.size) / count(usr.articles.size) - (avg(usr.articles.size) * avg(usr.articles.size))) from User usr")
	Double stdArticlesWrittenByWriter();

	// Acme-Newspaper 1.0 - Requisito 7.3.3
	@Query("select avg(news.articles.size) from Newspaper news")
	Double avgArticlesPerNewspaper();

	@Query("select sqrt(sum(news.articles.size * news.articles.size) / count(news.articles.size) - (avg(news.articles.size) * avg(news.articles.size))) from Newspaper news")
	Double stdArticlesPerNewspaper();

	@Query("select a from Article a where (a.title like %?1% or a.summary like %?1% or a.body like %?1% and a.publicationMoment != null ) and a.isDraft is false and a.newspaper.isPrivate = false and a.newspaper.publicationDate != null)")
	Collection<Article> findArticleByKeyword(String keyword);

	@Query("select a from Article a where (a.title like %?1% or a.summary like %?1% or a.body like %?1%) and a.writer.id = ?2")
	Collection<Article> findArticleByKeywordByUser(String keyword, int userId);

	// Acme-Newspaper 1.0 - Requisito 24.1.2
	@Query("select avg(n.articles.size) from Newspaper n where n.isPrivate is true")
	Double avgNoArticlesPerPrivateNewspapers();

	// Acme-Newspaper 1.0 - Requisito 24.1.3
	@Query("select avg(n.articles.size) from Newspaper n where n.isPrivate is false")
	Double avgNoArticlesPerPublicNewspapers();
}
