package boguszGroup.Blog.controller;

import boguszGroup.Blog.model.Post;
import boguszGroup.Blog.model.User;
import boguszGroup.Blog.service.PostService;
import boguszGroup.Blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/allPosts")
    @PreAuthorize("hasAuthority('READ_ALL_POSTS_PRIVILEGE')")
    public String blog(Model model) {
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("posts", allPosts);
        return "blog";
    }

    @PreAuthorize("hasAuthority('ENABLE_USERS_PRIVILEGE')")
    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        List<User> allUsers = userService.getUsers();
        model.addAttribute("users", allUsers);
        return "users";
    }

    @PreAuthorize("hasAuthority('ENABLE_USERS_PRIVILEGE')")
    @PostMapping("/admin/users/{id}")
    public String enableUsers(@PathVariable Long id, Model model) {
        userService.enableUser(id);
//        model.addAttribute("users", allUsers);
        return "redirect:/admin/users";
    }
}
