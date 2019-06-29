package wcci.BlogPlatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
PostStorage postRepo;
@RequestMapping("/posts")
pubic String displayPosts(Model model) {
	model.addAttribute("posts",postRepo.findAllPosts());
	return "posts";
}
}
