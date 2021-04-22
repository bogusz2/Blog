package boguszGroup.Blog.Post;

import boguszGroup.Blog.PostService;
import boguszGroup.Blog.security.model.Post;
import boguszGroup.Blog.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping("/myPosts")
    public String getMyPosts(Model model, Authentication authentication) {
        List<Post> myPosts = postService.getPostsByAuthor(authentication.getName());
        model.addAttribute("posts", myPosts);
        return "blog";
    }

    @PostMapping("/blog")
    public String savePost(@Valid Post post, BindingResult bindingResult, Authentication authentication) {

        if (bindingResult.hasErrors()) {
            System.out.println("ERROR");//todo throw exception
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getObjectName() + " " + error.getDefaultMessage()));
            return "newPostForm";
        } else {
            postService.addPost(post, authentication.getName());
            return "redirect:/blog";
        }
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

}
