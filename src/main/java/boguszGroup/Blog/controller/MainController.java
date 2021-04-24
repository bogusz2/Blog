package boguszGroup.Blog.controller;

import boguszGroup.Blog.model.Post;
import boguszGroup.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping({"/", "/start"})
    public String start() {
        return "home";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("posts", allPosts);
        return "blog";
    }





}
