package boguszGroup.Blog;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "post_table")
@NoArgsConstructor
public class Post {

    @NotNull
    private String title;

    @Lob
    @NotNull
    @Size(min = 1)
    private String text;

    @Column(name = "date")
    private String date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Transient
    private MultipartFile imgFile;

    @Lob
    private byte[] img;



}
