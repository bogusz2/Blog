package boguszGroup.Blog;


import java.time.LocalDateTime;

public class Post {
  private String title;
  private String text;
  private LocalDateTime time;
  private int id;
  public Post(String title, String text) {
    this.title = title;
    this.text = text;
    this.time = LocalDateTime.now();
  }

  public Post() {
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
