
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArticleRepository;
import domain.Actor;
import domain.Article;
import domain.Customer;
import domain.FollowUp;
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

	@Autowired
	private CustomerService		customerService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ArticleService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Article create() {
		final Article result = new Article();
		result.setIsDraft(true);
		result.setPictures(new ArrayList<String>());
		result.setFollowUps(new HashSet<FollowUp>());
		return result;
	}
	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Article save(final Article article) {
		Assert.notNull(article, "message.error.article.null");
		Article result;
		result = this.articleRepository.save(article);
		return result;
	}

	public Article saveFromCreate(final Article article) {
		Assert.notNull(article, "message.error.article.null");
		Assert.isTrue(article.getPublicationMoment() == null, "message.error.article.publicationDate.null");

		final Article result;

		User writer = this.userService.findByPrincipal();
		article.setWriter(writer);

		Assert.isTrue(article.getNewspaper().getPublicationDate() == null, "message.error.article.newspaper.publicationDate.null");

		result = this.save(article);

		writer = this.userService.findByPrincipal();
		writer.getArticles().add(result);
		this.userService.save(writer);

		return result;
	}
	public Article saveFromEdit(final Article article) {
		Assert.notNull(article, "message.error.article.null");
		Assert.isTrue(article.getPublicationMoment() == null, "message.error.article.publicationDate.null");

		Article result;
		final User principal = this.userService.findByPrincipal();

		Assert.isTrue(article.getWriter().getId() == principal.getId(), "message.error.article.writer.owner");
		Assert.isTrue(article.getNewspaper().getPublicationDate() == null, "message.error.article.newspaper.publicationDate.null");

		result = this.save(article);
		return result;
	}
	public void delete(final Article article) {
		Assert.notNull(article, "message.error.article.null");

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"), "message.error.article.delete.admin");

		final User writer = article.getWriter();
		writer.getArticles().remove(article);
		this.userService.save(writer);

		this.articleRepository.delete(article);
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

	public Collection<Article> findAllFromNewspaperSubscriptionByKeywordAndCustomerId(final String keyword) {
		Collection<Article> result = null;
		final Customer customer = this.customerService.findByPrincipal();
		result = this.articleRepository.findAllFromNewspaperSubscriptionByKeywordAndCustomerId(keyword, customer.getId());
		return result;
	}

	public Collection<Article> findAllFromVolumeSubscriptionByKeywordAndCustomerId(final String keyword) {
		Collection<Article> result = null;
		final Customer customer = this.customerService.findByPrincipal();
		result = this.articleRepository.findAllFromVolumeSubscriptionByKeywordAndCustomerId(keyword, customer.getId());
		return result;
	}
}
