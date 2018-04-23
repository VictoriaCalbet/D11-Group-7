
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.RendezvousService;
import domain.Actor;
import domain.Category;
import domain.Rendezvous;
import domain.form.CategoryForm;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CategoryService		categoryService;


	public RendezvousController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer rendezvousId, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
		final CategoryForm categoryForm = new CategoryForm();
		final Collection<Category> categories = this.categoryService.findAll();

		if (rendezvousId == null)
			try {
				final Actor a = this.actorService.findByPrincipal();
				rendezvouses = this.rendezvousService.findRendezvousesLogged(a);
			} catch (final Throwable oops) {
				rendezvouses = this.rendezvousService.findRendezvousesNotLogged();
			}
		else
			try {
				rendezvouses = this.rendezvousService.findRendezvousesSimilarsLogged(rendezvousId);
			} catch (final Throwable oops) {
				rendezvouses = this.rendezvousService.findRendezvousSimilarNotLogged(rendezvousId);
			}

		//RSVP button control
		Collection<Rendezvous> principalRendezvouses = new ArrayList<Rendezvous>();
		if (this.actorService.checkLogin()) {
			final Actor principal = this.actorService.findByPrincipal();
			principalRendezvouses = this.rendezvousService.findAllPrincipalRsvps(principal.getId());
		}

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("principalRendezvouses", principalRendezvouses);
		result.addObject("categoryForm", categoryForm);
		result.addObject("categories", categories);
		result.addObject("message", message);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;
	}

	@RequestMapping(value = "/listCategory", method = RequestMethod.POST, params = "save")
	public ModelAndView listCategory(@Valid final CategoryForm categoryForm, final BindingResult binding, @RequestParam(required = false) final String message) {
		ModelAndView result = null;
		Collection<Category> categories = null;
		Collection<Rendezvous> rendezvouses = null;
		Collection<Rendezvous> principalRendezvouses = null;

		categories = this.categoryService.findAll();

		if (categoryForm.getCategoryId() != 0) {
			Assert.notNull(this.categoryService.findOne(categoryForm.getCategoryId()), "message.error.category.null");
			rendezvouses = this.rendezvousService.findRendezvousByCategories(categoryForm.getCategoryId());

			//RSVP button control
			principalRendezvouses = new ArrayList<Rendezvous>();
			if (this.actorService.checkLogin()) {
				final Actor principal = this.actorService.findByPrincipal();
				principalRendezvouses = this.rendezvousService.findAllPrincipalRsvps(principal.getId());
			}
		} else
			try {
				final Actor a = this.actorService.findByPrincipal();
				rendezvouses = this.rendezvousService.findRendezvousesLogged(a);
			} catch (final Throwable oops) {
				rendezvouses = this.rendezvousService.findRendezvousesNotLogged();
			}

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("principalRendezvouses", principalRendezvouses);
		result.addObject("categoryForm", categoryForm);
		result.addObject("categories", categories);
		result.addObject("message", message);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;
	}

}
