package wcci.BlogPlatform.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.BlogPlatform.repos.PostCrudRepo;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Resource
	PostCrudRepo postRepo;

	@RequestMapping("")
	public String displayPosts(Model model)
		{
		model.addAttribute("postsModel", postRepo.findAll());
		return "allPostsView";
		}
}
