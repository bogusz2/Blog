package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
public interface PostRepository {

  Collection<Post> getPosts();

  Post getPostById(long id);

  void addNewPost(Post post);

  void addPostsFromLocalDisc(String folderPath) throws IOException;

  default Post createPostFromLocalDir(File src) throws IOException{
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
    MultipartFile imgMPF = new MockMultipartFile("imgFile", img.getName(), "image/jpg", IOUtils.toByteArray(input));
    post.setImgFile(imgMPF);
    post.setImg(imgMPF.getBytes());
    post.setPathPostImage(img.getAbsolutePath());

    return post;
  }

}
