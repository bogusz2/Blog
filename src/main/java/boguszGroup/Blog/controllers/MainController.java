package boguszGroup.Blog.controllers;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @GetMapping("/addPost")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "newPostForm";
    }

    @GetMapping("/post")
    public String getPost(@RequestParam("id") Long id, Model model, HttpServletResponse response) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        model.addAttribute("id", post.getId());
        return "post";
    }

    @GetMapping("/postImageJPG")
    public @ResponseBody
    byte[] getPostImage(@RequestParam("id") Long id, HttpServletResponse response, Model model) {
        model.addAttribute("id", id);
        return postService.getPostImageById(id);
    }


    @PostMapping("/blog")
    public String savePost(@Valid Post post, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("ERROR");//todo throw exception
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getObjectName() + " " + error.getDefaultMessage()));
            return "newPostForm";
        } else {
            postService.addPost(post);
            return "redirect:/blog";
        }
    }
}
