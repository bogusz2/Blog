package boguszGroup.Blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
@Getter
public enum PostAccessibility {
    PUBLIC("Public"),
    PRIVATE("Private");

    private final String access;

    PostAccessibility(String s) {
        this.access = s;
    }
}
