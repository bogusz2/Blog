package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;

import java.util.Collection;

public interface PostRepository {

  Collection<Post> getPosts();

  Post getPostById(Integer id);

  void addNewPost(Post post);

  }
