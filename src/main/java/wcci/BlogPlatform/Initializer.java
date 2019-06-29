package wcci.BlogPlatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

	@Override
	public void run(String... args) throws Exception
		{
		System.out.println("RUNNING INITIALIZER");
		populateWithEntities();
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
