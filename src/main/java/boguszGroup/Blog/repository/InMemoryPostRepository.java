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
  public Post getPostById(long id) {
    return posts.get(id);
  }

  @Override
  public void addNewPost(Post post) {
    post.setId(Ids.generateNewId(posts.keySet()));
    post.setTime(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
    try {
      File img = new File(folderPath, post.getId() + ".jpg");
      Path path = Paths.get(img.getAbsolutePath());
      Files.write(path, post.getImgFile().getBytes());
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

      post.setImg(post.getImgFile().getBytes());


    } catch (IOException e) {
      e.printStackTrace();
    }

    posts.put(post.getId(), post);
  }


  @Override
  public void addPostsFromLocalDisc(String folderPath) throws IOException {

    File folder = new File(folderPath);
    File[] fileList = folder.listFiles(file -> {
      return file.getName().contains(".html");
    });
    if(fileList!=null) {
      for (File src : fileList) {
        Post post = createPostFromLocalDir(src);
        posts.put(post.getId(), post);
      }
      System.out.println("Dodałem posty z dysku");
    }
    else{
      System.out.println("Nie ma postów na dysku");
    }
  }





}