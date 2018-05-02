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
import services.forms.FolderFormService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.forms.FolderForm;

@Controller
@RequestMapping("/folder/actor")
public class FolderActorController extends AbstractController {

	@Autowired
	private FolderService		folderService;

	@Autowired
	private FolderFormService	folderFormService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public FolderActorController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer folderId) {
		ModelAndView result;
		final Collection<Folder> folders;

		if (folderId == null)
			folders = this.folderService.findAllRootFoldersByActor();
		else
			folders = this.folderService.findAllChilderFoldersByFolderId(folderId);

		result = new ModelAndView("folder/list");
		result.addObject("folders", folders);
		result.addObject("requestURI", "folder/actor/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		FolderForm folderForm;

		folderForm = this.folderFormService.create();
		result = this.createEditModelAndView(folderForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int folderId) {
		final ModelAndView result;
		FolderForm folderForm;

		final Folder folder = this.folderService.findOne(folderId);
		final Actor principal = this.actorService.findByPrincipal();

		Assert.isTrue(principal.getId() == folder.getActor().getId());
		Assert.isTrue(!folder.getSystem());

		folderForm = this.folderFormService.createFromFolder(folderId);
		result = this.createEditModelAndView(folderForm);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FolderForm folderForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(folderForm);
		else
			try {
				if (folderForm.getId() != 0)
					this.folderFormService.saveFromEdit(folderForm);
				else
					this.folderFormService.saveFromCreate(folderForm);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				String messageError = "folder.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(folderForm, messageError);
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final FolderForm folderForm, final BindingResult binding) {
		ModelAndView result;

		try {
			this.folderFormService.delete(folderForm);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			String messageError = "folder.commit.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result = this.createEditModelAndView(folderForm, messageError);
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	public ModelAndView createEditModelAndView(final FolderForm folderForm) {
		ModelAndView result;

		result = this.createEditModelAndView(folderForm, null);

		return result;
	}

	public ModelAndView createEditModelAndView(final FolderForm folderForm, final String message) {
		ModelAndView result;

		final Collection<Folder> possibleParentFolders;

		possibleParentFolders = this.folderService.findAllPossibleParentFolders(folderForm.getId());

		result = new ModelAndView("folder/edit");
		result.addObject("folderForm", folderForm);
		result.addObject("message", message);
		result.addObject("possibleParentFolders", possibleParentFolders);
		result.addObject("requestURI", "folder/actor/edit.do");

		return result;
	}
}
