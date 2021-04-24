package boguszGroup.Blog.config;

import boguszGroup.Blog.repository.DBPostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfig {

    @Bean(name = "DBRepository")
    @Profile("dev")
    public DBPostRepository createPostRepoInDB() {
        return new DBPostRepository();
    }
}
