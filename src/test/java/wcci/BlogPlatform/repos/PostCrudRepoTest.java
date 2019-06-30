package wcci.BlogPlatform.repos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostCrudRepoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategoryCrudRepo categoryRepo;

	@Autowired
	private PostCrudRepo postRepo;

	// Arrange
	private String testCatName01 = "cat1";
	private Category testCat01 = new Category(testCatName01);

	private String testPostTitle01 = "title1";
	private String testPostBody01 = "body1";
	private Post testPost01 = new Post(testPostTitle01, testPostBody01, testCat01);

	@Before
	public void persistTestEntities()
		{
		categoryRepo.save(testCat01);

		entityManager.flush();
		entityManager.clear();
		}

	@Test
	public void shouldSaveAndLoadAPost()
		{
		// Action
		postRepo.save(testPost01);
		entityManager.flush();
		entityManager.clear();

		Long assignedId = testPost01.getId();
		Post postToLoad = postRepo.findById(assignedId).get();

		// Assert
		assertThat(testPost01, is(postToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}
}
