package boguszGroup.Blog.controllers;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {

  @Autowired
  PostService postService;

  @RequestMapping({"/", "/start"})
  public String start() {
    return "home";
  }

  @RequestMapping("/blog")
  public String blog(Model model) {
    List<Post> allPosts = postService.getAllPosts();
    model.addAttribute("posts", allPosts);
    return "blog";
  }

  @RequestMapping("/dodajwpis")
  public String nowywpis(Model model) {
    model.addAttribute("post", new Post());
    return "nowywpis";
  }

  @RequestMapping("/post")
  public String getPost(@RequestParam("id") Integer id, Model model){
    Post post = postService.getPost(id);
    model.addAttribute("post", post);
    model.addAttribute("id",post.getId());
    return "post";
  }

  @RequestMapping(value = "/blog", method = RequestMethod.POST)
  public String savePost(Post post) {
    System.out.println("dodaje wpis");
      postService.addPost(post);
      return "redirect:/blog";

  }
}
