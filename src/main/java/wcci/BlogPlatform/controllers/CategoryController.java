package wcci.BlogPlatform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@RequestMapping("")
	public String renderAllCategories(Model model)
		{
		// model.addAttribute("allCategoriesModel", categoryRepo.findAll());
		return "allCategoriesView";
		}

	@RequestMapping("{id}")
	public String renderSingleCategory(@PathVariable("id") Long id, Model model)
		{
		// model.addAttribute("singleCategoryModel", categoryRepo.findById(id).get());
		return "singleCategoryView";
		}

	@RequestMapping("add")
// @PostMapping("add")
	public String addCategory()
		{
		return "redirect:/categories";
		}
}
