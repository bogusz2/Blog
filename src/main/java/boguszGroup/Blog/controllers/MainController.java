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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    PostService postService;

    @RequestMapping("/blog")
    @ResponseBody
    public String blog() {
        return postService.getShortPostsJSON();
    }

    @RequestMapping("/dodajwpis")
    public String nowywpis(Model model) {
        model.addAttribute("post", new Post());
        return "nowywpis";
    }

    @RequestMapping("/post")
    @ResponseBody
    public String getPost(@RequestParam("id") Long id, Model model, HttpServletResponse response) throws IOException {
        String json = postService.getPost(id);
        model.addAttribute("post", json);
        return json;
    }

//    @RequestMapping(value = "/postImageJPG")
//    public @ResponseBody
//    byte[] getPostImage(@RequestParam("id") Long id, HttpServletResponse response, Model model) throws IOException {
//        model.addAttribute("id", id);
//        return postService.getPost(id).getImg();
//    }


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
