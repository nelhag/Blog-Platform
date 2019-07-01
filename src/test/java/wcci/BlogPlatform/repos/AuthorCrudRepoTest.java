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

import wcci.BlogPlatform.models.Author;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorCrudRepoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AuthorCrudRepo authorRepo;

	// Arrange
	private String testAuthorName01 = "author1";
	private Author testAuthor01 = new Author(testAuthorName01);

	@Before
	public void persistTestEntities()
		{
		authorRepo.save(testAuthor01);

		entityManager.flush();
		entityManager.clear();
		}

	@Test
	public void shouldLoadASavedAuthor()
		{
		// Action
		Long assignedId = testAuthor01.getId();
		Author authorToLoad = authorRepo.findById(assignedId).get();

		// Assert
		assertThat(testAuthor01, is(authorToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}

	@Test
	public void shouldLoadAuthorByName()
		{
		// Action
		Author authorToLoad = authorRepo.findByName(testAuthorName01);

		// Assert
		assertThat(testAuthor01, is(authorToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}

	@Test
	public void shouldFailToLoadAuthorByWrongName()
		{
		// Action
		Author authorToLoad = authorRepo.findByName("wrong name");

		// Assert
		assertThat(authorToLoad, is(nullValue()));
		}

//	@Test
//	public void shouldFailToSaveAuthorWithSameNameAsExistingAuthor()
//		{
//		// Arrange
//		String duplicateTestAuthorName01 = new String(testAuthorName01);
//		Author duplicateTestAuthor01 = new Author(duplicateTestAuthorName01);
//
//		// Action
//		long numberOfAuthorsBeforeSave = authorRepo.count();
//		try
//			{
//			authorRepo.save(duplicateTestAuthor01);
//			entityManager.flush();
//			entityManager.clear();
//			}
//		catch (Exception ex)
//			{
//			// Traverse exception nesting to final cause.
//			Throwable t = ex.getCause();
//			while ((t != null) && !(t instanceof ConstraintViolationException))
//				{
//				t = t.getCause();
//				}
//			if (t instanceof ConstraintViolationException)
//				{
//				// Here you're sure you have a ConstraintViolationException, so you can handle it
//				ConstraintViolationException cve = (ConstraintViolationException) t;
//				System.out.println("--------CONSTRAINT NAME: " + cve.getConstraintName());
//				entityManager.clear();
//				entityManager.flush();
//				}
//			else
//				{
//				System.out.println("Exception not of class ConstraintViolationException!");
//				System.out.println("-------------EXCEPTION MESSAGE------: " + ex.getMessage());
//				}
//			}
//		long numberOfAuthorsAfterSave = authorRepo.count();
//
//		// Assert
//		assertEquals(numberOfAuthorsBeforeSave, numberOfAuthorsAfterSave);
//		}
}
