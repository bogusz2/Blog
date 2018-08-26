package boguszGroup.Blog.controllers;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.services.PostService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
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
  public String getPost(@RequestParam("id") Integer id, Model model, HttpServletResponse response) throws IOException {
    Post post = postService.getPost(id);
    model.addAttribute("post", post);
    model.addAttribute("id", post.getId());
    //model.addAttribute("image",post.getBase64Image());

    return "post";
  }

  @RequestMapping(value = "/postImageJPG")
  public @ResponseBody
  byte[] getPostImage(@RequestParam("id") Integer id, HttpServletResponse response, Model model) throws IOException {
    model.addAttribute("id", id);
    FileInputStream file = new FileInputStream(postService.getPost(id).getPathPostImage());
    System.out.println(postService.getPost(id).getPathPostImage());
    // InputStream in = getClass().getResourceAsStream(pathOfImage);
    return IOUtils.toByteArray(file);
  }


  @RequestMapping(value = "/blog", method = RequestMethod.POST)
  public String savePost(@Valid Post post, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      System.out.println("ERROR");
      bindingResult.getAllErrors().forEach(error -> System.out.println(error.getObjectName() + " " + error.getDefaultMessage()));
      return "nowywpis";
    } else {
      postService.addPost(post);
      return "redirect:/blog";
    }

  }


}
