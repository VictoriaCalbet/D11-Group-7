
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArticleRepository;
import domain.Actor;
import domain.Article;
import domain.FollowUp;
import domain.Newspaper;
import domain.User;

@Service
@Transactional
public class ArticleService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private ArticleRepository	articleRepository;

	@Autowired
	private UserService			userService;
	@Autowired
	private ActorService		actorService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ArticleService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Article create() {
		final Article result = new Article();
		final User principal = this.userService.findByPrincipal();
		result.setWriter(principal);
		result.setIsDraft(true);
		return result;
	}
	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Article save(final Article article) {
		Assert.notNull(article);
		Article result;
		result = this.articleRepository.save(article);
		return result;
	}

	public Article saveFromCreate(final Article article) {
		Assert.notNull(article);
		Article result = article;
		final User writer = this.userService.findByPrincipal();
		result.setWriter(writer);
		final Collection<Article> principalArticles = writer.getArticles();
		principalArticles.add(result);
		writer.setArticles(principalArticles);
		this.userService.save(writer);
		final Collection<FollowUp> followUps = new ArrayList<FollowUp>();
		result.setFollowUps(followUps);
		Assert.isTrue(article.getPublicationMoment() == null);
		result = this.articleRepository.save(result);

		return result;
	}
	public Article saveFromEdit(final Article article) {
		Assert.notNull(article);
		final Article result = article;
		final User principal = this.userService.findByPrincipal();
		final Newspaper newspaper = article.getNewspaper();

		Assert.isTrue(principal == result.getWriter());
		Assert.isTrue(newspaper.getPublicationDate() == null);
		Assert.isTrue(result.getPublicationMoment() == null);
		this.articleRepository.save(article);
		return result;
	}
	public void delete(final Article article) {

		Assert.notNull(article);
		final Article result = article;
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"));
		final User writer = result.getWriter();

		final Collection<Article> writerArticles = writer.getArticles();

		writerArticles.remove(result);
		writer.setArticles(writerArticles);

		this.articleRepository.delete(result);
	}
	public Collection<Article> findAll() {
		Collection<Article> result = null;
		result = this.articleRepository.findAll();
		return result;
	}

	public Article findOne(final int articleId) {
		Article result = null;
		result = this.articleRepository.findOne(articleId);
		return result;
	}

	public void flush() {
		this.articleRepository.flush();
	}
	// Other business methods -------------------------------------------------

	public Collection<Article> findAllPublishedByUserId(final int userId) {
		Collection<Article> result = null;
		result = this.articleRepository.findAllPublishedByUserId(userId);
		return result;
	}

	public Collection<Article> findAvailableArticlesToCreateFollowUps(final int userId) {
		Collection<Article> result = null;
		result = this.articleRepository.findAvailableArticlesToCreateFollowUps(userId);
		return result;
	}

	public Collection<Article> findAllByNewspaperId(final int newspaperId) {
		Collection<Article> result = null;
		result = this.articleRepository.findAllByNewspaperId(newspaperId);
		return result;
	}

	public Collection<Article> getTabooArticles(final String keyword) {
		Assert.notNull(keyword);

		Collection<Article> result;

		result = this.articleRepository.getTabooArticles(keyword);

		return result;
	}

	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.2

	public Double avgArticlesWrittenByWriter() {
		Double result = null;
		result = this.articleRepository.avgArticlesWrittenByWriter();
		return result;
	}

	public Double stdArticlesWrittenByWriter() {
		Double result = null;
		result = this.articleRepository.stdArticlesWrittenByWriter();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 7.3.3

	public Double avgArticlesPerNewspaper() {
		Double result = null;
		result = this.articleRepository.avgArticlesPerNewspaper();
		return result;
	}

	public Double stdArticlesPerNewspaper() {
		Double result = null;
		result = this.articleRepository.stdArticlesPerNewspaper();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 24.1.2
	public Double avgNoArticlesPerPrivateNewspapers() {
		Double result = null;
		result = this.articleRepository.avgNoArticlesPerPrivateNewspapers();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 24.1.3
	public Double avgNoArticlesPerPublicNewspapers() {
		Double result = null;
		result = this.articleRepository.avgNoArticlesPerPublicNewspapers();
		return result;
	}

	public Collection<Article> findArticleByKeyword(final String keyword) {
		return this.articleRepository.findArticleByKeyword(keyword);
	}

	public Collection<Article> findArticleByKeywordAndNewspaperId(final String keyword, final int newspaperId) {
		return this.articleRepository.findArticleByKeywordAndNewspaperId(keyword, newspaperId);
	}

	public Collection<Article> findArticleByKeywordByUser(final String keyword, final int userId) {
		return this.articleRepository.findArticleByKeywordByUser(keyword, userId);
	}
}
