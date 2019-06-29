package wcci.BlogPlatform.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.BlogPlatform.repos.PostCrudRepo;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Resource
	PostCrudRepo postRepo;

	@RequestMapping("")
	public String renderAllPosts(Model model)
		{
		model.addAttribute("allPostsModel", postRepo.findAll());
		return "allPostsView";
		}

	@RequestMapping("{id}")
	public String renderSinglePost(@PathVariable("id") Long id, Model model)
		{
		// model.addAttribute("singlePostModel", reviewRepo.findById(id).get());
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
