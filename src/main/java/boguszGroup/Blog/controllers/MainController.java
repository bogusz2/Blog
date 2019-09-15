package boguszGroup.Blog.controllers;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {

    @Autowired
    PostService postService;

    @GetMapping("/blog")
    @ResponseBody
    public String blog(@RequestParam(value = "page", required = false, defaultValue = "0") Long page) {
        return postService.getShortPostsJSON(page);
    }


    @RequestMapping("/addPost")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "newpost";
    }

    @RequestMapping("/post")
    @ResponseBody
    public String getPost(@RequestParam("id") long id, Model model, HttpServletResponse response) throws IOException {
        String json = postService.getPost(id);
        model.addAttribute("post", json);
        return json;
    }


    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("ERROR");
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getObjectName() + " " + error.getDefaultMessage()));
            return "post";
        } else {
            postService.addPost(post);
            return "redirect:/blog";
        }

    }
}
