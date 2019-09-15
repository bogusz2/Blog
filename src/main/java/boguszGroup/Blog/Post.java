package boguszGroup.Blog;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public @Data
@Table(name="post_table")
class Post {

  @NotNull
  private String title;

  @Lob
  @NotNull
  @Size(min = 1)
  private String text;

  @Column(name="date")
  private String date;

  @Id
  private long id;

  @Transient
  private MultipartFile imgFile;

  @Lob
  private byte[] img;

  public Post() {
  }

}
