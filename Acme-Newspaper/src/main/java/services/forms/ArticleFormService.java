
package services.forms;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.ArticleService;
import services.UserService;
import domain.Article;
import domain.forms.ArticleForm;

@Service
@Transactional
public class ArticleFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private ArticleService	articleService;
	@Autowired
	private UserService		userService;


	// Constructors -----------------------------------------------------------

	public ArticleFormService() {
		super();
	}

	public ArticleForm create() {
		ArticleForm result;

		result = new ArticleForm();
		result.setIsDraft(true);
		result.setPictures(new ArrayList<String>());
		return result;
	}

	public ArticleForm create(final int articleId) {
		final Article a = this.articleService.findOne(articleId);
		Assert.isTrue(a.getWriter().getId() == this.userService.findByPrincipal().getId(), "message.error.article.writer.owner");
		Assert.isTrue(a.getIsDraft(), "message.error.article.draft");

		final ArticleForm articleForm = new ArticleForm();

		articleForm.setBody(a.getBody());
		articleForm.setPictures(a.getPictures());
		articleForm.setTitle(a.getTitle());
		articleForm.setSummary(a.getSummary());
		articleForm.setNewspaper(a.getNewspaper());
		articleForm.setId(a.getId());
		articleForm.setIsDraft(a.getIsDraft());

		return articleForm;
	}

	public Article saveFromCreate(final ArticleForm articleForm) {
		Assert.notNull(articleForm, "message.error.article.null");

		final Article article = this.articleService.create();

		article.setTitle(articleForm.getTitle());
		article.setId(articleForm.getId());
		article.setSummary(articleForm.getSummary());
		article.setBody(articleForm.getBody());
		article.setPictures(articleForm.getPictures());
		article.setWriter(this.userService.findByPrincipal());
		article.setNewspaper(articleForm.getNewspaper());
		article.setIsDraft(articleForm.getIsDraft());

		final Article result = this.articleService.saveFromCreate(article);

		return result;

	}

	public Article saveFromEdit(final ArticleForm articleForm) {
		Assert.notNull(articleForm, "message.error.article.null");

		final Article article = this.articleService.findOne(articleForm.getId());

		Assert.isTrue(article.getIsDraft(), "message.error.article.draft");
		Assert.isTrue(article.getNewspaper().getPublicationDate() == null, "message.error.article.newspaper.publicationDate.null");
		Assert.isTrue(article.getPublicationMoment() == null, "message.error.article.publicationDate.null");

		article.setTitle(articleForm.getTitle());
		article.setBody(articleForm.getBody());
		article.setId(articleForm.getId());
		article.setIsDraft(articleForm.getIsDraft());
		article.setNewspaper(articleForm.getNewspaper());
		article.setPictures(articleForm.getPictures());
		article.setSummary(articleForm.getSummary());

		final Article result = this.articleService.saveFromEdit(article);

		return result;

	}

}
