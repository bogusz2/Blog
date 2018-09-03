package boguszGroup.Blog;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public @Data class Post {
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

}
