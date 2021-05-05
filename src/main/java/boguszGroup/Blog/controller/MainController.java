package boguszGroup.Blog.controller;

import boguszGroup.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping({"/", "/start"})
    public String start() {
        return "home";
    }

}
