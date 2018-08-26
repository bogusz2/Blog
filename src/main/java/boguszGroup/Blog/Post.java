package boguszGroup.Blog;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class Post {
  @NotNull
  @Size(min=2, max=50)
  private String title;
  @NotNull
  @Size(min=1)
  private String text;
  private LocalDateTime time;
  private int id;
  private MultipartFile img;
  private String pathPostImage;

  public Post(String title, String text) {
    this.title = title;
    this.text = text;
    this.time = LocalDateTime.now();

  }

  public Post() {
  }

  public String getPathPostImage() {
    return pathPostImage;
  }

  public void setPathPostImage(String pathPostImage) {
    this.pathPostImage = pathPostImage;
  }


  public MultipartFile getImg() {
    return img;
  }

  public void setImg(MultipartFile img) {
    this.img = img;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
