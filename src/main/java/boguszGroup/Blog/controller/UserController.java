//package boguszGroup.Blog.controller;
//
//import boguszGroup.Blog.model.User;
//import boguszGroup.Blog.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @PreAuthorize("hasAuthority('READ_USERS_INFO')")
//    @GetMapping("/admin/users")
//    public String users(Model model) {
//        List<User> allUsers = userService.getUsers();
//        model.addAttribute("users", allUsers);
//        return "users";
//    }
//
//
//    //todo add enable user feature(set enable on users table)
//}
