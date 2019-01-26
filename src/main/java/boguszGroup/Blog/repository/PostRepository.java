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
import java.util.List;

public interface PostRepository {

  Collection<Post> getPosts();

  List<Object[]> getColumn(String query);

  Post getPostById(long id);

  void addNewPost(Post post);

}
