/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.forms.MessageFormService;
import controllers.AbstractController;
import domain.forms.MessageForm;

@Controller
@RequestMapping("/message/administrator")
public class MessageAdministratorController extends AbstractController {

	@Autowired
	private MessageFormService	messageFormService;


	// Constructors -----------------------------------------------------------

	public MessageAdministratorController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		MessageForm messageForm;

		messageForm = this.messageFormService.createForBroadcast();
		result = this.createEditModelAndView(messageForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MessageForm messageForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(messageForm);
		else
			try {
				this.messageFormService.saveFromBroadcast(messageForm);
				result = new ModelAndView("redirect:../../folder/actor/list.do");
			} catch (final Throwable oops) {
				String messageError = "message.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(messageForm, messageError);
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	public ModelAndView createEditModelAndView(final MessageForm messageForm) {
		ModelAndView result;

		result = this.createEditModelAndView(messageForm, null);

		return result;
	}

	public ModelAndView createEditModelAndView(final MessageForm messageForm, final String message) {
		ModelAndView result;
		final Collection<String> priorities;

		priorities = new ArrayList<String>();
		priorities.add("HIGH");
		priorities.add("NEUTRAL");
		priorities.add("LOW");

		result = new ModelAndView("message/broadcast");
		result.addObject("messageForm", messageForm);
		result.addObject("message", message);
		result.addObject("priorities", priorities);
		result.addObject("requestURI", "message/administrator/edit.do");

		return result;
	}
}
