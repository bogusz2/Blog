package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import boguszGroup.Blog.utils.Ids;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class InMemoryPostRepository implements PostRepository {

  private final String folderPath = "C:\\Users\\Gal Anonim\\BLOG";

  Map<Long, Post> posts = new HashMap<>();

  public InMemoryPostRepository() {
  }

  @Override
  public Collection<Post> getPosts() {
    return posts.values();
  }

  @Override
  public Post getPostById(Long id) {
    return posts.get(id);
  }

  @Override
  public InputStream getInputStreamImg(Long id) throws IOException {
    InputStream file = new FileInputStream(getPostById(id).getPathPostImage());
    return file;
  }

  @Override
  public void addNewPost(Post post) {
    post.setId(Ids.generateNewId(posts.keySet()));
    post.setTime(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
    try {
      File img = new File(folderPath, post.getId() + ".jpg");
      Path path = Paths.get(img.getAbsolutePath());
      Files.write(path, post.getImg().getBytes());
      post.setPathPostImage(path.toString());

      File dateTitlePost = new File(folderPath, post.getId() + ".txt");
      BufferedWriter bw = new BufferedWriter(new FileWriter(dateTitlePost));
      bw.write(post.getTime());
      bw.newLine();
      bw.write(post.getTitle());
      bw.close();

      File html = new File(folderPath, post.getId() + ".html");
      BufferedWriter bwHtml = new BufferedWriter(new FileWriter(html));
      bwHtml.write(post.getText());
      bwHtml.close();

      post.setByteArrayImg(post.getImg().getBytes());


    } catch (IOException e) {
      e.printStackTrace();
    }

    posts.put(post.getId(), post);
  }

  @PostConstruct
  @Override
  public void addPostsFromLocalDisc() throws IOException {

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

      posts.put(post.getId(), post);
    }
    System.out.println("Dodałem posty z dysku");
  }

}