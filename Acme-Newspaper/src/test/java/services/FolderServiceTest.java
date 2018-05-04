
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FolderServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	/**
	 * Acme-Newspaper 2.0: Requirement 13.2:
	 * 
	 * An actor who is authenticated must be able to:
	 * Manage his or her message folders, except for the system folders.
	 * 
	 * Positive test1: Correct folder creation
	 * Negative test2: Folder creation without a name
	 * Negative test2: Folder creation unlogged
	 */
	@Test
	public void testSaveFromCreateFolder() {
		final Object[][] testingData = {
			{
				"admin", "testFolder1", "out box", null
			}, {
				"admin", null, "out box", NullPointerException.class
			}, {
				null, "testFolder3", "out box", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateFolderTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void testSaveFromCreateFolderTemplate(final String actor, final String name, final String parentName, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			this.authenticate(actor);
			final Actor principal = this.actorService.findByPrincipal();
			final Folder parentFolder = this.folderService.findOneByActorIdAndFolderName(principal.getId(), parentName);

			final Folder folder = this.folderService.create();
			folder.setName(name);
			folder.setParent(parentFolder);

			this.folderService.saveFromCreate(folder);
			this.folderService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

	/**
	 * Acme-Newspaper 2.0: Requirement 13.2:
	 * 
	 * An actor who is authenticated must be able to:
	 * Manage his or her message folders, except for the system folders.
	 * 
	 * Positive test1: Correct folder modification
	 * Negative test2: Wrong new name. Same as in box folder.
	 * Negative test2: Null name.
	 */
	@Test
	public void testSaveFromEditFolder() {
		final Object[][] testingData = {
			{
				"admin", "testFolder1", "testFolder1Edited", "out box", null
			}, {
				"admin", "testFolder1", "in box", "out box", IllegalArgumentException.class
			}, {
				"admin", "testFolder1", null, "out box", NullPointerException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromEditFolderTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
	}

	protected void testSaveFromEditFolderTemplate(final String actor, final String oldName, final String newName, final String parentName, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			this.authenticate(actor);
			final Actor principal = this.actorService.findByPrincipal();
			final Folder parentFolder = this.folderService.findOneByActorIdAndFolderName(principal.getId(), parentName);

			final Folder folder = this.folderService.create();
			folder.setName(oldName);
			folder.setParent(parentFolder);

			final Folder result = this.folderService.saveFromCreate(folder);

			result.setName(newName);
			this.folderService.saveFromEdit(result);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

	/**
	 * Acme-Newspaper 2.0: Requirement 13.2:
	 * 
	 * An actor who is authenticated must be able to:
	 * Manage his or her message folders, except for the system folders.
	 * 
	 * Positive test1: Correct folder deletion
	 */
	@Test
	public void testDeleteFolder() {
		final Object[][] testingData = {
			{
				"admin", "testFolder1", "out box", null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDeleteFolder((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void testDeleteFolder(final String actor, final String name, final String parentName, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			this.authenticate(actor);
			final Actor principal = this.actorService.findByPrincipal();
			final Folder parentFolder = this.folderService.findOneByActorIdAndFolderName(principal.getId(), parentName);

			final Folder folder = this.folderService.create();
			folder.setName(name);
			folder.setParent(parentFolder);

			final Folder result = this.folderService.saveFromCreate(folder);

			this.folderService.delete(result);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}
}
