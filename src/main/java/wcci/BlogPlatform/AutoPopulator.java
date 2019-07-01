package wcci.BlogPlatform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

	private static final Random RANDOM = new Random(System.nanoTime());
	private static final Lorem LOREM = LoremIpsum.getInstance();

	public AutoPopulator()
		{
		}

	public void randomlyLinkPostsAuthorsAndTags(PostCrudRepo postRepo, AuthorCrudRepo authorRepo, TagCrudRepo tagRepo)
		{
		Set<Author> authors = new HashSet<Author>();
		authorRepo.findAll().iterator().forEachRemaining(authors::add);
		Set<Tag> tags = new HashSet<Tag>();
		tagRepo.findAll().iterator().forEachRemaining(tags::add);

		ArrayList<Post> posts = new ArrayList<Post>();
		postRepo.findAll().iterator().forEachRemaining(posts::add);

		for (Post post : posts)
			{
			int authorCount = 1 + Math.max(0, -2 + RANDOM.nextInt(authors.size()));
			Set<Author> randomAuthors = getRandomSubsetOfAuthorSet(authors, authorCount);
			linkPostWithAuthors(randomAuthors, post, authorRepo);

			int tagCount = Math.max(0, -2 + RANDOM.nextInt(tags.size()));
			Set<Tag> randomTags = getRandomSubsetOfTagSet(tags, tagCount);
			linkPostWithTags(randomTags, post, postRepo);
			}
		}

	public void linkPostWithTags(Set<Tag> tags, Post post, PostCrudRepo postRepo)
		{
		Post upToDatePost = postRepo.findById(post.getId()).get();
		for (Tag tag : tags)
			{
			upToDatePost.addTag(tag);
			}
		postRepo.save(upToDatePost);
		}

	public void linkPostWithAuthors(Set<Author> authors, Post post, AuthorCrudRepo authorRepo)
		{
		for (Author author : authors)
			{
			Author upToDateAuthor = authorRepo.findById(author.getId()).get();
			upToDateAuthor.addPost(post);
			authorRepo.save(upToDateAuthor);
			}
		}

	public Set<Tag> getRandomSubsetOfTagSet(Set<Tag> superSet, int subSetSize)
		{
		if (subSetSize >= superSet.size())
			{
			return superSet;
			}
		else
			{
			List<Tag> list = new LinkedList<Tag>(superSet);
			Set<Tag> randomSubSet = new HashSet<Tag>();
			int counter = 0;
			while (!list.isEmpty() && counter < subSetSize)
				{
				int index = RANDOM.nextInt(list.size());
				randomSubSet.add(list.remove(index));
				counter++;
				}
			return randomSubSet;
			}
		}

	public Set<Author> getRandomSubsetOfAuthorSet(Set<Author> superSet, int subSetSize)
		{
		if (subSetSize >= superSet.size())
			{
			return superSet;
			}
		else
			{
			List<Author> list = new LinkedList<Author>(superSet);
			Set<Author> randomSubSet = new HashSet<Author>();
			int counter = 0;
			while (!list.isEmpty() && counter < subSetSize)
				{
				int index = RANDOM.nextInt(list.size());
				randomSubSet.add(list.remove(index));
				counter++;
				}
			return randomSubSet;
			}
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
