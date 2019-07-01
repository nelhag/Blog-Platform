package wcci.BlogPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.BlogPlatform.models.Tag;
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
		model.addAttribute("postsModel", tagRepo.findById(id).get().getPosts());
		return "singleTagView";
		}

	@PostMapping("add")
	public String addTag(String name)
		{
		if (name == null)
			{
			return "redirect:/tags";
			}
		if (name.isEmpty())
			{
			return "redirect:/tags";
			}
		Tag existingTag = tagRepo.findByName(name);
		if (existingTag == null)
			{
			Tag tagToAdd = new Tag(name);
			tagRepo.save(tagToAdd);
			}
		return "redirect:/tags";
		}
}
