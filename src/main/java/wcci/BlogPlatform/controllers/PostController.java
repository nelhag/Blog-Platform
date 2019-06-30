package wcci.BlogPlatform.controllers;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "allPostsView";
		}

	@RequestMapping("{id}")
	public String renderSinglePost(@PathVariable("id") Long id, Model model)
		{
		model.addAttribute("singlePostModel", postRepo.findById(id).get());
		return "singlePostView";
		}

	@RequestMapping("new")
	public String renderNewPost(Model model)
		{
		return "newPostView";
		}

	@RequestMapping("add")
	// @PostMapping("add")
	public String addPost()
		{
		return "redirect:/posts";
		}
}
