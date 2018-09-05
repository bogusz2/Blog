package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public interface PostRepository {

  Collection<Post> getPosts();

  Post getPostById(Integer id);

  void addNewPost(Post post);

  InputStream getInputStreamImg(int id) throws IOException;

  void addPostsFromLocalDisc() throws IOException;

}
