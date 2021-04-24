package boguszGroup.Blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "post")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name="post_author_id", referencedColumnName = "id")
    private User author;

    @NotNull
    private String title;

    @Lob
    @NotNull
    @Size(min = 1)
    private String text;

    private String date;

    @Transient
    private MultipartFile imgFile;

    @Lob
    private byte[] img;

}
