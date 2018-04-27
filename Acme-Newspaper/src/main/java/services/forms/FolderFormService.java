
package services.forms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.FolderService;
import domain.Folder;
import domain.forms.FolderForm;

@Service
@Transactional
public class FolderFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private FolderService	folderService;


	// Constructors -----------------------------------------------------------

	public FolderFormService() {
		super();
	}

	public FolderForm create() {
		FolderForm result;

		result = new FolderForm();
		result.setId(0);

		return result;
	}

	public FolderForm createFromFolder(final int folderId) {
		FolderForm result;
		Folder folder;

		folder = this.folderService.findOne(folderId);

		result = new FolderForm();
		result.setId(folderId);
		result.setName(folder.getName());
		result.setSystem(folder.getSystem());

		if (folder.getParent() != null)
			result.setParentId(folder.getParent().getId());

		return result;
	}

	public Folder saveFromCreate(final FolderForm folderForm) {
		Assert.notNull(folderForm, "message.error.folderForm.null");

		Folder result;
		Folder folder;
		Folder parent;

		folder = this.folderService.create();
		parent = this.folderService.findOne(folderForm.getParentId());

		folder.setName(folderForm.getName());
		folder.setParent(parent);

		result = this.folderService.saveFromCreate(folder);

		return result;

	}

	public Folder saveFromEdit(final FolderForm folderForm) {
		Assert.notNull(folderForm, "message.error.folderForm.null");

		Folder result;
		Folder folder;
		Folder parent;

		folder = this.folderService.findOne(folderForm.getId());
		parent = this.folderService.findOne(folderForm.getParentId());

		folder.setName(folderForm.getName());
		folder.setParent(parent);

		result = this.folderService.saveFromEdit(folder);

		return result;

	}
}
