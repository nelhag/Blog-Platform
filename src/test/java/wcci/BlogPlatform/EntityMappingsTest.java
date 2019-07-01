package wcci.BlogPlatform;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
import wcci.BlogPlatform.repos.AuthorCrudRepo;
import wcci.BlogPlatform.repos.CategoryCrudRepo;
import wcci.BlogPlatform.repos.PostCrudRepo;
import wcci.BlogPlatform.repos.TagCrudRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityMappingsTest {

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
	public void postShouldHaveAuthorAfterAuthorAddsPostAndViceVersa()
		{
		// Arrange
		Post postUnderTest = postRepo.findById(testPost01.getId()).get();
		Author authorUnderTest = authorRepo.findById(testAuthor01.getId()).get();

		// Action
		authorUnderTest.addPost(postUnderTest);
		authorRepo.save(authorUnderTest);
		entityManager.flush();
		entityManager.clear();

		// reload entities
		Post postUnderTestAfterLoad = postRepo.findById(testPost01.getId()).get();
		Author authorUnderTestAfterLoad = authorRepo.findById(testAuthor01.getId()).get();

		// Assert
		assertThat(postUnderTestAfterLoad.getAuthors(), containsInAnyOrder(authorUnderTestAfterLoad));
		assertThat(authorUnderTestAfterLoad.getPosts(), containsInAnyOrder(postUnderTestAfterLoad));
		}

	@Test
	public void shouldNotBeAbleToAddDuplicatePostsToAuthor()
		{
		// Arrange
		Post postUnderTest = postRepo.findById(testPost01.getId()).get();
		Author authorUnderTest = authorRepo.findById(testAuthor01.getId()).get();

		// Action
		authorUnderTest.addPost(postUnderTest);
		authorRepo.save(authorUnderTest);
		entityManager.flush();
		entityManager.clear();

		authorUnderTest.addPost(postUnderTest);
		authorRepo.save(authorUnderTest);
		entityManager.flush();
		entityManager.clear();

		// reload post
		postUnderTest = postRepo.findById(testPost01.getId()).get();

		// Assert
		int expectedSize = 1;
		int actualSize = postUnderTest.getAuthors().size();
		assertEquals(expectedSize, actualSize);
		}

	@Test
	public void shouldBeAbleToAddMultiplePostsToAuthor()
		{
		// Arrange
		Post postUnderTest01 = postRepo.findById(testPost01.getId()).get();
		Post postUnderTest02 = postRepo.findById(testPost02.getId()).get();
		Author authorUnderTest = authorRepo.findById(testAuthor01.getId()).get();

		// Action
		authorUnderTest.addPost(postUnderTest01);
		authorUnderTest.addPost(postUnderTest02);
		authorRepo.save(authorUnderTest);
		entityManager.flush();
		entityManager.clear();

		// reload entities
		Author authorUnderTestAfterLoad = authorRepo.findById(testAuthor01.getId()).get();
		Post postUnderTest01AfterLoad = postRepo.findById(testPost01.getId()).get();
		Post postUnderTest02AfterLoad = postRepo.findById(testPost02.getId()).get();

		// Assert
		// Verify Author gets posts
		int expectedSize = 2;
		int actualSize = authorUnderTestAfterLoad.getPosts().size();
		assertEquals(expectedSize, actualSize);
		assertThat(authorUnderTestAfterLoad.getPosts(), containsInAnyOrder(postUnderTest01AfterLoad, postUnderTest02AfterLoad));

		// Verify Posts get author
		expectedSize = 1;
		actualSize = postUnderTest01AfterLoad.getAuthors().size();
		assertEquals(expectedSize, actualSize);
		assertThat(postUnderTest01.getAuthors(), containsInAnyOrder(authorUnderTest));

		expectedSize = 1;
		actualSize = postUnderTest02AfterLoad.getAuthors().size();
		assertEquals(expectedSize, actualSize);
		assertThat(postUnderTest02.getAuthors(), containsInAnyOrder(authorUnderTest));
		}

	@Test
	public void tagShouldHavePostAfterPostAddsTagAndViceVersa()
		{
		// Arrange
		Post postUnderTest = postRepo.findById(testPost01.getId()).get();
		Tag tagUnderTest = tagRepo.findById(testTag01.getId()).get();

		// Action
		postUnderTest.addTag(tagUnderTest);
		postRepo.save(postUnderTest);
		entityManager.flush();
		entityManager.clear();

		// reload entities
		Post postUnderTestAfterLoad = postRepo.findById(testPost01.getId()).get();
		Tag tagUnderTestAfterLoad = tagRepo.findById(testTag01.getId()).get();

		// Assert
		assertThat(tagUnderTestAfterLoad.getPosts(), containsInAnyOrder(postUnderTestAfterLoad));
		assertThat(postUnderTestAfterLoad.getTags(), containsInAnyOrder(tagUnderTestAfterLoad));
		}

}
