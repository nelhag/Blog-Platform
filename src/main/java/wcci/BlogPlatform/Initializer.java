package wcci.BlogPlatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import wcci.BlogPlatform.models.Author;
import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.models.Tag;
import wcci.BlogPlatform.repos.AuthorCrudRepo;
import wcci.BlogPlatform.repos.CategoryCrudRepo;
import wcci.BlogPlatform.repos.PostCrudRepo;
import wcci.BlogPlatform.repos.TagCrudRepo;

@Component
public class Initializer implements CommandLineRunner {

	@Autowired
	private CategoryCrudRepo categoryRepo;

	@Autowired
	private PostCrudRepo postRepo;

	@Autowired
	private AuthorCrudRepo authorRepo;

	@Autowired
	private TagCrudRepo tagRepo;

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
		}

	@Override
	public void run(String... args) throws Exception
		{
		System.out.println("RUNNING INITIALIZER");
		// populateWithEntities();
		persistTestEntities();

		testAuthor01.addPost(testPost01);
		authorRepo.save(testAuthor01);

		testAuthor02.addPost(testPost01);
		testAuthor02.addPost(testPost02);
		authorRepo.save(testAuthor02);

		testPost01.addTag(testTag01);
		testPost01.addTag(testTag02);
		postRepo.save(testPost01);

		testPost02.addTag(testTag01);
		testPost02.addTag(testTag01);
		postRepo.save(testPost02);
		}

	private void populateWithEntities()
		{
		AutoPopulator populator = new AutoPopulator();
		populator.populateRepoWithRandomCategories(categoryRepo, 5);
		populator.populateRepoWithRandomAuthors(authorRepo, 5);
		populator.populateRepoWithRandomTags(tagRepo, 5);
		populator.populateRepoWithRandomPosts(categoryRepo, postRepo, 10);
		}
}
