package wcci.BlogPlatform;

import java.util.ArrayList;
import java.util.Random;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import wcci.BlogPlatform.models.Author;
import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.models.Tag;
import wcci.BlogPlatform.repos.AuthorCrudRepo;
import wcci.BlogPlatform.repos.CategoryCrudRepo;
import wcci.BlogPlatform.repos.PostCrudRepo;
import wcci.BlogPlatform.repos.TagCrudRepo;

public class AutoPopulator {

	private static final char[] CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final Random RANDOM = new Random(System.nanoTime());
	private static final Lorem LOREM = LoremIpsum.getInstance();

	public AutoPopulator()
		{
		}

	private static String generateRandomString(int length)
		{
		String returnStr = "";
		for (int i = 0; i < length; i++)
			{
			int index = RANDOM.nextInt(CHARS.length);
			returnStr += CHARS[index];
			}
		return returnStr;
		}

	public void populateRepoWithRandomPosts(CategoryCrudRepo categoryRepo, PostCrudRepo postRepo, int count)
		{
		if (count <= 0)
			{
			return;
			}
		else
			{
			ArrayList<Category> categories = new ArrayList<Category>();
			categoryRepo.findAll().iterator().forEachRemaining(categories::add);

			if (categories.size() > 0)
				{
				for (int i = 0; i < count; i++)
					{
					Category category = categories.get(RANDOM.nextInt(categories.size()));
					String title = LOREM.getTitle(2, 5);
					String body = LOREM.getParagraphs(1, 10);
					Post post = new Post(title, body, category);
					postRepo.save(post);
					}
				}
			}
		}

	public void populateRepoWithRandomCategories(CategoryCrudRepo categoryRepo, int count)
		{
		if (count <= 0)
			{
			return;
			}
		else
			{
			for (int i = 0; i < count; i++)
				{
				Category category = new Category(LOREM.getTitle(1));
				categoryRepo.save(category);
				}
			}
		}

	public void populateRepoWithRandomAuthors(AuthorCrudRepo authorRepo, int count)
		{
		if (count <= 0)
			{
			return;
			}
		else
			{
			for (int i = 0; i < count; i++)
				{
				Author author = new Author(LOREM.getName());
				authorRepo.save(author);
				}
			}
		}

	public void populateRepoWithRandomTags(TagCrudRepo tagRepo, int count)
		{
		if (count <= 0)
			{
			return;
			}
		else
			{
			for (int i = 0; i < count; i++)
				{
				Tag tag = new Tag(LOREM.getWords(1));
				tagRepo.save(tag);
				}
			}
		}
}
