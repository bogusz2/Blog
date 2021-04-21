package boguszGroup.Blog;

import boguszGroup.Blog.repository.DBPostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfig {

    @Bean(name = "DBRepository")
    @Profile("prod")
    public DBPostRepository createPostRepoInDB() {
        return new DBPostRepository();
    }
}
