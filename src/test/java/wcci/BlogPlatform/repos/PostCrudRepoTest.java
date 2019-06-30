package wcci.BlogPlatform.repos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import wcci.BlogPlatform.models.Author;
import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.models.Tag;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostCrudRepoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategoryCrudRepo categoryRepo;

	@Autowired
	private PostCrudRepo postRepo;

	@Autowired
	private AuthorCrudRepo authorRepo;

	@Autowired
	private TagCrudRepo tagRepo;

	// Arrange
	// Categories
	private String testCatName01 = "cat01";
	private Category testCat01 = new Category(testCatName01);
	private String testCatName02 = "cat02";
	private Category testCat02 = new Category(testCatName02);

	// Authors
	private String testAuthorName01 = "author01";
	private Author testAuthor01 = new Author(testAuthorName01);
	private String testAuthorName02 = "author02";
	private Author testAuthor02 = new Author(testAuthorName02);

	// Tags
	private String testTagName01 = "tag01";
	private Tag testTag01 = new Tag(testTagName01);
	private String testTagName02 = "tag02";
	private Tag testTag02 = new Tag(testTagName02);

	// Posts
	private String testPostTitle01 = "title01";
	private String testPostBody01 = "body01";
	private Post testPost01 = new Post(testPostTitle01, testPostBody01, testCat01);

	private String testPostTitle02 = "title02";
	private String testPostBody02 = "body02";
	private Post testPost02 = new Post(testPostTitle02, testPostBody02, testCat02);

	@Before
	public void persistTestEntities()
		{
		categoryRepo.save(testCat01);
		categoryRepo.save(testCat02);
		authorRepo.save(testAuthor01);
		authorRepo.save(testAuthor02);
		tagRepo.save(testTag01);
		tagRepo.save(testTag02);
		postRepo.save(testPost01);
		postRepo.save(testPost02);
		entityManager.flush();
		entityManager.clear();
		}

	@Test
	public void shouldLoadAPost()
		{
		// Action
		Post postToLoad = postRepo.findById(testPost01.getId()).get();

		// Assert
		assertThat(testPost01, is(postToLoad)); // This works because "equals" is based on only id and name. (if posts were included this would never be true because the memory location of the posts field would be different for both objects.)
		}
}
