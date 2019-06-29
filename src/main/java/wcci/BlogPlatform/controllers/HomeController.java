package wcci.BlogPlatform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String renderRoot(Model model)
		{
		model.addAttribute("homeModel", "");
		return "homeView";
		}

	@RequestMapping("home")
	public String renderHomePage(Model model)
		{
		model.addAttribute("homeModel", "");
		return "homeView";
		}
}
