package boguszGroup.Blog.repository;

import boguszGroup.Blog.Post;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryPostRepositoryTest {

  @Test
  void postFromDiscNotNull() {
    String filePath = "C:\\Users\\Gal Anonim\\BLOG\\100.html";
    File src = new File(filePath);
    InMemoryPostRepository repo = new InMemoryPostRepository();
    try {
      Post post = repo.createPostFromLocalDir(src);
      assertNotNull(post.getImg());
      assertNotNull(post.getImgFile());
      assertTrue(post.getId() != 0);
      assertEquals(post.getPathPostImage(), filePath.replace(".html",".jpg"));
      assertNotNull(post.getTitle());
      assertNotNull(post.getTime());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Test
  void postsFromDisc() {
    String folderPath = "C:\\Users\\Gal Anonim\\BLOG";
    InMemoryPostRepository repo = new InMemoryPostRepository();
    File folder = new File(folderPath);
    File[] fileList = folder.listFiles(file -> {
      return file.getName().contains(".html");
    });
    assertFalse(fileList==null);
    try {
    repo.addPostsFromLocalDisc(folderPath);
      assertFalse(repo.posts.isEmpty());
      assertEquals(repo.posts.size(),fileList.length);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  @Test
  void addNewPostTest() throws IOException{
    String filePath = "C:\\Users\\Gal Anonim\\BLOG\\1.jpg";
    String text = "TestUTF8ąłżó";
    String title = "TestTitleUTF8ąłżó";

    Post post = new Post();
    File img = new File(filePath);
    FileInputStream input = new FileInputStream(img);
    post.setImgFile(new MockMultipartFile("testFile", IOUtils.toByteArray(input)));
    post.setImg(IOUtils.toByteArray(input));
    post.setText(text);
    post.setTitle(title);

    InMemoryPostRepository repo = new InMemoryPostRepository();
    assertTrue(repo.posts.isEmpty());
    repo.addNewPost(post);
    assertFalse(repo.posts.isEmpty());
    assertEquals(title, repo.getPostById(0).getTitle());
    assertEquals(text, repo.getPostById(0).getText());
    assertNotNull(repo.getPostById(0).getTime());
    assertNotNull(repo.getPostById(0).getImg());


  }
}