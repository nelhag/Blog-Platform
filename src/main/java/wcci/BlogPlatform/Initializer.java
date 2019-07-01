package wcci.BlogPlatform;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import wcci.BlogPlatform.models.Author;
import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;
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

	private AutoPopulator populator = new AutoPopulator();

	@Override
	public void run(String... args) throws Exception
		{
		System.out.println("RUNNING INITIALIZER");
		System.out.println("POPULATING DATABASE WITH RANDOM LATIN");
		populateWithEntities();
		populator.randomlyLinkPostsAuthorsAndTags(postRepo, authorRepo, tagRepo);
		}

	private void populateWithEntities()
		{
		populator.populateRepoWithRandomCategories(categoryRepo, 8);
		populator.populateRepoWithRandomAuthors(authorRepo, 7);
		populator.populateRepoWithRandomTags(tagRepo, 14);
		populator.populateRepoWithRandomPosts(categoryRepo, postRepo, 25);
		}
}
