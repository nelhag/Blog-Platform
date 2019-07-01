package wcci.BlogPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.BlogPlatform.models.Author;
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
		model.addAttribute("postsModel", authorRepo.findById(id).get().getPosts());
		return "singleAuthorView";
		}

	@PostMapping("add")
	public String addAuthor(String name)
		{
		if (name == null)
			{
			return "redirect:/authors";
			}
		if (name.isEmpty())
			{
			return "redirect:/authors";
			}
		Author existingAuthor = authorRepo.findByName(name);
		if (existingAuthor == null)
			{
			Author authorToAdd = new Author(name);
			authorRepo.save(authorToAdd);
			}
		return "redirect:/authors";
		}
}
