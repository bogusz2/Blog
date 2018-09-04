package boguszGroup.Blog;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public @Data class Post {

  @NotNull
  @Size(min=2, max=50)
  private String title;

  @Lob
  @NotNull
  @Size(min=1)
  private String text;

  private String time;
  
  @Id
  private int id;

  @Lob
  private MultipartFile img;
  @Lob
  private String pathPostImage;

  public Post(String title, String text) {
    this.title = title;
    this.text = text;


  }

  public Post() {
  }

}
