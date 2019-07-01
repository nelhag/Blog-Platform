package wcci.BlogPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.repos.AuthorCrudRepo;
import wcci.BlogPlatform.repos.CategoryCrudRepo;
import wcci.BlogPlatform.repos.PostCrudRepo;
import wcci.BlogPlatform.repos.TagCrudRepo;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryCrudRepo categoryRepo;

	@Autowired
	private PostCrudRepo postRepo;

	@Autowired
	private AuthorCrudRepo authorRepo;

	@Autowired
	private TagCrudRepo tagRepo;

	@RequestMapping("")
	public String renderAllCategories(Model model)
		{
		model.addAttribute("allCategoriesModel", categoryRepo.findAll());
		return "allCategoriesView";
		}

	@RequestMapping("{id}")
	public String renderSingleCategory(@PathVariable("id") Long id, Model model)
		{
		model.addAttribute("singleCategoryModel", categoryRepo.findById(id).get());
		model.addAttribute("postsModel", categoryRepo.findById(id).get().getPosts());
		return "singleCategoryView";
		}

	@PostMapping("add")
	public String addCategory(String name)
		{
		if (name == null)
			{
			return "redirect:/categories";
			}
		if (name.isEmpty())
			{
			return "redirect:/categories";
			}
		Category existingCategory = categoryRepo.findByName(name);
		if (existingCategory == null)
			{
			Category categoryToAdd = new Category(name);
			categoryRepo.save(categoryToAdd);
			}
		return "redirect:/categories";
		}
}
