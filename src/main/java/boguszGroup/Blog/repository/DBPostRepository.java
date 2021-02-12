package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;

public class DBPostRepository implements PostRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Post> getPosts() {
        return em.createQuery("from Post  order by id asc", Post.class).getResultList();
    }

    @Override
    public Post getPostById(long id) {
        return em.find(Post.class, id);
    }

    public byte[] getPostImageById(long id) {
        return (byte[])
                em.createQuery("select img from Post where id=:query_id order by id asc")
                        .setParameter("query_id", id)
                        .getResultList()
                        .get(0);
    }

    @Override
    @Transactional
    public void addNewPost(Post post) {
        post.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        post.setImg(getImageBytes(post));
        em.persist(post);
    }

    private byte[] getImageBytes(Post post) {
        MultipartFile imgFile = post.getImgFile();
        if (imgFile != null) {
            try {
                return imgFile.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
                return null;//todo throw exception or warning about failure in adding image
            }
        } else {
            return null;
        }
    }
}
