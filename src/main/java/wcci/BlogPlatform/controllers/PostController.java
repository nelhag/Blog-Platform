package wcci.BlogPlatform.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.BlogPlatform.models.Author;
import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.models.Tag;
import wcci.BlogPlatform.repos.AuthorCrudRepo;
import wcci.BlogPlatform.repos.CategoryCrudRepo;
import wcci.BlogPlatform.repos.PostCrudRepo;
import wcci.BlogPlatform.repos.TagCrudRepo;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private CategoryCrudRepo categoryRepo;

	@Autowired
	private PostCrudRepo postRepo;

	@Autowired
	private AuthorCrudRepo authorRepo;

	@Autowired
	private TagCrudRepo tagRepo;

	@RequestMapping("")
	public String renderAllPosts(Model model)
		{
		model.addAttribute("allPostsModel", postRepo.findAll());
		model.addAttribute("allCategoriesModel", categoryRepo.findAll());
		model.addAttribute("allAuthorsModel", authorRepo.findAll());
		model.addAttribute("allTagsModel", tagRepo.findAll());
		return "allPostsView";
		}

	@RequestMapping("{id}")
	public String renderSinglePost(@PathVariable("id") Long id, Model model)
		{
		Post post = postRepo.findById(id).get();
		model.addAttribute("singlePostModel", post);
		model.addAttribute("authorsModel", post.getAuthors());
		model.addAttribute("tagsModel", post.getTags());

		List<Author> allAuthors = new ArrayList<Author>();
		authorRepo.findAll().iterator().forEachRemaining(allAuthors::add);
		allAuthors.removeAll(post.getAuthors());
		model.addAttribute("allAuthorsModel", allAuthors);

		List<Tag> allTags = new ArrayList<Tag>();
		tagRepo.findAll().iterator().forEachRemaining(allTags::add);
		allTags.removeAll(post.getTags());
		model.addAttribute("allTagsModel", allTags);
		return "singlePostView";
		}

	@PostMapping("{id}/add-tag")
	public String addTag(@PathVariable("id") Long id, String tag)
		{
		if (tag == null)
			{
			return "redirect:/posts/" + id;
			}
		Tag selectedTag = tagRepo.findByName(tag);
		Post post = postRepo.findById(id).get();
		if (selectedTag != null && post != null)
			{
			if (post.getTags().contains(selectedTag))
				{
				return "redirect:/posts/" + post.getId();
				}
			post.addTag(selectedTag);
			postRepo.save(post);
			return "redirect:/posts/" + post.getId();
			}
		return "redirect:/posts";
		}

	@PostMapping("{id}/add-author")
	public String addAuthor(@PathVariable("id") Long id, String author)
		{
		if (author == null)
			{
			return "redirect:/posts/" + id;
			}
		Author selectedAuthor = authorRepo.findByName(author);
		Post post = postRepo.findById(id).get();
		if (selectedAuthor != null && post != null)
			{
			if (post.getAuthors().contains(selectedAuthor))
				{
				return "redirect:/posts/" + post.getId();
				}
			selectedAuthor.addPost(post);
			authorRepo.save(selectedAuthor);
			return "redirect:/posts/" + post.getId();
			}
		return "redirect:/posts";
	}


	@PostMapping("add")
	public String addPost(String title, String body, String category, String author, String tag)
		{
		Category selectedCategory = categoryRepo.findByName(category);
		Author selectedAuthor = authorRepo.findByName(author);
		Tag selectedTag = tagRepo.findByName(tag);
		if (title == null || body == null || selectedCategory == null || selectedAuthor == null)
			{
			return "redirect:/posts";
			}
		Post postToAdd = new Post(title, body, selectedCategory);
		postRepo.save(postToAdd);

		selectedAuthor.addPost(postToAdd);
		authorRepo.save(selectedAuthor);

		if (selectedTag != null)
			{
			postToAdd = postRepo.findById(postToAdd.getId()).get();
			postToAdd.addTag(selectedTag);
			postRepo.save(postToAdd);
			}
		return "redirect:/posts/" + postToAdd.getId();
		}

}
