package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;

import java.util.List;

public interface PostRepository {

    List<Object[]> getPosts(String query);

    Post getPostById(long id);

    void addNewPost(Post post);

}
