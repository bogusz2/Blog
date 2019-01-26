package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import com.google.gson.Gson;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.io.*;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.List;

public class DBPostRepository implements PostRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Post> getPosts() {
        return em.createQuery("select id, title, date from Post  order by id asc", Post.class).getResultList();
    }

    @Override
    public Post getPostById(long id) {
        return em.find(Post.class, id);
    }

    @Override
    public List<Object[]> getColumn(String query) {
//        return em.createQuery("select title from Post order by id asc", String.class).getResultList();
        List<Object[]> a = em.createQuery(query, Object[].class).getResultList();

        return a;
    }

    @Override
    @Transactional
    public void addNewPost(Post post) {
        long maxId = em.createQuery("select max(id) from Post", Long.class).getSingleResult();
        post.setId(maxId + 1);
        post.setTime(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        try {
            byte[] dataImg = post.getImgFile().getBytes();
            post.setImg(dataImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        em.persist(post);
    }


}
