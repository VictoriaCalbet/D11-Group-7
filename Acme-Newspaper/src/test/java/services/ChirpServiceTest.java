
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Chirp;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChirpServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ChirpService	chirpService;

	//Supporting services

	@Autowired
	private UserService		userService;


	/**
	 * Acme-Newspaper Requirement 16.1
	 * An actor who is authenticated as a user must be able to:
	 * Post a chirp. Chirps may not be changed or deleted once they are posted.
	 * 
	 * Test 1: Positive case.
	 * Test 2: Negative case; the title is null
	 * Test 3: Negative case; the description is null
	 * 
	 */
	@Test
	public void testSaveFromCreateChirp() {
		// Chirp: title, description, user, expected exception.
		final User user = this.userService.findOne(this.getEntityId("user1"));
		final Object[][] testingData = {
			{
				"Title", "Description", user, null
			}, {
				null, "Description", user, IllegalArgumentException.class
			}, {
				"Title", null, user, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateChirpTemplate((String) testingData[i][0], (String) testingData[i][1], (User) testingData[i][2], (Class<?>) testingData[i][3]);

	}

	protected void testSaveFromCreateChirpTemplate(final String title, final String description, final User user, final Class<?> expectedException) {

		Class<?> caught = null;

		try {

			this.authenticate("user1");

			final Chirp result = this.chirpService.create();

			result.setTitle(title);
			result.setDescription(description);
			result.setUser(user);

			this.chirpService.saveFromCreate(result);
			this.chirpService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

	/**
	 * Acme-Newspaper Requirement 16.2
	 * An actor who is authenticated as a user must be able to:
	 * 
	 * Follow or unfollow another user. This test will be dedicated to following users functionality.
	 * 
	 * Test 1: Positive case
	 * Test 2: Negative case; the user tries to follow itself
	 * Test 3: Negative case; the user tries to follow the same user twice
	 */

	@Test
	public void testFollowUser() {
		// Chirp: User, user, expected exceptionfinal .
		final User user = this.userService.findOne(this.getEntityId("user1"));
		final User user2 = this.userService.findOne(this.getEntityId("user2"));
		final Object[][] testingData = {
			{
				user, null, null
			}, {
				user2, null, IllegalArgumentException.class
			}, {
				user, user, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testFollowUser((User) testingData[i][0], (User) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	protected void testFollowUser(final User user, final User user2, final Class<?> expectedException) {

		Class<?> caught = null;

		try {

			this.authenticate("user2");

			this.chirpService.followUser(user.getId());
			this.chirpService.flush();

			Assert.isTrue(this.userService.findByPrincipal().getFollowed().contains(user));

			if (user2 != null)
				this.chirpService.followUser(user2.getId());

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

	/**
	 * Acme-Newspaper Requirement 16.2
	 * An actor who is authenticated as a user must be able to:
	 * 
	 * Follow or unfollow another user. This test will be dedicated to unfollowing users functionality.
	 * 
	 * Test 1: Positive case
	 * Test 2: Negative case; the user to unfollow is null
	 * Test 3: Negative case; the user tries to unfollow a user they weren't previously following
	 */

	@Test
	public void testUnfollowUser() {
		// Arguments: User, user, expected exception.

		final User user2 = this.userService.findOne(this.getEntityId("user2"));
		final Object[][] testingData = {
			{
				user2, null, null
			}, {
				null, null, NullPointerException.class
			}, {
				user2, user2, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testUnfollowUser((User) testingData[i][0], (User) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	protected void testUnfollowUser(final User user, final User user2, final Class<?> expectedException) {

		Class<?> caught = null;

		try {

			this.authenticate("user1");

			this.chirpService.unfollowUser(user.getId());
			this.chirpService.flush();
			Assert.isTrue(!this.userService.findByPrincipal().getFollowed().contains(user));

			if (user2 != null) {

				this.chirpService.unfollowUser(user2.getId());
				this.chirpService.flush();
			}

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

	/**
	 * Acme-Newspaper Requirement 17.5
	 * An actor who is authenticated as an administrator must be able to:
	 * 
	 * Remove a chirp that he or she thinks is inappropriate.
	 * 
	 * Test 1: Positive case
	 * Test 2: Negative case; the chirp to delete is null
	 * Test 3: Negative case; the logged actor isn't an administrator
	 */

	@Test
	public void testDeleteChirp() {
		// Chirp: Chirp, loggedactor, expected exceptifinal on.
		final Chirp chirp = this.chirpService.findOne(this.getEntityId("chirp1"));
		final Object[][] testingData = {
			{
				chirp, "admin", null
			}, {
				null, "admin", IllegalArgumentException.class
			}, {
				chirp, "user1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDeleteChirp((Chirp) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	protected void testDeleteChirp(final Chirp chirp, final String loggedUser, final Class<?> expectedException) {

		Class<?> caught = null;

		try {

			this.authenticate(loggedUser);

			this.chirpService.delete(chirp);
			this.chirpService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

}
