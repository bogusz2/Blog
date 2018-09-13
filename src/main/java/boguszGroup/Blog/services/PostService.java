package boguszGroup.Blog.services;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {

  @Autowired
  PostRepository postRepository;

  public List<Post> getAllPosts() {
    List<Post> posts = new ArrayList<>(postRepository.getPosts());

    return posts;
  }

  public Post getPost(Long id) {
    return postRepository.getPostById(id);
  }

  public InputStream getInputStreamImgFromRepo(Long id) throws IOException {
    return postRepository.getInputStreamImg(id);
  }


  public void addPost(Post post) {
    postRepository.addNewPost(post);

  }
}
