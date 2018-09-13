package boguszGroup.Blog;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public @Data
class Post {

  @NotNull
  private String title;

  @Lob
  @NotNull
  @Size(min = 1)
  private String text;

  private String time;

  @Id
  private long id;

  @Transient
  private MultipartFile img;

  @Lob
  private byte[] byteArrayImg;

  @Transient
  private String pathPostImage;

  public Post(String title, String text) {
    this.title = title;
    this.text = text;
  }

  public Post() {
  }

}
