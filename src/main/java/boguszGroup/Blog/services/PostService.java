package boguszGroup.Blog.services;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.repository.PostRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {

    @Autowired
    PostRepository postRepository;

    final private int POSTS_ON_PAGE = 10;

    public String getShortPostsJSON(long page) {
        long id_first = 1 + (POSTS_ON_PAGE * page);
        long id_last = id_first + POSTS_ON_PAGE;
        String query = "select id, title, date from Post where id>" + id_first + "and id<" + id_last + " order by id asc";
        Gson gson = new Gson();
        List<Object[]> lista = postRepository.getPosts(query);
        return gson.toJson(lista);
    }


    public String getPost(Long id) {
        String query = "from Post where " + id + " order by id asc";
        Post result = postRepository.getPostById(id);
        Gson gson = new Gson();
        return gson.toJson(result);
    }

    public void addPost(Post post) {
        postRepository.addNewPost(post);

    }
}
