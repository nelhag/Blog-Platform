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

import wcci.BlogPlatform.models.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryCrudRepoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategoryCrudRepo categoryRepo;

	// Arrange
	private String testCatName01 = "cat1";
	private Category testCat01 = new Category(testCatName01);

	@Before
	public void persistTestEntities()
		{
		categoryRepo.save(testCat01);

		entityManager.flush();
		entityManager.clear();
		}

	@Test
	public void shouldLoadASavedCategory()
		{
		// Action
		Long assignedId = testCat01.getId();
		Category categoryToLoad = categoryRepo.findById(assignedId).get();

		// Assert
		assertThat(testCat01, is(categoryToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}

	@Test
	public void shouldLoadCategoryByName()
		{
		// Action
		Category categoryToLoad = categoryRepo.findByName(testCatName01);

		// Assert
		assertThat(testCat01, is(categoryToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}

	@Test
	public void shouldFailToLoadCategoryByWrongName()
		{
		// Action
		Category categoryToLoad = categoryRepo.findByName("wrong name");

		// Assert
		assertThat(categoryToLoad, is(nullValue()));
		}

	@Test
	public void shouldFailToSaveCategoryWithSameNameAsExistingCategory()
		{
		// Arrange
		String duplicateTestCatName01 = "cat1";
		Category duplicateTestCat01 = new Category(duplicateTestCatName01);

		// Action
		long numberOfCatsBeforeSave = categoryRepo.count();
		try
			{
			categoryRepo.save(duplicateTestCat01);
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
		long numberOfCatsAfterSave = categoryRepo.count();

		// Assert
		assertEquals(numberOfCatsBeforeSave, numberOfCatsAfterSave);
		}
}
