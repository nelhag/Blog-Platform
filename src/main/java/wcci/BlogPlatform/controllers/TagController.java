package wcci.BlogPlatform.controllers;

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
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private CategoryCrudRepo categoryRepo;

	@Autowired
	private PostCrudRepo postRepo;

	@Autowired
	private AuthorCrudRepo authorRepo;

	@Autowired
	private TagCrudRepo tagRepo;

	@RequestMapping("")
	public String renderAllTags(Model model)
		{
		model.addAttribute("allTagsModel", tagRepo.findAll());
		return "allTagsView";
		}

	@RequestMapping("{id}")
	public String renderSingleTag(@PathVariable("id") Long id, Model model)
		{
		model.addAttribute("singleTagModel", tagRepo.findById(id).get());
		return "singleTagView";
		}

	@RequestMapping("add")
// @PostMapping("add")
	public String addTag()
		{
		return "redirect:/tags";
		}
}
