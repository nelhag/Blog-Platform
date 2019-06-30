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
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private CategoryCrudRepo categoryRepo;

	@Autowired
	private PostCrudRepo postRepo;

	@Autowired
	private AuthorCrudRepo authorRepo;

	@Autowired
	private TagCrudRepo tagRepo;

	@RequestMapping("")
	public String renderAllAuthors(Model model)
		{
		model.addAttribute("allAuthorsModel", authorRepo.findAll());
		return "allAuthorsView";
		}

	@RequestMapping("{id}")
	public String renderSingleAuthor(@PathVariable("id") Long id, Model model)
		{
		model.addAttribute("singleAuthorModel", authorRepo.findById(id).get());
		return "singleAuthorView";
		}

	@RequestMapping("add")
// @PostMapping("add")
	public String addAuthor()
		{
		return "redirect:/authors";
		}
}
