package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository{

    Collection<Post> getPosts();

    Post getPostById(long id);

    void addNewPost(Post post);

    byte[] getPostImageById(long id);
}
