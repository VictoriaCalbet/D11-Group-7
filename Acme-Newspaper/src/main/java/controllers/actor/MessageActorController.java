/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.actor;

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
import services.FolderService;
import services.forms.MessageFormService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.forms.MessageForm;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController {

	@Autowired
	private MessageFormService	messageFormService;

	@Autowired
	private FolderService		folderService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public MessageActorController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final Integer folderId) {
		final ModelAndView result;
		final Collection<Message> messages;

		final Folder folder = this.folderService.findOne(folderId);
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(folder);
		Assert.isTrue(principal.getFolders().contains(folder));

		messages = folder.getMessages();

		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/list.do");
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		MessageForm messageForm;

		messageForm = this.messageFormService.create();
		result = this.createEditModelAndView(messageForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int messageId) {
		final ModelAndView result;
		MessageForm messageForm;

		messageForm = this.messageFormService.createFromMessage(messageId);
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
				if (messageForm.getId() != 0)
					this.messageFormService.saveFromEdit(messageForm);
				else
					this.messageFormService.saveFromCreate(messageForm);
				result = new ModelAndView("redirect:../../folder/actor/list.do");
			} catch (final Throwable oops) {
				String messageError = "message.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(messageForm, messageError);
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final MessageForm messageForm, final BindingResult binding) {
		ModelAndView result;

		try {
			this.messageFormService.delete(messageForm);
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
		Collection<Actor> recipients;
		Collection<Folder> folders;
		Actor principal;

		priorities = new ArrayList<String>();
		priorities.add("HIGH");
		priorities.add("NEUTRAL");
		priorities.add("LOW");

		recipients = this.actorService.findAll();
		principal = this.actorService.findByPrincipal();
		recipients.remove(principal);

		folders = principal.getFolders();

		result = new ModelAndView("message/edit");
		result.addObject("messageForm", messageForm);
		result.addObject("message", message);
		result.addObject("priorities", priorities);
		result.addObject("recipients", recipients);
		result.addObject("folders", folders);
		result.addObject("requestURI", "message/actor/edit.do");

		return result;
	}
}
