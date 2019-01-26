package boguszGroup.Blog.services;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.repository.PostRepository;
import com.google.gson.Gson;
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

  public String getBlogJSON() {
    String query = "select id, title, time from Post order by id asc";
    Gson gson = new Gson();
    List<Object[]> lista = postRepository.getColumn(query);
    String json = gson.toJson(lista);
    return json;
  }

  public String getPost(Long id) {
    String query = "from Post where "+id+" order by id asc";
    Post result = postRepository.getPostById(id);
    Gson gson = new Gson();
    String postJson = gson.toJson(result);
    return postJson;
  }

  public void addPost(Post post) {
    postRepository.addNewPost(post);

  }
}
