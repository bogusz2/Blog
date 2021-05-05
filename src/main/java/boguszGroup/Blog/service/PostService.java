package boguszGroup.Blog.service;

import boguszGroup.Blog.model.Post;
import boguszGroup.Blog.repository.PostRepository;
import boguszGroup.Blog.model.User;
import boguszGroup.Blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;



    public List<Post> getAllPosts() {
        return new ArrayList<>(postRepository.getPosts());
    }

    public List<Post> getPublicPosts() {
        return new ArrayList<>(postRepository.getPublicPosts());
    }

    public List<Post> getPostsByAuthor(String username) {
        long userId = userRepository.findByUsername(username).getId();//todo when posts is null
        return new ArrayList<>(postRepository.getPostsByUserId(userId));
    }

    public Post getPost(Long id) {
        return postRepository.getPostById(id);
    }

    public byte[] getPostImageById(Long id) {
        return postRepository.getPostImageById(id);
    }

    public void addPost(Post post, String username) {
        User user = userRepository.findByUsername(username);
        post.setAuthor(user);
        postRepository.addNewPost(post);
    }
}
