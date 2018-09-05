package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;

public class DBPostRepository implements PostRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Collection<Post> getPosts() {
    return em.createQuery("from Post", Post.class).getResultList();
  }

  @Override
  public Post getPostById(Integer id) {
    return em.find(Post.class, id);
  }

  @Override
  public InputStream getInputStreamImg(int id) {
    InputStream file = new ByteArrayInputStream(getPostById(id).getByteArrayImg());
    return file;
  }

  @Override
  @Transactional
  public void addNewPost(Post post) {
    int maxId = em.createQuery("select max(id) from Post", Integer.class).getSingleResult();
    post.setId(maxId + 1);
    post.setTime(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
    try {
      byte[] dataImg = post.getImg().getBytes();
      post.setByteArrayImg(dataImg);
    } catch (IOException e) {
      e.printStackTrace();
    }

    em.persist(post);
  }

  //@PostConstruct
  @Transactional
  @Override
  public void addPostsFromLocalDisc() throws IOException {
    final String folderPath = "C:\\Users\\Gal Anonim\\BLOG";

    File folder = new File(folderPath);
    File[] fileList = folder.listFiles(file -> {
      if (file.getName().contains(".html")) {
        return true;
      }
      return false;
    });

    for (File src : fileList) {

      Post post = new Post();
      post.setId(Integer.parseInt(src.getName().substring(0, src.getName().lastIndexOf("."))));
      String textOfPost = new String(Files.readAllBytes(Paths.get(src.getAbsolutePath())));
      post.setText(textOfPost);

      String pathTXT = src.getAbsolutePath().replace(".html", ".txt");
      String txt = new String(Files.readAllBytes(Paths.get(pathTXT)));
      String dateOfPost = txt.substring(0, txt.indexOf("\n"));
      post.setTime(dateOfPost);

      String titleOfPost = txt.substring(txt.indexOf("\n")).trim();
      if (titleOfPost.equals("Fotoblog mrrrasniasta w Photoblog.pl")) {
        titleOfPost = "Wpis " + src.getName().replace(".html", "");
      }
      post.setTitle(titleOfPost);

      File img = new File(src.getAbsolutePath().replace(".html", ".jpg"));
      FileInputStream input = new FileInputStream(img);
      MultipartFile imgMPF = new MockMultipartFile("img", img.getName(), "image/jpg", IOUtils.toByteArray(input));
      post.setImg(imgMPF);

      post.setPathPostImage(src.getAbsolutePath().replace(".html", ".jpg"));

      byte[] data = post.getImg().getBytes();
      post.setByteArrayImg(data);
      em.persist(post);
    }
    System.out.println("Dodałem posty z dysku do bazy danych");
  }
}
