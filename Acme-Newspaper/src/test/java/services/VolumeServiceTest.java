
package services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Newspaper;
import domain.Volume;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class VolumeServiceTest extends AbstractTest {

	// The SUT (Service Under Test) -------------------------------------------

	@Autowired
	private VolumeService		volumeService;

	@Autowired
	private NewspaperService	newspaperService;


	// Tests ------------------------------------------------------------------
	/**
	 * Acme-Newspaper 2.0: Requirement 10.1
	 * 
	 * An actor who is authenticated as a user must be able to:
	 * Create a volume with as many published newspapers as he or she wishes.
	 * Note that the newspapers in a volume can be added or removed at any time.
	 * The same newspaper may be used to create different volumes.
	 * 
	 * Positive test1: A user creates a volume, and adds 2 publicated newspapers
	 * Negative test2: A customer tries to create a volume
	 * Negative test3: An agent tries to create a volume
	 * Negative test4: A null actor tries to create a volume
	 * Negative test5: An admin tries to create a volume
	 * Negative test6: A user tries to create a volume but put the title in blank
	 * Negative test7: A user tries to create a volume but put the description as null
	 * Negative test8: A user tries to create a volume but does not choose any newspaper
	 * Negative test9: A user tries to create a volume with a future year
	 */
	@Test
	public void testCreateVolumeDriver() {

		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));
		final Newspaper n2 = this.newspaperService.findOne(this.getEntityId("newspaper2"));
		final Collection<Newspaper> newspapers = new ArrayList<Newspaper>();

		n1.setPublicationDate(new Date(System.currentTimeMillis() - 50000000));
		newspapers.add(n1);
		newspapers.add(n2);
		final Object testingData[][] = {

			/** userPrincipal,title,description, year, newspapers ,exception */
			{
				"user2", "Title of volume1", "Description of volume1", 2017, newspapers, null
			}, {
				"customer2", "Title of volume1", "Description of volume1", 2017, newspapers, IllegalArgumentException.class
			}, {
				"agent2", "Title of volume1", "Description of volume1", 2017, newspapers, IllegalArgumentException.class
			}, {
				null, "Title of volume1", "Description of volume1", 2017, newspapers, IllegalArgumentException.class
			}, {
				"admin", "Title of volume1", "Description of volume1", 2017, newspapers, IllegalArgumentException.class
			}, {
				"user2", "", "Description of volume1", 2017, newspapers, ConstraintViolationException.class
			}, {
				"user2", "Title of volume1", null, 2017, newspapers, ConstraintViolationException.class
			}, {
				"user2", "Title of volume1", "Description of volume1", 2017, null, NullPointerException.class
			}, {
				"user2", "Title of volume1", "Description of volume1", 2019, newspapers, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateVolumeTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (int) testingData[i][3], (Collection<Newspaper>) testingData[i][4], (Class<?>) testingData[i][5]);
	}
	protected void testCreateVolumeTemplate(final String username, final String title, final String description, final int year, final Collection<Newspaper> newspapers, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);

			final Volume v = this.volumeService.create();
			v.setTitle(title);
			v.setDescription(description);
			v.setYear(year);
			v.setNewspapers(newspapers);
			this.volumeService.saveFromCreate(v);
			this.unauthenticate();
			this.volumeService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper 2.0: Save a volume
	 * 
	 * 
	 * Positive test1: User1, the creator of the volume1, modifies the volume, and add publicated newspapers
	 * Negative test2: The user2 tries to modify the volume1, but he is not the creator
	 * Negative test3: User1, the creator of the volume1, tries to add not publicated newspapers
	 * Negative test4: An agent tries to modify a volume
	 * Negative test5: An anonymous actor tries to modify a volume
	 * Negative test6: An admin tries to modify a volume
	 * Negative test7: The creator of the volume1 tries to modify it with title in blank
	 * Negative test8: The creator of volume1 tries to modify it but null description
	 * Negative test9: The creator of volume1 tries to modify it but does not choose newspapers
	 */

	@Test
	public void testSaveVolumeDriver() {

		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));
		final Newspaper n2 = this.newspaperService.findOne(this.getEntityId("newspaper2"));
		final Newspaper n4 = this.newspaperService.findOne(this.getEntityId("newspaper4"));
		final Collection<Newspaper> newspapers1 = new ArrayList<Newspaper>();
		final Collection<Newspaper> newspapers2 = new ArrayList<Newspaper>();
		final Volume v1 = this.volumeService.findOne(this.getEntityId("volume1"));
		newspapers1.add(n1);
		newspapers1.add(n2);
		newspapers2.add(n1);
		//Newspaper4 has not been published already
		//User1 is the owner of volume1
		newspapers2.add(n4);
		final Object testingData[][] = {

			/** userPrincipal,volume,title,description, year, newspapers ,exception */
			{
				"user1", v1, "Title of Testvolume1", "Description of Testvolume1", 2017, newspapers1, null
			}, {
				"user2", v1, "Title of volume1", "Description of volume1", 2017, newspapers1, IllegalArgumentException.class
			}, {
				"user1", v1, "Title of volume1", "Description of volume1", 2017, newspapers2, IllegalArgumentException.class
			}, {
				"agent2", v1, "Title of volume1", "Description of volume1", 2017, newspapers1, IllegalArgumentException.class
			}, {
				null, v1, "Title of volume1", "Description of volume1", 2017, newspapers1, IllegalArgumentException.class
			}, {
				"admin", v1, "Title of volume1", "Description of volume1", 2017, newspapers1, IllegalArgumentException.class
			}, {
				"user1", v1, "", "Description of volume1", 2017, newspapers1, ConstraintViolationException.class
			}, {
				"user1", v1, "Title of volume1", null, 2017, newspapers1, ConstraintViolationException.class
			}, {
				"user1", v1, "Title of volume1", "Description of volume1", 2017, null, NullPointerException.class
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveVolumeTemplate((String) testingData[i][0], (Volume) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (int) testingData[i][4], (Collection<Newspaper>) testingData[i][5], (Class<?>) testingData[i][6]);
	}
	protected void testSaveVolumeTemplate(final String username, final Volume volume, final String title, final String description, final int year, final Collection<Newspaper> newspapers, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);

			volume.setTitle(title);
			volume.setDescription(description);
			volume.setYear(year);
			volume.setNewspapers(newspapers);
			this.volumeService.saveFromEdit(volume);
			this.unauthenticate();
			this.volumeService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper 2.0: Add a newspaper to a volume
	 * 
	 * Positive test1: User1 tries to add a newspaper to a volume that he is the owner
	 * Negative test2: User2 tries to add a newspaper but he is not the owner
	 * Negative test3: User1, the creator of volume1, tries to add a not publicated newspaper
	 * 
	 */
	@Test
	public void testAddNewspaperToVolumeDriver() {

		final Newspaper n2 = this.newspaperService.findOne(this.getEntityId("newspaper2"));
		final Newspaper n4 = this.newspaperService.findOne(this.getEntityId("newspaper4"));
		final Volume v1 = this.volumeService.findOne(this.getEntityId("volume1"));
		//Newspaper4 is not published

		final Object testingData[][] = {

			/** userPrincipal,volume,newspaper, exception */
			{
				"user1", v1, n2, null
			}, {
				"user2", v1, n2, IllegalArgumentException.class
			}, {
				"user1", v1, n4, IllegalArgumentException.class
			}, {
				"admin", v1, n4, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.testAddNewspaperToVolumeTemplate((String) testingData[i][0], (Volume) testingData[i][1], (Newspaper) testingData[i][2], (Class<?>) testingData[i][3]);
	}
	protected void testAddNewspaperToVolumeTemplate(final String username, final Volume volume, final Newspaper newspaper, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);

			this.volumeService.addNewspaperToVolume(newspaper.getId(), volume.getId());
			this.unauthenticate();
			this.volumeService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper 2.0: Delete a newspaper from a volume
	 * 
	 * Positive test1: User1 delete a newspaper contained in a volume that he is the owner
	 * Negative test2: User2 tries to delete a newspaper from a volume but he is not the owner
	 * Negative test3: User1, the creator of volume1, tries to delete a newspaper that is not in the volume
	 * Negative test4: The admin tries to delete a newspaper
	 * 
	 */
	@Test
	public void testDeleteNewspaperFromVolumeDriver() {

		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));
		final Newspaper n2 = this.newspaperService.findOne(this.getEntityId("newspaper2"));
		final Newspaper n4 = this.newspaperService.findOne(this.getEntityId("newspaper4"));
		final Volume v1 = this.volumeService.findOne(this.getEntityId("volume1"));
		//Newspaper4 does not belong to any volume.
		final Object testingData[][] = {

			/** userPrincipal,volume,newspaper,exception */
			{
				"user1", v1, n1, null
			}, {
				"user2", v1, n2, IllegalArgumentException.class
			}, {
				"user1", v1, n4, IllegalArgumentException.class
			}, {
				"admin", v1, n4, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDeleteNewspaperFromVolumeTemplate((String) testingData[i][0], (Volume) testingData[i][1], (Newspaper) testingData[i][2], (Class<?>) testingData[i][3]);
	}
	protected void testDeleteNewspaperFromVolumeTemplate(final String username, final Volume volume, final Newspaper newspaper, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {

			this.authenticate(username);
			this.volumeService.deleteNewspaperFromVolume(newspaper.getId(), volume.getId());
			this.unauthenticate();
			this.volumeService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
}
