
package services.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ArticleService;
import services.FollowUpService;
import services.UserService;
import domain.Article;
import domain.FollowUp;
import domain.User;
import domain.forms.FollowUpForm;

@Service
@Transactional
public class FollowUpFormService {

	// Supporting services ----------------------------------------------------

	@Autowired
	private FollowUpService	followUpService;

	@Autowired
	private UserService		userService;

	@Autowired
	private ArticleService	articleService;


	// Constructors -----------------------------------------------------------

	public FollowUpFormService() {
		super();
	}

	// Creación de formularios ------------------------------------------------

	// Utilizado al crear una nueva entidad de Announcement

	public FollowUpForm createFromCreate() {
		FollowUpForm result = null;
		User user = null;

		result = new FollowUpForm();
		user = this.userService.findByPrincipal();
		result.setUserId(user.getId());

		return result;
	}

	// Utilizado al editar una nueva entidad de Announcement

	public FollowUpForm createFromEdit(final int followUpId) {
		FollowUp followUp = null;
		FollowUpForm result = null;

		followUp = this.followUpService.findOne(followUpId);
		result = new FollowUpForm();

		result.setId(followUp.getId());
		result.setTitle(followUp.getTitle());
		result.setSummary(followUp.getSummary());
		result.setText(followUp.getText());
		result.setPictures(followUp.getPictures());
		result.setUserId(followUp.getUser().getId());
		result.setArticleId(followUp.getArticle().getId());

		return result;
	}

	// Reconstrucción de objetos (Reconstruct) --------------------------------

	public FollowUp saveFromCreate(final FollowUpForm followUpForm) {
		FollowUp followUp = null;
		User user = null;
		Article article = null;
		FollowUp result = null;

		Assert.notNull(followUpForm, "message.error.followUp.null");

		followUp = this.followUpService.create();
		user = this.userService.findOne(followUpForm.getUserId());
		article = this.articleService.findOne(followUpForm.getArticleId());

		Assert.notNull(followUp.getUser().equals(user), "message.error.followUp.heDoesntWriteTheArticle");

		followUp.setTitle(followUpForm.getTitle());
		followUp.setSummary(followUpForm.getSummary());
		followUp.setText(followUpForm.getText());
		followUp.setPictures(followUpForm.getPictures());
		followUp.setUser(user);
		followUp.setArticle(article);

		result = this.followUpService.saveFromCreate(followUp);

		return result;
	}

	public FollowUp saveFromEdit(final FollowUpForm followUpForm) {
		FollowUp followUp = null;
		User user = null;
		Article article = null;
		FollowUp result = null;

		Assert.notNull(followUpForm, "message.error.followUp.null");

		followUp = this.followUpService.findOne(followUpForm.getId());
		user = this.userService.findOne(followUpForm.getUserId());
		article = this.articleService.findOne(followUpForm.getArticleId());

		Assert.notNull(followUp.getUser().equals(user), "message.error.followUp.heDoesntWriteTheArticle");

		followUp.setTitle(followUpForm.getTitle());
		followUp.setSummary(followUpForm.getSummary());
		followUp.setText(followUpForm.getText());
		followUp.setPictures(followUpForm.getPictures());
		followUp.setUser(user);
		followUp.setArticle(article);

		result = this.followUpService.saveFromEdit(followUp);

		return result;
	}
}
