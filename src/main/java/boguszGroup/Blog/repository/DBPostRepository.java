package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class DBPostRepository implements PostRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Post getPostById(long id) {
        return em.find(Post.class, id);
    }

    @Override
    public List<Object[]> getPosts(String query) {
        List<Object[]> a = em.createQuery(query, Object[].class).getResultList();
        return a;
    }

    @Override
    @Transactional
    public void addNewPost(Post post) { //todo
        long maxId = em.createQuery("select max(id) from Post", Long.class).getSingleResult();
        post.setId(maxId + 1);
        post.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        try {
            byte[] dataImg = post.getImgFile().getBytes();
            post.setImg(dataImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        em.persist(post);
    }


}
