package wcci.BlogPlatform.repos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import wcci.BlogPlatform.models.Tag;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TagCrudRepoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TagCrudRepo tagRepo;

	// Arrange
	private String testTagName01 = "tag1";
	private Tag testTag01 = new Tag(testTagName01);

	@Before
	public void persistTestEntities()
		{
		tagRepo.save(testTag01);

		entityManager.flush();
		entityManager.clear();
		}

	@Test
	public void shouldLoadASavedTag()
		{
		// Action
		Long assignedId = testTag01.getId();
		Tag tagToLoad = tagRepo.findById(assignedId).get();

		// Assert
		assertThat(testTag01, is(tagToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}

	@Test
	public void shouldLoadTagByName()
		{
		// Action
		Tag tagToLoad = tagRepo.findByName(testTagName01);

		// Assert
		assertThat(testTag01, is(tagToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}

	@Test
	public void shouldFailToLoadTagByWrongName()
		{
		// Action
		Tag tagToLoad = tagRepo.findByName("wrong name");

		// Assert
		assertThat(tagToLoad, is(nullValue()));
		}

	@Test
	public void shouldFailToSaveTagWithSameNameAsExistingTag()
		{
		// Arrange
		String duplicateTestTagName01 = new String(testTagName01);
		Tag duplicateTestTag01 = new Tag(duplicateTestTagName01);

		// Action
		long numberOfTagsBeforeSave = tagRepo.count();
		try
			{
			tagRepo.save(duplicateTestTag01);
			entityManager.flush();
			entityManager.clear();
			}
		catch (Exception ex)
			{
			// Traverse exception nesting to final cause.
			Throwable t = ex.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException))
				{
				t = t.getCause();
				}
			if (t instanceof ConstraintViolationException)
				{
				// Here you're sure you have a ConstraintViolationException, so you can handle it
				ConstraintViolationException cve = (ConstraintViolationException) t;
				System.out.println("--------CONSTRAINT NAME: " + cve.getConstraintName());
				entityManager.clear();
				entityManager.flush();
				}
			else
				{
				System.out.println("Exception not of class ConstraintViolationException!");
				System.out.println("-------------EXCEPTION MESSAGE------: " + ex.getMessage());
				}
			}
		long numberOfTagsAfterSave = tagRepo.count();

		// Assert
		assertEquals(numberOfTagsBeforeSave, numberOfTagsAfterSave);
		}
}
