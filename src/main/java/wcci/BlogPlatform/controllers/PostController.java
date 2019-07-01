package wcci.BlogPlatform.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "singlePostView";
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

//	@PostMapping("add")
//	public String addPost(String title, String body, String category, @RequestParam("tagsChecked") Collection<String> tagNames, @RequestParam("authorsChecked") Collection<String> authorNames)
//		{
//		Category selectedCategory = categoryRepo.findByName(category);
//		if (title == null || body == null || selectedCategory == null || authorNames == null)
//			{
//			return "redirect:/posts";
//			}
//		Post postToAdd = new Post(title, body, selectedCategory);
//		postRepo.save(postToAdd);
//
//		for (String name : authorNames)
//			{
//			Author author = authorRepo.findByName(name);
//			author.addPost(postToAdd);
//			authorRepo.save(author);
//			}
//		if (tagNames != null)
//			{
//			Long postId = postToAdd.getId();
//			Post postToAddUpdated = postRepo.findById(postId).get();
//			for (String name : tagNames)
//				{
//				Tag tag = tagRepo.findByName(name);
//				postToAddUpdated.addTag(tag);
//				}
//			postRepo.save(postToAddUpdated);
//			}
//		return "redirect:/posts";
//		}

}
