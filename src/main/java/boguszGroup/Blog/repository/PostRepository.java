package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import java.io.IOException;
import java.util.Collection;
public interface PostRepository {

  Collection<Post> getPosts();

  Post getPostById(Long id);

  void addNewPost(Post post);

  void addPostsFromLocalDisc(String folderPath) throws IOException;

}
