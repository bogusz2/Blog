package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.utils.Ids;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class PostRepository {


  Map<Integer, Post> posts = new HashMap<>();

  public PostRepository() {
  }

  public Collection<Post> getPosts() {
    return posts.values();
  }

  @PostConstruct
  public void addNewPost() throws IOException {
    Post post1 = new Post("title1", "text1");
    posts.put(Ids.generateNewId(posts.keySet()), post1);
    posts.put(Ids.generateNewId(posts.keySet()), new Post("title2", "text2"));

    System.out.println("Dodałem testowy post");
  }

  public void addNewPost(Post post) {
    post.setId(Ids.generateNewId(posts.keySet()));

    try {
      File tempFile = File.createTempFile("Image"+((Integer)post.getId()),".png");
      Path path = Paths.get(tempFile.getAbsolutePath());
      Files.write(path, post.getImg().getBytes());
      post.setPathPostImage(path.toString());
      System.out.println(path);
      System.out.println(post.getId());
    } catch (IOException e) {
      e.printStackTrace();
    }

    post.setTime(LocalDateTime.now());
    posts.put(post.getId(), post);
  }

  public Post getPostById(Integer id) {
    return posts.get(id);
  }

}
