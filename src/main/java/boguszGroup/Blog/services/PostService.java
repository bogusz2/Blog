package boguszGroup.Blog.services;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>(postRepository.getPosts());
        return posts;
    }

    public Post getPost(Long id) {
        return postRepository.getPostById(id);
    }

    public byte[] getPostImageById(Long id) {
        return postRepository.getPostImageById(id);
    }

    public void addPost(Post post) {
        postRepository.addNewPost(post);
    }
}
