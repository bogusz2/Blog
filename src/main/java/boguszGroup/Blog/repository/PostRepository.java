package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.utils.Ids;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;


public class PostRepository {


  Map<Integer,Post> posts = new HashMap<>();

  public PostRepository() {
  }

  public Collection<Post> getPosts() {
    return posts.values();
  }

  @PostConstruct
  public void addNewPost() {

    posts.put(Ids.generateNewId(posts.keySet()),new Post("title1", "text1"));
    posts.put(Ids.generateNewId(posts.keySet()),new Post("title2", "text2"));
    System.out.println("Dodałem testowy post");
  }

  public void addNewPost(Post post) {
    post.setId(Ids.generateNewId(posts.keySet()));
    post.setTime(LocalDateTime.now());
    posts.put(post.getId(), post);
  }

  public Post getPostById(Integer id){
    return posts.get(id);
  }

}
