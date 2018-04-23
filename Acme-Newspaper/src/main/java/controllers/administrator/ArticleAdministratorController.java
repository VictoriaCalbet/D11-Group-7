
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import controllers.AbstractController;
import domain.Article;

@Controller
@RequestMapping("/article/administrator")
public class ArticleAdministratorController extends AbstractController {

	//Services
	@Autowired
	private ArticleService	articleService;


	//Constructor

	public ArticleAdministratorController() {
		super();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int articleId) {
		ModelAndView result;
		final Article article = this.articleService.findOne(articleId);
		try {
			this.articleService.delete(article);
			result = new ModelAndView("redirect:/newspaper/administrator/list.do");
		} catch (final Throwable oops) {
			String messageError = "article.delete.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result = new ModelAndView("redirect:/newspaper/administrator/list.do");
			result.addObject("messageError", messageError);
		}
		return result;
	}
}
